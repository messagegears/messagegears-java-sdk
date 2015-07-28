package com.messagegears.sdk;

import java.io.File;

/**
 * Utility class containing the MessageGears properties 
 * items that are used by the @MessageGearsClient. The
 * user must supply their MessageGears Account Id and API
 * Key.
 * 
 * @author tjones
 *
 */
public class MessageGearsProperties {

    private String messageGearsEndPoint = "http://localhost:8080/3.1/WebService";
    private String myMessageGearsAccountId;
    private String myMessageGearsApiKey;
    private String downloadDirectory = "./";
    
    /**
     * @return the messageGearsEndPoint
     */
    public String getMessageGearsEndPoint() {
        return messageGearsEndPoint;
    }
    
    /**
     * @param messageGearsEndPoint the messageGearsEndPoint to set
     */
    public void setMessageGearsEndPoint(String messageGearsEndPoint) {
        this.messageGearsEndPoint = messageGearsEndPoint;
    }
    
    /**
     * @return the myMessageGearsAccountId
     */
    public String getMyMessageGearsAccountId() {
        return myMessageGearsAccountId;
    }
    
    /**
     * @param myMessageGearsAccountId the myMessageGearsAccountId to set
     */
    public void setMyMessageGearsAccountId(String myMessageGearsAccountId) {
        this.myMessageGearsAccountId = myMessageGearsAccountId;
    }
    
    /**
     * @return the myMessageGearsApiKey
     */
    public String getMyMessageGearsApiKey() {
        return myMessageGearsApiKey;
    }
    
    /**
     * @param myMessageGearsApiKey the myMessageGearsApiKey to set
     */
    public void setMyMessageGearsApiKey(String myMessageGearsApiKey) {
        this.myMessageGearsApiKey = myMessageGearsApiKey;
    }
    
    /**
     * @return the downloadDirectory
     */
    public String getDownloadDirectory() {
        return downloadDirectory;
    }
    
    /**
     * @param downloadDirectory the downloadDirectory to set
     */
    public void setDownloadDirectory(String downloadDirectory) {
        this.downloadDirectory = downloadDirectory;
        if (!downloadDirectory.endsWith(File.separator)) {
            this.downloadDirectory += File.separator;
        }
    }
    
    @Override
    public String toString() {
        return "MessageGearsProperties { MessageGearsEndPoint = '" + messageGearsEndPoint +
            "', MyMessageGearsAccountId = '" + myMessageGearsAccountId +
            "', MyMessageGearsApiKey = '" + myMessageGearsApiKey + 
            "', DownloadDirectory = '" + downloadDirectory + "' }";
    }
    
}
