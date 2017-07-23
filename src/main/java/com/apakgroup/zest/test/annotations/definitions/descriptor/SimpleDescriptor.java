package com.apakgroup.zest.test.annotations.definitions.descriptor;

import org.apache.logging.log4j.message.FormattedMessageFactory;
import org.apache.logging.log4j.message.MessageFactory;

public class SimpleDescriptor implements Descriptor {

	private final MessageFactory messageFactory = new FormattedMessageFactory();

	@Override
	public String describe(String format, String[] params) {
		return messageFactory.newMessage(format, params).getFormattedMessage();
	}

}
