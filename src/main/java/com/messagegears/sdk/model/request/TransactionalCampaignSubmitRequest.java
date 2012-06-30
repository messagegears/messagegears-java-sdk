package com.messagegears.sdk.model.request;

public class TransactionalCampaignSubmitRequest extends BaseJobRequest {

    private static final RequestType ACTION = RequestType.TRANSACTIONAL_CAMPAIGN_SUBMIT;
    
    private long campaignId;
    private String recipientXml;
    
    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public long getCampaignId() {
        return campaignId;
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
