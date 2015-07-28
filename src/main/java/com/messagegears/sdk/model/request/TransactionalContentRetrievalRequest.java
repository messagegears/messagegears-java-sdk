package com.messagegears.sdk.model.request;

public class TransactionalContentRetrievalRequest extends Request {

    private static final RequestType ACTION = RequestType.TRANSACTIONAL_CONTENT_RETRIEVAL;
    
    private String originalRequestId;
    
    public String getOriginalRequestId() {
        return originalRequestId;
    }

    public void setOriginalRequestId(String originalRequestId) {
        this.originalRequestId = originalRequestId;
    }
    
    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
}
