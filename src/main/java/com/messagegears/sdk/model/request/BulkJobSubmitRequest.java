package com.messagegears.sdk.model.request;

public class BulkJobSubmitRequest extends JobRequest {
    
    private static final RequestType ACTION = RequestType.BULK_JOB_SUBMIT;
    
    private String recipientListXmlUrl;
    private String contextDataXml;

    public void setRecipientListXmlUrl(String recipientListXmlUrl) {
        this.recipientListXmlUrl = recipientListXmlUrl;
    }

    public String getRecipientListXmlUrl() {
        return recipientListXmlUrl;
    }
    
    public void setContextDataXml(String contextDataXml) {
        this.contextDataXml = contextDataXml;
    }

    public String getContextDataXml() {
        return contextDataXml;
    }
    
    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
}
