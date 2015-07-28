package com.messagegears.sdk.model.request;

public enum RequestType {
    ACCOUNT_ACTIVITY("AccountActivity", "Retrieve your account's activity file for a given date."),
    ACCOUNT_SUMMARY("AccountSummary", "Retrieve your account's activity summary for a given date."),
    BULK_JOB_SUBMIT("BulkJobSubmit", "Submit a job to multiple recipients."),
    BULK_CAMPAIGN_SUBMIT("BulkCampaignSubmit", "Submit a job to multiple recipients using an existing campaign."),
    BULK_JOB_SUMMARY("BulkJobSummary", "Retrieve the activity summary for a particular bulk job."),
    CREATE_ACCOUNT("CreateAccount", "Create a new account."),
    MESSAGE_PREVIEW("MessagePreview", "Preview a particular template as it will be rendered."),
    TRANSACTIONAL_SUBMIT("TransactionalJobSubmit", "Submit a job to a single recipient."),
    TRANSACTIONAL_CAMPAIGN_SUBMIT("TransactionalCampaignSubmit", "Submit a job to a single recipient using an existing campaign."),
    THUMBNAIL("Thumbnail", "Create a thumbnail image file for an HTML or Text string."),
    UPDATE_ACCOUNT("UpdateAccount", "Update attributes of an existing account by specifying the accountId."),
    TRANSACTIONAL_CONTENT_RETRIEVAL("TransactionalContentRetrieval", "Retrieve the rendered content for a particular transactional job or campaign."),
    ;
    
    private String action;
    private String description;
    
    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    private RequestType (String action, String description) {
         this.action = action;
         this.description = description;
    }
}
