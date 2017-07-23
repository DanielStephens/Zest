package com.apakgroup.zest.test.annotations.transformer;

import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.scenario.Scenario;
import com.google.common.base.Optional;

public class CompositionScenarioContextTransformer implements ScenarioTransformer {

    private final ScenarioTransformer first;

    private final ScenarioTransformer second;

    private CompositionScenarioContextTransformer(final ScenarioTransformer first,
            final ScenarioTransformer second) {
        this.first = first;
        this.second = second;
    }

    private CompositionScenarioContextTransformer(final ScenarioTransformer second) {
        this.first = new ScenarioTransformer() {

            @Override
            public Optional<Scenario> build(final FrameworkMethod method, final Object target) {
                return Optional.absent();
            }

        };
        this.second = second;
    }

    public static CompositionScenarioContextTransformer use(final ScenarioTransformer transformer) {
        return new CompositionScenarioContextTransformer(transformer);
    }

    @Override
    public Optional<Scenario> build(final FrameworkMethod method, final Object target) {
        return first.build(method, target).or(second.build(method, target));
    }

    public CompositionScenarioContextTransformer or(final ScenarioTransformer or) {
        return new CompositionScenarioContextTransformer(this, or);
    }

}

