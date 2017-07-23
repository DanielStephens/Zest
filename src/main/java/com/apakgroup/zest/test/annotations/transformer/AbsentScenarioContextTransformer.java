package com.apakgroup.zest.test.annotations.transformer;

import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.scenario.Scenario;
import com.google.common.base.Optional;

public class AbsentScenarioContextTransformer implements ScenarioTransformer {
    @Override
    public Optional<Scenario> build(final FrameworkMethod method, final Object target) {
        return Optional.absent();
    }


}

