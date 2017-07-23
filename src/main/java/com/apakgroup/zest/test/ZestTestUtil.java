package com.apakgroup.zest.test;

import java.lang.annotation.Annotation;

import com.apakgroup.zest.test.annotations.WiredZestContext;
import com.apakgroup.zest.test.annotations.definitions.AnnotationDefinitionsUtil;
import com.apakgroup.zest.test.annotations.transformer.AbsentScenarioContextTransformer;
import com.apakgroup.zest.test.annotations.transformer.ClassAnnotationScenarioTransformer;
import com.apakgroup.zest.test.annotations.transformer.CompositionScenarioContextTransformer;
import com.apakgroup.zest.test.annotations.transformer.ExistingFieldScenarioContextTransformer;
import com.apakgroup.zest.test.annotations.transformer.MethodAnnotationScenarioTransformer;
import com.apakgroup.zest.test.annotations.transformer.ScenarioTransformer;
import com.apakgroup.zest.test.before.ZestTestInjector;
import com.apakgroup.zest.test.injection.DefaultAnnotatedFieldRetriever;
import com.apakgroup.zest.test.injection.DefaultFieldSetter;
import com.apakgroup.zest.test.injection.FieldRetriever;
import com.apakgroup.zest.test.injection.FieldSetter;

public class ZestTestUtil {

    private ZestTestUtil() {
    }

    public static ZestTestInjector zestTestInjector() {
        return new ZestTestInjector(allMethodAnnotationsTransformer().or(existingFieldScenarioTransformer())
                .or(allClassAnnotationsTransformer()), fieldSetter());
    }

    private static FieldSetter<Object, ZestContext> fieldSetter() {
        return new DefaultFieldSetter<>(fieldRetriever());
    }

    private static FieldRetriever fieldRetriever() {
        return new DefaultAnnotatedFieldRetriever<>(WiredZestContext.class, ZestContext.class);
    }

    private static CompositionScenarioContextTransformer compose(final ScenarioTransformer transformer) {
        return CompositionScenarioContextTransformer.use(transformer);
    }

    private static CompositionScenarioContextTransformer allMethodAnnotationsTransformer() {
        CompositionScenarioContextTransformer transformer = compose(new AbsentScenarioContextTransformer());

        for (Class<? extends Annotation> clazz : AnnotationDefinitionsUtil.getTestMethodAnnotations()) {
            transformer = transformer.or(new MethodAnnotationScenarioTransformer<>(clazz));
        }

        return transformer;
    }

    private static ScenarioTransformer allClassAnnotationsTransformer() {
        CompositionScenarioContextTransformer transformer = compose(new AbsentScenarioContextTransformer());

        for (Class<? extends Annotation> clazz : AnnotationDefinitionsUtil.getTestMethodAnnotations()) {
            transformer = transformer.or(new ClassAnnotationScenarioTransformer<>(clazz));
        }

        return transformer;
    }

    private static ScenarioTransformer existingFieldScenarioTransformer() {
        return new ExistingFieldScenarioContextTransformer<>(fieldRetriever());
    }
    
}

