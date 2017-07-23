package com.apakgroup.zest.rule;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.internal.matchers.ThrowableMessageMatcher.hasMessage;

import org.hamcrest.Matcher;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RollingExpectedExceptionRule implements TestRule {

    private String missingExceptionMessage = "Expected test to throw %s";

    private final ExpectedExceptionMatcherBuilder matcherBuilder = new ExpectedExceptionMatcherBuilder();

    private RollingExpectedExceptionRule() {
    }

    public static RollingExpectedExceptionRule none() {
        return new RollingExpectedExceptionRule();
    }

    public static RollingExpectedExceptionRule withExpectation(final Class<? extends Throwable> e) {
        RollingExpectedExceptionRule rule = new RollingExpectedExceptionRule();
        rule.expect(e);
        return rule;
    }



    /**
     * Specifies the failure message for tests that are expected to throw an exception but do not
     * throw any. You can use a {@code %s} placeholder for the description of the expected
     * exception. E.g. "Test doesn't throw %s." will fail with the error message
     * "Test doesn't throw an instance of foo.".
     *
     * @param message
     *            exception detail message
     * @return the rule itself
     */
    public RollingExpectedExceptionRule reportMissingExceptionWithMessage(final String message) {
        missingExceptionMessage = message;
        return this;
    }

    /**
     * Verify that your code throws an exception that is matched by a Hamcrest matcher.
     * 
     * <pre>
     * 
     * &#064;Test
     * public void throwsExceptionThatCompliesWithMatcher() {
     *     NullPointerException e = new NullPointerException();
     *     thrown.expect(is(e));
     *     throw e;
     * }
     * </pre>
     */
    public void expect(final Matcher<?> matcher) {
        matcherBuilder.add(matcher);
    }

    /**
     * Verify that your code throws an exception that is an instance of specific {@code type}.
     * 
     * <pre>
     * 
     * &#064;Test
     * public void throwsExceptionWithSpecificType() {
     *     thrown.expect(NullPointerException.class);
     *     throw new NullPointerException();
     * }
     * </pre>
     */
    public void expect(final Class<? extends Throwable> type) {
        expect(instanceOf(type));
    }

    /**
     * Verify that your code throws an exception whose message contains a specific text.
     * 
     * <pre>
     * 
     * &#064;Test
     * public void throwsExceptionWhoseMessageContainsSpecificText() {
     *     thrown.expectMessage(&quot;happened&quot;);
     *     throw new NullPointerException(&quot;What happened?&quot;);
     * }
     * </pre>
     */
    public void expectMessage(final String substring) {
        expectMessage(containsString(substring));
    }

    /**
     * Verify that your code throws an exception whose message is matched by a Hamcrest matcher.
     * 
     * <pre>
     * 
     * &#064;Test
     * public void throwsExceptionWhoseMessageCompliesWithMatcher() {
     *     thrown.expectMessage(startsWith(&quot;What&quot;));
     *     throw new NullPointerException(&quot;What happened?&quot;);
     * }
     * </pre>
     */
    public void expectMessage(final Matcher<String> matcher) {
        expect(hasMessage(matcher));
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new ExpectedExceptionStatement(matcherBuilder.build(), base, missingExceptionMessage);
    }

}

