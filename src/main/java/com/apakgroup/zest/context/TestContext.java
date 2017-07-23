package com.apakgroup.zest.context;

import java.util.ArrayList;
import java.util.List;

import org.junit.rules.TestRule;

import com.apakgroup.recall.defaults.DefaultResolver;
import com.apakgroup.recall.setup.Recall;
import com.apakgroup.zest.builder.TestRunner;

public class TestContext {

    private final List<TestRule> applicableRules;

    private final Recall recall;

    private final DescriptionContext descriptor;

    public TestContext(final DescriptionContext descriptor) {
        this.applicableRules = new ArrayList<>();
        this.recall = new Recall();
        this.descriptor = descriptor;
    }

    public void addRule(final TestRule rule) {
        applicableRules.add(rule);
    }

    public <T> T enhance(final T object) {
        return recall.enhance(object);
    }

    public <T> T enhanceClass(final Class<T> clazz, final DefaultResolver defaultResolver) {
        return recall.enhanceClass(clazz, defaultResolver);
    }

    public TestRunner buildRunner() {
        return new TestRunner(recall, descriptor, applicableRules);
    }

}

