package com.apakgroup.zest.test.annotations.definitions;

import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.scenario.Scenario;
import com.google.common.base.Optional;

public class TestAnnotationDefinition extends GenericAnnotationDefinition<Test> {

    public TestAnnotationDefinition() {
        super(Test.class);
    }

    @Override
    protected Test internalConvert(final Test annotation) {
        return annotation;
    }

    @Override
    protected Optional<Scenario> internalConvertToScenario(final FrameworkMethod method, final Object target,
            final Test annotation) {
        return Optional.absent();
    }

}

