package com.apakgroup.zest.test.annotations.transformer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.scenario.Scenario;
import com.apakgroup.zest.test.injection.FieldRetriever;
import com.google.common.base.Optional;

public class ExistingFieldScenarioContextTransformer<A extends Annotation> implements ScenarioTransformer {

    private final FieldRetriever fieldRetriever;

    public ExistingFieldScenarioContextTransformer(final FieldRetriever fieldRetriever) {
        this.fieldRetriever = fieldRetriever;
    }

    @Override
    public Optional<Scenario> build(final FrameworkMethod method, final Object target) {
        for (Field field : fieldRetriever.getFields(target)) {
            Object o;
            try {
                o = field.get(target);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                continue;
            }
            if (o != null && o instanceof Scenario) {
                return Optional.fromNullable((Scenario) o);
            }
        }

        return Optional.absent();
    }


}

