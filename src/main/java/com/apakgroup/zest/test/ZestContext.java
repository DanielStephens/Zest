package com.apakgroup.zest.test;

import com.apakgroup.zest.scenario.Scenario;

public class ZestContext {

    private final Scenario scenario;

    public ZestContext(final Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario currentScenario() {
        return scenario;
    }

}

