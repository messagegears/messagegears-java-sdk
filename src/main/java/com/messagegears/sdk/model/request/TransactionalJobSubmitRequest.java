package com.messagegears.sdk.model.request;

public class TransactionalJobSubmitRequest extends JobRequest {
    
    private static final RequestType ACTION = RequestType.TRANSACTIONAL_SUBMIT;

    private String contextDataXml;
    private String recipientXml;

    public String getContextDataXml() {
        return contextDataXml;
    }

    public void setContextDataXml(String contextDataXml) {
        this.contextDataXml = contextDataXml;
    }

    public void setRecipientXml(String recipientXml) {
        this.recipientXml = recipientXml;
    }

    public String getRecipientXml() {
        return recipientXml;
    }
    
    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
}
