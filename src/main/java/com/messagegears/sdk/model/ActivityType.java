package com.messagegears.sdk.model;

public enum ActivityType {
    BOUNCES("BouncedMessageActivity"),
    CLICKS("ClickActivity"),
    OPENS("OpenActivity"),
    DELIVERIES("DeliveredMessageActivity"),
    RENDER_ERRORS("RenderErrorActivity"),
    JOB_ERRORS("JobErrorActivity"),
    SPAM_COMPLAINTS("SpamComplaintActivity"),
    UNSUBSCRIBES("UnsubActivity"),
    INBOUND_EMAIL_ACTIVITY("InboundEmailActivity"),
    SMS_DELIVERED_ACTIVITY("SmsDeliveryActivity"),
    SMS_DELIVERY_FAILURE_ACTIVITY("SmsDeliveryFailureActivity"),
    SMS_RENDER_ERROR_ACTIVITY("SmsRenderErrorActivity"),
    SMS_INBOUND_ACTIVITY("SmsInboundActivity"),
    REQUEST_ACTIVITY("RequestActivity");
    
    private String xmlTag;
    
    public String getXmlTag() {
        return xmlTag;
    }
    
    private ActivityType (String xmlTag) {
        this.xmlTag = xmlTag;
    }
}
