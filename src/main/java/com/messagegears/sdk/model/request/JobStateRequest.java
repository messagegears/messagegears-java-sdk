package com.messagegears.sdk.model.request;

public class JobStateRequest extends Request {

    private static final RequestType ACTION = RequestType.JOB_STATE;
    
    private Integer status;
    private String jobRequestId;
    
    public String getJobRequestId() {
        return jobRequestId;
    }

    public void setJobRequestId(String jobRequestId) {
        this.jobRequestId = jobRequestId;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
}
