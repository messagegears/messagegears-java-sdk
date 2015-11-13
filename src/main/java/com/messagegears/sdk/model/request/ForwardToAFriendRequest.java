package com.messagegears.sdk.model.request;

public class ForwardToAFriendRequest extends BaseJobRequest {
	
	private static final RequestType ACTION = RequestType.FORWARD_TO_A_FRIEND;

    private String recipientToken;
    private String recipientXml;
    private Long campaignId;

	public String getRecipientToken() {
		return recipientToken;
	}

	public void setRecipientToken(String recipientToken) {
		this.recipientToken = recipientToken;
	}

	public String getRecipientXml() {
		return recipientXml;
	}

	public void setRecipientXml(String recipientXml) {
		this.recipientXml = recipientXml;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	@Override
	public RequestType getRequestType() {
		return ACTION;
	}
	
}
