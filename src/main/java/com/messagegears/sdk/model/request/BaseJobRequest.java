package com.messagegears.sdk.model.request;

public abstract class BaseJobRequest extends Request {

    private String notificationEmailAddress;
    private String correlationId;
    private String jobCategory;
	
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
    
    /**
     * @return the jobCategory
     */
    public String getJobCategory() {
        return jobCategory;
    }
    
    /**
     * @param jobCategory the jobCategory to set
     */
    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }
}
