package com.apakgroup.zest.test.annotations.transformer;

import java.lang.annotation.Annotation;

import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.scenario.Scenario;
import com.apakgroup.zest.test.annotations.definitions.AnnotationDefinitionsUtil;
import com.google.common.base.Optional;

public class MethodAnnotationScenarioTransformer<A extends Annotation> implements ScenarioTransformer {

    private final Class<A> annotationClass;

    public MethodAnnotationScenarioTransformer(final Class<A> annotationClass) {
        this.annotationClass = annotationClass;
    }

    @Override
    public Optional<Scenario> build(final FrameworkMethod method, final Object target) {
        A annotation = method.getAnnotation(annotationClass);
        if (annotation == null) {
            return Optional.absent();
        }

        return AnnotationDefinitionsUtil.buildPossibleScenarioFrom(method, target, annotation);
    }


}

