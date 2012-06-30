package com.messagegears.sdk.model.request;


public class CreateAccountRequest extends AbstractAccountRequest {
    
    private static final RequestType ACTION = RequestType.CREATE_ACCOUNT;
    
    @Override
    public RequestType getRequestType() {
        return ACTION;
    }

}
