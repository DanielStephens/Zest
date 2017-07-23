package com.apakgroup.zest.test.before;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.apakgroup.zest.scenario.Scenario;
import com.apakgroup.zest.test.ZestContext;
import com.apakgroup.zest.test.annotations.transformer.ScenarioTransformer;
import com.apakgroup.zest.test.filters.MockitoFilter;
import com.apakgroup.zest.test.injection.FieldSetter;
import com.google.common.base.Optional;

public class ZestTestInjector implements BeforeSetup {

    private final ScenarioTransformer scenarioAnnotationTransformer;

    private final FieldSetter<Object, ZestContext> fieldSetter;

    public ZestTestInjector(final ScenarioTransformer scenarioAnnotationTransformer,
            final FieldSetter<Object, ZestContext> fieldSetter) {
        this.scenarioAnnotationTransformer = scenarioAnnotationTransformer;
        this.fieldSetter = fieldSetter;
    }

    @Override
    public void before(final FrameworkMethod method, final Object target, final Statement statement) {
        Optional<Scenario> scenario = scenarioAnnotationTransformer.build(method, target);
        if (scenario.isPresent()) {
            ZestContext context = new ZestContext(scenario.get());
            MockitoFilter.setZestContext(context);
            fieldSetter.setFields(target, context);
        } else {
            MockitoFilter.setZestContext(null);
        }

    }

}

