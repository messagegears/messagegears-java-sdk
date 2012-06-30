package com.messagegears.sdk.model.request;

import java.util.Calendar;
import java.util.Date;

public class AccountSummaryRequest extends Request {
    
    private static final RequestType ACTION = RequestType.ACCOUNT_SUMMARY;
    
    private Date date;
    private boolean isMonthly = false;
    
    /**
     * @return The date.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * 
     * @param year The year of the account summary
     * @param month The month of the account summary
     */
    public void setDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        this.date = cal.getTime();
    }

    /**
     * 
     * @param isMonthly set to true if request is for a monthly summary.
     */
    public void setMonthly(boolean isMonthly) {
        this.isMonthly = isMonthly;
    }
    
    /**
     * @return Returns true if request is for a monthly summary
     */
    public boolean isMonthly() {
        return isMonthly;
    }

    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
}
