package com.messagegears.sdk.model.request;


public class UpdateAccountRequest extends AbstractAccountRequest {
    
    private static final RequestType ACTION = RequestType.UPDATE_ACCOUNT;
    
    @Override
    public RequestType getRequestType() {
        return ACTION;
    }
    
}
