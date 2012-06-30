package com.messagegears.sdk.model.request;

public class BulkJobSummaryRequest extends Request {

    private static final RequestType ACTION = RequestType.BULK_JOB_SUMMARY;
    
    private String requestId;
    private String correlationId;
    
    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
}
