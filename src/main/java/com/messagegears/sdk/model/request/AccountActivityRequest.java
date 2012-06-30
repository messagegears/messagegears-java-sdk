package com.messagegears.sdk.model.request;

import java.util.Date;

import com.messagegears.sdk.model.ActivityType;

public class AccountActivityRequest extends Request {
    
    private static final RequestType ACTION = RequestType.ACCOUNT_ACTIVITY;
    
    private Date date;
    private ActivityType activityType;
    
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the activityType
     */
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * @param activityType the activityType to set
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
}
