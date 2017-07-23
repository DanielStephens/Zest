package com.apakgroup.zest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.assertj.core.api.StringAssert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.apakgroup.zest.test.ZestMockitoRunner;
import com.apakgroup.zest.test.ZestTest;
import com.apakgroup.zest.test.annotations.definitions.DescriptiveTestAnnotationDefinition.DescriptiveTest;

@RunWith(ZestMockitoRunner.class)
public class ExampleTest2 extends ZestTest {

	@Mock
	private WrappedObj obj;

	@InjectMocks
	private ObjWrapper wrapper;

	@Before
	public void setup() {
		given().nothingHappens().isUsedWhenCalling(obj).a();
		given().theRealMethod().isUsedWhenCalling(obj).realB();
		given().theValue("abc").isUsedWhenCalling(obj).getB();
		given().theRealMethod().isUsedWhenCalling(obj).setB(Matchers.anyString());
		given().theThrowable(new RuntimeException("A")).isThrownWhenCalling(obj).c();
		given().theAnswer(new Answer<String>() {

			@Override
			public String answer(final InvocationOnMock invocation) throws Throwable {
				return invocation.getMethod().getName();
			}

		}).isUsedWhenCalling(obj).d();
		given().theRealMethod().isUsedWhenCalling(obj).getE();
		given().theRealMethod().isUsedWhenCalling(obj).setE(Matchers.anyString());

		obj.setB("B");
		obj.setE("E");
	}

	@DescriptiveTest(description = "Testing")
	public void testAssertingOnProxy() {
		when(obj).calls().a();
		String b = when().object(obj).calls().realB();

		then().assertThat(b, is(equalTo("B")));
		then().assertThat(b, is(equalTo("A")),
				"was expecting B but thought we'd assert for A to force a failure! actual answer was {}", b);
	}

	@DescriptiveTest(description = "Testing {}", parameters = { "Injection" })
	public void testFailsWithInjectedMessageAssertingOnProxy() {
		when(obj).calls().a();
		String b = when().object(obj).calls().realB();

		then().assertThat(b, is(equalTo("B")));
		then().assertThat(b, is(equalTo("A")),
				"was expecting B but thought we'd assert for A to force a failure! actual answer was {}", b);
	}

	@DescriptiveTest(description = "Testing {}", parameters = { "Injection" })
	public void testAssertingOnProxyPass() {
		when(obj).calls().a();
		String b = when().object(obj).calls().realB();

		then().assertThat(b, is(equalTo("B")));
	}

	@DescriptiveTest(description = "Testing", parameters = { "Injection" })
	public void testRecallOrdering() {
		given().theRealMethod().isUsedWhenCalling(obj).a();

		String b = when().object(obj).calls().realB();
		when(obj).calls().a();

		then().assertThat(b, is(equalTo("B")));
		then().assertUsing(StringAssert.class).that(b).startsWith("B");
	}

	@DescriptiveTest(description = "Testing")
	public void test() {
		assertThat(given(), is(not(equalTo(null))));
	}

}
