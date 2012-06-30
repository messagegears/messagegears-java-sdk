package com.messagegears.sdk.model.request;

public class MessagePreviewRequest extends JobRequest {

    private static final RequestType ACTION = RequestType.MESSAGE_PREVIEW;

    private String recipientXml;

    private String contextDataXml;

    public void setRecipientXml(String recipientXml) {
        this.recipientXml = recipientXml;
    }

    public String getRecipientXml() {
        return recipientXml;
    }

    /**
     * @return the contextDataXml
     */
    public String getContextDataXml() {
        return contextDataXml;
    }

    /**
     * @param contextDataXml
     *            the contextDataXml to set
     */
    public void setContextDataXml(String contextDataXml) {
        this.contextDataXml = contextDataXml;
    }

    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
}
