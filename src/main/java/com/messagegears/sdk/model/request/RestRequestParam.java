package com.messagegears.sdk.model.request;

public enum RestRequestParam {
    ACTION("Action", "Action to invoke."),
    ACCOUNT_ID("AccountId", "Your MessageGears Account Id."),
    ACTIVITY_DATE("ActivityDate", "The date to retrieve activity for."),
    ACTIVITY_TYPE("ActivityType", "The type of activity requested."),
    API_KEY("ApiKey", "Your MessageGears API Key."),
    ATTACHMENT_NAME("AttachmentName", "The file name of the attachment."),
    ATTACHMENT_CONTENT_TYPE("AttachmentContentType", "The content type to specify with the attachement."),
    ATTACHMENT_URL("AttachmentUrl", "The url of the attachment file."),
    ATTACHMENT_CONTENT("AttachmentContent", "The Base64 string encoded in line attachment content."),
    AUTOTRACK("AutoTrack", "Specify whether to autotrack links found in the HtmlTemplate."),
    BULK_CORRELATION_ID("BulkJobCorrelationId", "The BulkJobCorrelationId"),
    BULK_REQUEST_ID("BulkJobRequestId", "The BulkJobRequestId."),
    CAMPAIGN_ID("CampaignId", "The Campaign Id of the template to use."),
    CORRELATION_ID("CorrelationId", "Specify a correlation Id to help map this job back to your system."),
    CUSTOM_TRACKING_DOMAIN("CustomTrackingDomain", "The custom tracking domain specified in trackable links and unsubscribes."),
    CHARACTER_SET("CharacterSet", "The character set of the message. Defaults to UTF-8"),
    FROM_ADDRESS("FromAddress", "The from address for the message. This populates the From header."),
    FROM_NAME("FromName", "The from name for the message. This appears along with the From address."),
    HEADER_NAME("HeaderName", "The name of the header to be included in the message."),
    HEADER_VALUE("HeaderValue", "The value of the header to be included in the message."),
    HTML_TEMPLATE("HtmlTemplate", "The html template content of the message."),
    JOB_CATEGORY("JobCategory", "Specify a job category to help map this request back to your system."),
    NAME("Name","The name of the subaccount being referenced."),
    NOTIFICATION_EMAIL_ADDRESS("NotificationEmailAddress", "The email address that JobError notifications will be sent to."),
    RECIPIENT_LIST_XML_URL("RecipientListXmlUrl", "The URL to the recipient list to be used with a bulk job submit."),
    RECIPIENT_XML("RecipientXml", "The receipient xml portion of the message."),
    CONTEXT_DATA_XML("ContextDataXml", "The optional context xml."),
    REQUEST_ID("RequestId", "The RequestId to retrieve activity for."),
    ON_BEHALF_OF_ADDRESS("OnBehalfOfAddress", "Populates the OnBehalfOfAddress header."),
    ON_BEHALF_OF_NAME("OnBehalfOfName", "Populates the OnBehalfOfName header."),
    REPLY_TO_ADDRESS("ReplyToAddress", "The reply to address of the message."),
    UPDATE_ACCOUNT_ID("Id", "The AccountId to update for this request."),
    SUBJECT_LINE("SubjectLine", "The subject line of the message."),
    TEMPLATE_LANGUAGE("TemplateLanguage","The template language to render the message with. Defaults to FREEMARKER."),
    TEMPLATE_LIBRARY("TemplateLibrary","The optional inline global template library to render the message with. Only supported by FREEMARKER."),
    TEXT_TEMPLATE("TextTemplate", "The text template content of the message."),
    UNSUBSCRIBE_HEADER("UnsubscribeHeader", "Specifies whether messages should include the unsubscribe header."),
    URL_APPEND("UrlAppend", "One or more name=value pairs that will be included with each autotracked link."),
    CONTENT("Content","The string of data to be used to render a thumbnail image."),
    IMAGE_ID("ImageId", "The image id to be used to name the thumbnail image."),
    IMAGE_SIZE("ImageSize", "The size of the thumbnail image to be generated."),
    ORIGINAL_REQUEST_ID("OriginalRequestId", "The request id of the transactional job or campaign"),
    ;
    
    private String paramName;
    private String description;
    
    private RestRequestParam(String paramName, String description) {
        this.paramName = paramName;
        this.description = description;
    }

    /**
     * @return the paramName
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * @param paramName the paramName to set
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
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
    
    
}
