package com.messagegears.sdk.model;

public enum ActivityType {
    BOUNCES("BouncedMessageActivity"),
    CLICKS("ClickActivity"),
    OPENS("OpenActivity"),
    DELIVERIES("DeliveredMessageActivity"),
    RENDER_ERRORS("RenderErrorActivity"),
    JOB_ERRORS("JobErrorActivity"),
    SPAM_COMPLAINTS("SpamComplaintActivity"),
    UNSUBSCRIBES("UnsubActivity");
    
    private String xmlTag;
    
    public String getXmlTag() {
        return xmlTag;
    }
    
    private ActivityType (String xmlTag) {
        this.xmlTag = xmlTag;
    }
}
