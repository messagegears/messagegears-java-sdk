package com.messagegears.sdk.model.request;

public class BulkCampaignSubmitRequest extends BaseJobRequest {
    
    private static final RequestType ACTION = RequestType.BULK_CAMPAIGN_SUBMIT;
    
    private String recipientListXmlUrl;
    private String contextDataXml;
    private long campaignId;

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
    
    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public long getCampaignId() {
        return campaignId;
    }
    
    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
}
