package com.messagegears.sdk.exception;

public class MessageGearsServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public MessageGearsServiceException(Exception exception) {
        super(exception);
    }

    public MessageGearsServiceException(String message) {
        super(message);
    }

    public MessageGearsServiceException(String message, Exception exception) {
        super(message, exception);
    }

}
