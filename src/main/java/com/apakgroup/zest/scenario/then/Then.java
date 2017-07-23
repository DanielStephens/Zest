package com.apakgroup.zest.scenario.then;

import org.assertj.core.api.AbstractAssert;
import org.hamcrest.Matcher;

public interface Then {

    <A extends AbstractAssert<SELF, ACTUAL>, SELF extends AbstractAssert<SELF, ACTUAL>, ACTUAL> ThenUseAssertJ<A, SELF, ACTUAL> assertUsing(
            Class<A> asseter);

    <T> void assertThat(T t, Matcher<T> matcher);

    <T> void assertThat(T t, Matcher<T> matcher, String message, Object... params);

    void expectException(Class<? extends Throwable> throwable);

    void expectExceptionWithCause(Matcher<? extends Throwable> expectedCause);

    void expectExceptionWithMessage(String substring);

    void expectExceptionWithMessage(Matcher<String> matcher);

}

