package com.messagegears.sdk.model.request;

public abstract class BaseJobRequest extends Request {

    private String subaccountId;
    private String notificationEmailAddress;
    private String correlationId;
	
    /**
     * @return the subaccountId
     */
    public String getSubaccountId() {
        return subaccountId;
    }
    
    /**
     * @param subaccountId the subaccountId to set
     */
    public void setSubaccountId(String subaccountId) {
        this.subaccountId = subaccountId;
    }
    
    
    /**
     * @return the notificationEmailAddress
     */
    public String getNotificationEmailAddress() {
        return notificationEmailAddress;
    }
    
    /**
     * @param notificationEmailAddress the notificationEmailAddress to set
     */
    public void setNotificationEmailAddress(String notificationEmailAddress) {
        this.notificationEmailAddress = notificationEmailAddress;
    }
    
    
    /**
     * @return the correlationId
     */
    public String getCorrelationId() {
        return correlationId;
    }
    
    /**
     * @param correlationId the correlationId to set
     */
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
