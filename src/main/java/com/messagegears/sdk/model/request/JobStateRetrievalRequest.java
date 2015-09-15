package com.messagegears.sdk.model.request;

public class JobStateRetrievalRequest extends Request {

    private static final RequestType ACTION = RequestType.JOB_STATE_RETRIEVAL;
    
    private String jobRequestId;
    
    public String getJobRequestId() {
        return jobRequestId;
    }

    public void setJobRequestId(String jobRequestId) {
        this.jobRequestId = jobRequestId;
    }
    
    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
}
