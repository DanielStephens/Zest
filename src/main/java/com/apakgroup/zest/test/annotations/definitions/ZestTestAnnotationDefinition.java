package com.apakgroup.zest.test.annotations.definitions;

import java.lang.annotation.Annotation;

import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.scenario.Scenario;
import com.google.common.base.Optional;

public interface ZestTestAnnotationDefinition<B extends Annotation> {

	<A extends Annotation> TestBuilder<A> testBuilderFor(A annotation);

	<A extends Annotation> boolean isApplicableFor(A annotation);

	<A extends Annotation> Optional<Scenario> convertToScenario(FrameworkMethod method, Object target, A annotation);

	Class<B> getAnotationClass();

}
