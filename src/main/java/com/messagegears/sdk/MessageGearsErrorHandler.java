package com.messagegears.sdk;

public interface MessageGearsErrorHandler {
	
	public void handleUnprocessedMessage(String messageBody, Exception e);
	
	public void handleUnprocessedMessage(String messageBody);
	
}
