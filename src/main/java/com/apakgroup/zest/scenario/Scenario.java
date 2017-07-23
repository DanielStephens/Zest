package com.apakgroup.zest.scenario;

import com.apakgroup.zest.context.DescriptionContext;
import com.apakgroup.zest.context.TestContext;
import com.apakgroup.zest.exceptions.TestFailureException;
import com.apakgroup.zest.scenario.given.Given;
import com.apakgroup.zest.scenario.given.GivenScenario;
import com.apakgroup.zest.scenario.then.Then;
import com.apakgroup.zest.scenario.then.ThenScenario;
import com.apakgroup.zest.scenario.when.When;
import com.apakgroup.zest.scenario.when.WhenScenario;

public class Scenario {

    private final TestContext context;

    private final DescriptionContext descriptor;

    public Scenario(final DescriptionContext descriptor) {
        this.descriptor = descriptor;
        this.context = new TestContext(descriptor);
    }

    public Given defineGiven() {
        return new GivenScenario();
    }

    public When defineWhen() {
        return new WhenScenario(context);
    }

    public Then defineThen() {
        return new ThenScenario(context);
    }

    public void runTest() {
        try{
            context.buildRunner().test();
            descriptor.logSuccess();
        } catch (AssertionError ae) {
            descriptor.logFailure(ae);
            throw ae;
        } catch (Throwable e) {
            descriptor.logFailure(e);
            throw new TestFailureException(e);
        }
    }
}

