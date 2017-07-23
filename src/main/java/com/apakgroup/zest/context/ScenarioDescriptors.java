package com.apakgroup.zest.context;

import org.apache.logging.log4j.message.FormattedMessageFactory;
import org.apache.logging.log4j.message.MessageFactory;

public class ScenarioDescriptors {

    private static final MessageFactory MESSAGE_FACTORY = new FormattedMessageFactory();

    private ScenarioDescriptors() {

    }

    public static ScenarioDescriptor from(final String string, final Object... params) {
        return from(MESSAGE_FACTORY.newMessage(string, params).getFormattedMessage());
    }

    public static ScenarioDescriptor from(final String string) {
        return new ScenarioDescriptor() {
            @Override
            public String describe() {
                return string;
            }
        };
    }

}

