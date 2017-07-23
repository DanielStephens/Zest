package com.apakgroup.zest.builder;

import java.util.List;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import com.apakgroup.recall.setup.Recall;
import com.apakgroup.zest.context.DescriptionContext;

public class TestRunner {

    private final Statement wrappedStatement;

    public TestRunner(final Recall recall, final DescriptionContext descriptor, final List<TestRule> rules) {
        Statement baseStatement = new TestStatement(recall);
        Description description = descriptor.asDescription();
        wrappedStatement = wrap(baseStatement, description, rules);
    }

    public void test() throws Throwable {
        wrappedStatement.evaluate();
    }

    private Statement wrap(final Statement base, final Description description, final List<TestRule> rules) {
        Statement statement = base;

        for (TestRule rule : rules) {
            statement = rule.apply(statement, description);
        }

        return statement;
    }

}

