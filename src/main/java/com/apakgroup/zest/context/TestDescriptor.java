package com.apakgroup.zest.context;


public class TestDescriptor implements ScenarioDescriptor {

    private final String className;

    private final String methodName;

    private final ScenarioDescriptor descriptor;

    public TestDescriptor(final String className, final String methodName, final ScenarioDescriptor descriptor) {
        this.className = className;
        this.methodName = methodName;
        this.descriptor = descriptor;
    }

    public String className() {
        return className;
    }

    public String methodName() {
        return methodName;
    }

    @Override
    public String describe() {
        return descriptor.describe();
    }

}

