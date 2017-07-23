package com.apakgroup.zest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.apakgroup.zest.builder.Zest;
import com.apakgroup.zest.context.ScenarioDescriptors;
import com.apakgroup.zest.scenario.Scenario;
import com.apakgroup.zest.scenario.given.Given;
import com.apakgroup.zest.scenario.then.Then;
import com.apakgroup.zest.scenario.when.When;

@RunWith(MockitoJUnitRunner.class)
public class ExampleTests {

    @Mock
    private WrappedObj obj;

    @InjectMocks
    private ObjWrapper wrapper;

    private Scenario scenario;

    @Before
    public void setup() {
        scenario = Zest.testScenario(ScenarioDescriptors.from("Jira {} : Scenario #{}", 123, 5));

        Given given = scenario.defineGiven();

        given.nothingHappens().isUsedWhenCalling(obj).a();
        given.theRealMethod().isUsedWhenCalling(obj).realB();
        given.theValue("abc").isUsedWhenCalling(obj).getB();
        given.theRealMethod().isUsedWhenCalling(obj).setB(Matchers.anyString());
        given.theThrowable(new RuntimeException("A")).isThrownWhenCalling(obj).c();
        given.theAnswer(new Answer<String>() {

            @Override
            public String answer(final InvocationOnMock invocation) throws Throwable {
                return invocation.getMethod().getName();
            }

        }).isUsedWhenCalling(obj).d();
        given.theRealMethod().isUsedWhenCalling(obj).getE();
        given.theRealMethod().isUsedWhenCalling(obj).setE(Matchers.anyString());

        obj.setB("B");
        obj.setE("E");
    }

    @Test
    public void testAssertingOnProxy() {
        When when = scenario.defineWhen();

        when.object(obj).calls().a();
        String b = when.object(obj).calls().realB();

        Then then = scenario.defineThen();
        then.assertThat(b, is(equalTo("B")));

        scenario.runTest();
    }

    @Test
    public void testAssertingOnProxyChanges() {
        Given given = scenario.defineGiven();
        given.theRealMethod().isUsedWhenCalling(obj).a();

        When when = scenario.defineWhen();

        when.object(obj).calls().a();
        String b = when.object(obj).calls().realB();

        Then then = scenario.defineThen();
        then.assertThat(b, is(equalTo("NEW")));

        scenario.runTest();
    }

    @Test
    public void testRecallOrdering() {
        Given given = scenario.defineGiven();
        given.theRealMethod().isUsedWhenCalling(obj).a();

        When when = scenario.defineWhen();

        String b = when.object(obj).calls().realB();
        when.object(obj).calls().a();

        Then then = scenario.defineThen();
        then.assertThat(b, is(equalTo("B")));

        scenario.runTest();
    }

    @Test
    public void testExceptions() {
        When when = scenario.defineWhen();

        when.object(obj).calls().c();
        when.object(obj).calls().c();

        Then then = scenario.defineThen();
        then.expectException(RuntimeException.class);

        scenario.runTest();
    }

    @Test
    public void testFailurePrintsCorrectDefaultMessage() {
        Given given = scenario.defineGiven();
        given.theRealMethod().isUsedWhenCalling(obj).a();

        When when = scenario.defineWhen();

        when.object(obj).calls().a();
        String b = when.object(obj).calls().realB();

        Then then = scenario.defineThen();
        then.assertThat(b, is(equalTo("ABC")));
        then.expectExceptionWithMessage("Expected: is \"ABC\"");

        scenario.runTest();
    }

    @Test
    public void testFailurePrintsCorrectCustomMessage() {
        Given given = scenario.defineGiven();
        given.theRealMethod().isUsedWhenCalling(obj).a();

        When when = scenario.defineWhen();

        when.object(obj).calls().a();
        String b = when.object(obj).calls().realB();

        Then then = scenario.defineThen();
        then.assertThat(b, is(equalTo("ABC")), "Something was wrong with {}", b);
        then.expectExceptionWithMessage("Something was wrong with NEW");

        scenario.runTest();
    }

}

