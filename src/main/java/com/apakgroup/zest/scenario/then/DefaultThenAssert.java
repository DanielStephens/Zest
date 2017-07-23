package com.apakgroup.zest.scenario.then;

import org.apache.logging.log4j.message.FormattedMessageFactory;
import org.apache.logging.log4j.message.MessageFactory;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

import com.apakgroup.zest.context.TestContext;

public class DefaultThenAssert<T> implements ThenAssert {

    private final T t;
    
    private final Matcher<T> matcher;
    
    private final TestContext recall;

    private final MessageFactory messageFactory;

    public DefaultThenAssert(final TestContext recall, final T t, final Matcher<T> matcher) {
        this.t = t;
        this.matcher = matcher;
        this.recall = recall;
        this.messageFactory = new FormattedMessageFactory();
    }

    @Override
    public void orFailWithMessage(final String message, final Object... params) {
        recall.enhance(new NonStaticMatcherAssert())
                .assertThat(recall.enhance(messageFactory).newMessage(message, params).getFormattedMessage(), t,
                        matcher);
    }

    @Override
    public void orFailWithDefaultMessage() {
        recall.enhance(new NonStaticMatcherAssert()).assertThat(t, matcher);
    }

    public static class NonStaticMatcherAssert {

        public <T> void assertThat(final String reason, final T actual, final Matcher<? super T> matcher) {
            MatcherAssert.assertThat(reason, actual, matcher);
        }

        public <T> void assertThat(final T actual, final Matcher<? super T> matcher) {
            MatcherAssert.assertThat(actual, matcher);
        }
    }

}

