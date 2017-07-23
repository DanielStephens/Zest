package com.apakgroup.zest.rule;

import static java.lang.String.format;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.runners.model.Statement;

public class ExpectedExceptionStatement extends Statement {

    private final String missingExceptionMessage;

    private Matcher<Throwable> exceptionMatcher = neverMatching();

    private final Statement statement;

    private boolean wasCalled = false;

    public ExpectedExceptionStatement(final Matcher<Throwable> exceptionMatcher, final Statement statement,
            final String message) {
        this.exceptionMatcher = exceptionMatcher;
        this.statement = statement;
        this.missingExceptionMessage = message;
    }

    @Override
    public void evaluate() throws Throwable {
        try {
            statement.evaluate();
        } catch (Throwable e) {
            handleException(e);
            evaluate();
        }

        if (!wasCalled) {
            failDueToMissingException();
        }
    }

    private void handleException(final Throwable e) throws Throwable {
        try {
            assertThat(e, exceptionMatcher);
            wasCalled = true;
        } catch (Throwable e2) {
            throw e;
        }
    }

    private void failDueToMissingException() throws AssertionError {
        fail(missingExceptionMessage());
    }

    private String missingExceptionMessage() {
        String expectation = StringDescription.toString(exceptionMatcher);
        return format(missingExceptionMessage, expectation);
    }

    private static <T> Matcher<T> neverMatching() {
        return CoreMatchers.not(ExpectedExceptionStatement.<T> alwaysMatching());
    }

    private static <T> Matcher<T> alwaysMatching() {
        return new BaseMatcher<T>() {

            @Override
            public boolean matches(final Object item) {
                return true;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText(" matching");
            }
        };
    }
}

