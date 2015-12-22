package com.messagegears.sdk.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.messagegears.sdk.MessageGearsErrorHandler;

public class MessageGearsDefaultErrorHandler implements MessageGearsErrorHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageGearsDefaultErrorHandler.class);

	public void handleUnprocessedMessage(String messageBody, Exception e) {
		LOGGER.warn("Unable to process message: '{}'", messageBody, e);
		LOGGER.warn("This message type may not be supported by your current SDK version.");
	}
	
	public void handleUnprocessedMessage(String messageBody) {
		LOGGER.warn("Unable to process message: '{}'", messageBody);
		LOGGER.warn("This message type may not be supported by your current SDK version.");
	}

}
