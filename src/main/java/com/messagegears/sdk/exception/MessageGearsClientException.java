package com.messagegears.sdk.exception;

public class MessageGearsClientException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MessageGearsClientException(Exception exception) {
        super(exception);
    }

    public MessageGearsClientException(String message) {
        super(message);
    }

    public MessageGearsClientException(String message, Exception exception) {
        super(message, exception);
    }

}