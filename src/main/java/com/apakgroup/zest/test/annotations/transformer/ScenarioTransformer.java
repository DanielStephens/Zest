package com.apakgroup.zest.test.annotations.transformer;

import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.scenario.Scenario;
import com.google.common.base.Optional;

public interface ScenarioTransformer {

    public Optional<Scenario> build(FrameworkMethod method, Object target);

}
