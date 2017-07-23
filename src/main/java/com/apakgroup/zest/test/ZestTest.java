package com.apakgroup.zest.test;

import com.apakgroup.zest.scenario.given.Given;
import com.apakgroup.zest.scenario.then.Then;
import com.apakgroup.zest.scenario.when.When;
import com.apakgroup.zest.scenario.when.WhenCall;
import com.apakgroup.zest.test.annotations.WiredZestContext;

public class ZestTest {

    @WiredZestContext
    private ZestContext context;

    protected final Given given() {
        return context.currentScenario().defineGiven();
    }

    protected final When when() {
        return context.currentScenario().defineWhen();
    }

    protected final <T> WhenCall<T> when(final T realObject) {
        return context.currentScenario().defineWhen().object(realObject);
    }

    protected final Then then() {
        return context.currentScenario().defineThen();
    }

}

