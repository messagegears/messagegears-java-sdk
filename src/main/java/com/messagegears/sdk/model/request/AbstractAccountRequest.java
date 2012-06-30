package com.messagegears.sdk.model.request;


public abstract class AbstractAccountRequest extends Request {
    
    private String name;
    private boolean autoTrack;
    private String urlAppend;
    private String customTrackingDomain;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAutoTrack() {
        return autoTrack;
    }

    public void setAutoTrack(boolean autoTrack) {
        this.autoTrack = autoTrack;
    }

    public String getUrlAppend() {
        return urlAppend;
    }

    public void setUrlAppend(String urlAppend) {
        this.urlAppend = urlAppend;
    }

    public String getCustomTrackingDomain() {
        return customTrackingDomain;
    }

    public void setCustomTrackingDomain(String customTrackingDomain) {
        this.customTrackingDomain = customTrackingDomain;
    }

    
}
