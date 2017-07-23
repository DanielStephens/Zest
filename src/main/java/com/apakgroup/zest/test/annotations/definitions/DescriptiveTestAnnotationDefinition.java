package com.apakgroup.zest.test.annotations.definitions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Test;
import org.junit.runners.model.FrameworkMethod;
import org.springframework.beans.BeanUtils;

import com.apakgroup.zest.builder.Zest;
import com.apakgroup.zest.context.ScenarioDescriptor;
import com.apakgroup.zest.scenario.Scenario;
import com.apakgroup.zest.test.annotations.AnnotationUtil;
import com.apakgroup.zest.test.annotations.definitions.DescriptiveTestAnnotationDefinition.DescriptiveTest;
import com.apakgroup.zest.test.annotations.definitions.descriptor.Descriptor;
import com.apakgroup.zest.test.annotations.definitions.descriptor.SimpleDescriptor;
import com.google.common.base.Optional;

public class DescriptiveTestAnnotationDefinition extends GenericAnnotationDefinition<DescriptiveTest> {

	public DescriptiveTestAnnotationDefinition() {
		super(DescriptiveTest.class);
	}

	@Override
	protected Test internalConvert(final DescriptiveTest annotation) {
		return AnnotationUtil.testAnnotation(annotation.timeout());
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD })
	public static @interface DescriptiveTest {

		long timeout() default 0L;

		Class<? extends Descriptor> descriptor() default SimpleDescriptor.class;

		String description();

		String[] parameters() default {};

	}

	@Override
	protected Optional<Scenario> internalConvertToScenario(final FrameworkMethod method, final Object target,
			final DescriptiveTest annotation) {
		Descriptor descriptor = BeanUtils.instantiateClass(annotation.descriptor());
		final String description = descriptor.describe(annotation.description(), annotation.parameters());

		ScenarioDescriptor scenarioDescriptor = new ScenarioDescriptor() {

			@Override
			public String describe() {
				return description;
			}
		};

		return Optional.of(Zest.testScenario(method, target, scenarioDescriptor));
	}

}
