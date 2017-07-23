package com.apakgroup.zest.scenario.when;

import com.apakgroup.zest.context.TestContext;

public class WhenScenario implements When {

    private final TestContext recall;

    public WhenScenario(final TestContext recall) {
        this.recall = recall;
    }

    @Override
    public void nothingHappens() {
        return;
    }

    @Override
    public <T> WhenCall<T> object(final T realObject) {
        return new WhenCall<>(recall.enhance(realObject));
    }
    
}

