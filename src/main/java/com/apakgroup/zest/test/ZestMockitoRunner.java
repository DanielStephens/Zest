package com.apakgroup.zest.test;

import java.lang.reflect.InvocationTargetException;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.Filterable;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.mockito.internal.runners.RunnerImpl;

import com.apakgroup.zest.test.filters.MockitoFilter;

public class ZestMockitoRunner extends Runner implements Filterable {

    private final RunnerImpl runner;

    public ZestMockitoRunner(final Class<?> klass) throws InvocationTargetException, InitializationError {
        runner = new MockitoFilter(klass);
    }

    @Override
    public void run(final RunNotifier notifier) {
        runner.run(notifier);
    }

    @Override
    public Description getDescription() {
        return runner.getDescription();
    }

    @Override
    public void filter(final Filter filter) throws NoTestsRemainException {
        runner.filter(filter);
    }

}

