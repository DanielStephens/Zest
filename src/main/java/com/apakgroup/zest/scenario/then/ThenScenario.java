package com.apakgroup.zest.scenario.then;

import org.assertj.core.api.AbstractAssert;
import org.hamcrest.Matcher;
import org.junit.rules.ExpectedException;

import com.apakgroup.zest.context.TestContext;

public class ThenScenario implements Then {

    private final TestContext recall;

    public ThenScenario(final TestContext recall) {
        this.recall = recall;
    }

    @Override
    public <A extends AbstractAssert<SELF, ACTUAL>, SELF extends AbstractAssert<SELF, ACTUAL>, ACTUAL> ThenThat<A, SELF, ACTUAL> assertUsing(
            final Class<A> asseter) {
        return recall.enhance(new ThenThat<A, SELF, ACTUAL>(asseter));
    }

    @Override
    public <T> void assertThat(final T t, final Matcher<T> matcher) {
        ThenAssert thenAssert = new DefaultThenAssert<>(recall, t, matcher);
        thenAssert.orFailWithDefaultMessage();
    }

    @Override
    public <T> void assertThat(final T t, final Matcher<T> matcher, final String message, final Object... params) {
        ThenAssert thenAssert = new DefaultThenAssert<>(recall, t, matcher);
        thenAssert.orFailWithMessage(message, params);
    }

    @Override
    public void expectException(final Class<? extends Throwable> throwable) {
        ExpectedException rule = ExpectedException.none();
        rule.expect(throwable);
        recall.addRule(rule);
    }

    @Override
    public void expectExceptionWithCause(final Matcher<? extends Throwable> expectedCause) {
        ExpectedException rule = ExpectedException.none();
        rule.expectCause(expectedCause);
        recall.addRule(rule);
    }

    @Override
    public void expectExceptionWithMessage(final String substring) {
        ExpectedException rule = ExpectedException.none();
        rule.expectMessage(substring);
        recall.addRule(rule);
    }

    @Override
    public void expectExceptionWithMessage(final Matcher<String> matcher) {
        ExpectedException rule = ExpectedException.none();
        rule.expectMessage(matcher);
        recall.addRule(rule);
    }

}

