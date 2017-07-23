package com.apakgroup.zest.test.annotations.definitions;

import java.lang.annotation.Annotation;
import java.util.Collection;

import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.scenario.Scenario;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public class AnnotationDefinitionsUtil {

	private static final Collection<ZestTestAnnotationDefinition<?>> definitions = ImmutableList
			.<ZestTestAnnotationDefinition<?>>of(new DescriptiveTestAnnotationDefinition(),
					new TestAnnotationDefinition());

	private AnnotationDefinitionsUtil() {
	}

	public static <A extends Annotation> Test buildTestFrom(final A annotation) {
		return doForApplicable(annotation, new Function<ZestTestAnnotationDefinition<?>, Test>() {

			@Override
			public Test apply(final ZestTestAnnotationDefinition<?> input) {
				return input.testBuilderFor(annotation).convert(annotation);
			}

		});
	}

	public static <A extends Annotation> Optional<Scenario> buildPossibleScenarioFrom(final FrameworkMethod method,
			final Object target, final A annotation) {
		return doForApplicable(annotation, new Function<ZestTestAnnotationDefinition<?>, Optional<Scenario>>() {

			@Override
			public Optional<Scenario> apply(final ZestTestAnnotationDefinition<?> input) {
				return input.convertToScenario(method, target, annotation);
			}

		});
	}

	public static <A extends Annotation, T> T doForApplicable(final A annotation,
			final Function<ZestTestAnnotationDefinition<?>, T> function) {
		for (ZestTestAnnotationDefinition<?> definition : definitions) {
			if (!definition.isApplicableFor(annotation)) {
				continue;
			}

			return function.apply(definition);
		}

		return function.apply(new DefaultAnnotationDefinition());
	}

	public static Collection<Class<? extends Annotation>> getTestMethodAnnotations() {
		Builder<Class<? extends Annotation>> builder = ImmutableList.builder();
		for (ZestTestAnnotationDefinition<?> definition : definitions) {
			builder = builder.add(definition.getAnotationClass());
		}
		return builder.build();
	}

}
