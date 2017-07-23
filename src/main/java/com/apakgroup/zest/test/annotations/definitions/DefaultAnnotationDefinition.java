package com.apakgroup.zest.test.annotations.definitions;

import java.lang.annotation.Annotation;

import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.scenario.Scenario;
import com.apakgroup.zest.test.annotations.AnnotationUtil;
import com.google.common.base.Optional;

public class DefaultAnnotationDefinition implements ZestTestAnnotationDefinition<Annotation> {

	@Override
	public <A extends Annotation> TestBuilder<A> testBuilderFor(final A annotation) {
		return new TestBuilder<A>() {

			@Override
			public Test convert(final A annotation) {
				return AnnotationUtil.testAnnotation();
			}

		};
	}

	@Override
	public <A extends Annotation> boolean isApplicableFor(final A annotation) {
		return true;
	}

	@Override
	public <A extends Annotation> Optional<Scenario> convertToScenario(final FrameworkMethod method,
			final Object target, final A annotation) {
		return Optional.absent();
	}

	@Override
	public Class<Annotation> getAnotationClass() {
		return Annotation.class;
	}

}
