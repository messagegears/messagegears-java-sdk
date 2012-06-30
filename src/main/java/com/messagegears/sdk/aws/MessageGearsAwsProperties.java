package com.messagegears.sdk.aws;

/**
 * Utility class containing the MessageGears AWS properties 
 * items that are used by the @MessageGearsAwsClient. The
 * user must supply their AWS Account Key, AWS SecretKey,
 * and AWS EventQueue Url to use the Amazon AWS functionality.
 * 
 * @author tjones
 *
 */
public class MessageGearsAwsProperties {

    private String myAwsAccountKey;
    private String myAwsSecretKey;
    private String myAwsEventQueueUrl;
    
    private String messageGearsAwsCanonicalId;
    private String messageGearsAwsAccountId;
    
    private int sqsVisibilityTimeoutSecs;
    private int s3PutTimeoutSecs;
    
    public MessageGearsAwsProperties() {
        messageGearsAwsCanonicalId = "2dd8e53f1a8e4dfe3a6893d1229635b4915661d95f5283df75215779ce462819";    
        messageGearsAwsAccountId="406967126799";
        sqsVisibilityTimeoutSecs=600;
        s3PutTimeoutSecs=900;    
    }
  
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" MessageGearsAWSCanonicalId=").append(messageGearsAwsCanonicalId);
        builder.append(" MessageGearsAWSAccountId=").append(messageGearsAwsAccountId);
        builder.append(" MyAWSAccountKey=").append(myAwsAccountKey);
        builder.append(" MyAWSSecretKey=").append("<hidden>");
        builder.append(" SQSVisibilityTimeoutSecs=").append(sqsVisibilityTimeoutSecs);
        builder.append(" S3PutTimeoutSecs=").append(s3PutTimeoutSecs);
        return builder.toString();
    }

    /**
     * @return the myAwsAccountKey
     */
    public String getMyAwsAccountKey() {
        return myAwsAccountKey;
    }

    /**
     * @param myAwsAccountKey the myAwsAccountKey to set
     */
    public void setMyAwsAccountKey(String myAwsAccountKey) {
        this.myAwsAccountKey = myAwsAccountKey;
    }

    /**
     * @return the myAwsSecretKey
     */
    public String getMyAwsSecretKey() {
        return myAwsSecretKey;
    }

    /**
     * @param myAwsSecretKey the myAwsSecretKey to set
     */
    public void setMyAwsSecretKey(String myAwsSecretKey) {
        this.myAwsSecretKey = myAwsSecretKey;
    }

    /**
     * @return the myAwsEventQueueUrl
     */
    public String getMyAwsEventQueueUrl() {
        return myAwsEventQueueUrl;
    }

    /**
     * @param myAwsEventQueueUrl the myAwsEventQueueUrl to set
     */
    public void setMyAwsEventQueueUrl(String myAwsEventQueueUrl) {
        this.myAwsEventQueueUrl = myAwsEventQueueUrl;
    }

    /**
     * @return the messageGearsAwsCanonicalId
     */
    public String getMessageGearsAwsCanonicalId() {
        return messageGearsAwsCanonicalId;
    }

    /**
     * @param messageGearsAwsCanonicalId the messageGearsAwsCanonicalId to set
     */
    public void setMessageGearsAwsCanonicalId(String messageGearsAwsCanonicalId) {
        this.messageGearsAwsCanonicalId = messageGearsAwsCanonicalId;
    }

    /**
     * @return the messageGearsAwsAccountId
     */
    public String getMessageGearsAwsAccountId() {
        return messageGearsAwsAccountId;
    }

    /**
     * @param messageGearsAwsAccountId the messageGearsAwsAccountId to set
     */
    public void setMessageGearsAwsAccountId(String messageGearsAwsAccountId) {
        this.messageGearsAwsAccountId = messageGearsAwsAccountId;
    }

    /**
     * @return the sqsVisibilityTimeoutSecs
     */
    public int getSqsVisibilityTimeoutSecs() {
        return sqsVisibilityTimeoutSecs;
    }

    /**
     * @param sqsVisibilityTimeoutSecs the sqsVisibilityTimeoutSecs to set
     */
    public void setSqsVisibilityTimeoutSecs(int sqsVisibilityTimeoutSecs) {
        this.sqsVisibilityTimeoutSecs = sqsVisibilityTimeoutSecs;
    }

    /**
     * @return the s3PutTimeoutSecs
     */
    public int getS3PutTimeoutSecs() {
        return s3PutTimeoutSecs;
    }

    /**
     * @param s3PutTimeoutSecs the s3PutTimeoutSecs to set
     */
    public void setS3PutTimeoutSecs(int s3PutTimeoutSecs) {
        this.s3PutTimeoutSecs = s3PutTimeoutSecs;
    }
}
