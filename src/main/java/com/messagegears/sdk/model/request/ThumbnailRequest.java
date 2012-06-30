package com.messagegears.sdk.model.request;

import com.messagegears.sdk.model.ThumbnailSize;

public class ThumbnailRequest extends Request {
    
    private static final RequestType ACTION = RequestType.THUMBNAIL;
    
    private String imageId;
    private ThumbnailSize thumbnailSize;
    private String content;
    
    @Override
    public RequestType getRequestType() {
        return ACTION;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public ThumbnailSize getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(ThumbnailSize thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
