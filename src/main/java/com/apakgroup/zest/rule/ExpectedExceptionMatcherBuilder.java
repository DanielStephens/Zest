package com.apakgroup.zest.rule;

import static org.hamcrest.CoreMatchers.allOf;
import static org.junit.matchers.JUnitMatchers.isThrowable;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.rules.ExpectedException;

/**
 * Builds special matcher used by {@link ExpectedException}.
 */
public class ExpectedExceptionMatcherBuilder {

    private final List<Matcher<?>> matchers = new ArrayList<>();

    void add(final Matcher<?> matcher) {
        matchers.add(matcher);
    }

    boolean expectsThrowable() {
        return !matchers.isEmpty();
    }

    Matcher<Throwable> build() {
        return isThrowable(allOfTheMatchers());
    }

    private Matcher<Throwable> allOfTheMatchers() {
        if (matchers.size() == 1) {
            return cast(matchers.get(0));
        }
        return allOf(castedMatchers());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private List<Matcher<? super Throwable>> castedMatchers() {
        return new ArrayList<>((List) matchers);
    }

    @SuppressWarnings("unchecked")
    private Matcher<Throwable> cast(final Matcher<?> singleMatcher) {
        return (Matcher<Throwable>) singleMatcher;
    }
}

