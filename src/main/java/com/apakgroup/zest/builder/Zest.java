package com.apakgroup.zest.builder;

import org.junit.runners.model.FrameworkMethod;

import com.apakgroup.zest.context.DescriptionContext;
import com.apakgroup.zest.context.ScenarioDescriptor;
import com.apakgroup.zest.context.TestDescriptor;
import com.apakgroup.zest.scenario.Scenario;

public class Zest {

    public static Scenario testScenario(final FrameworkMethod method, final Object target,
            final ScenarioDescriptor descriptor) {
        return testScenario(new TestDescriptor(target.getClass().getName(), method.getName(), descriptor));
    }

    public static Scenario testScenario(final ScenarioDescriptor descriptor) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        return testScenario(new TestDescriptor(element.getClassName(), element.getMethodName(), descriptor));
    }

    private static Scenario testScenario(final TestDescriptor descriptor) {
        return new Scenario(new DescriptionContext(descriptor));
    }
}

