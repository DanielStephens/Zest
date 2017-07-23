package com.apakgroup.zest.test.annotations.definitions;

import java.lang.annotation.Annotation;

import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.scenario.Scenario;
import com.google.common.base.Optional;

public abstract class GenericAnnotationDefinition<T extends Annotation> implements ZestTestAnnotationDefinition<T> {

	private final Class<T> anotationClass;

	public GenericAnnotationDefinition(final Class<T> anotationClass) {
		this.anotationClass = anotationClass;
	}

	@Override
	public <A extends Annotation> TestBuilder<A> testBuilderFor(final A annotation) {
		if (!isApplicableFor(annotation)) {
			throw new ClassCastException(
					String.format("Cannot handle the annotation class '{}'", annotation.annotationType()));
		}

		return new TestBuilder<A>() {

			@SuppressWarnings("unchecked")
			@Override
			public Test convert(final A annotation) {
				T b = (T) annotation;
				return internalConvert(b);
			}

		};
	}

	protected abstract Test internalConvert(T annotation);

	@Override
	public <A extends Annotation> boolean isApplicableFor(final A annotation) {
		return anotationClass.equals(annotation.annotationType());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A extends Annotation> Optional<Scenario> convertToScenario(final FrameworkMethod method,
			final Object target, final A annotation) {
		if (!isApplicableFor(annotation)) {
			return Optional.absent();
		}

		return internalConvertToScenario(method, target, (T) annotation);
	}

	protected abstract Optional<Scenario> internalConvertToScenario(final FrameworkMethod method, final Object target,
			T annotation);

	public Class<T> getAnotationClass() {
		return anotationClass;
	}

}
