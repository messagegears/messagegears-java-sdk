package com.messagegears.sdk.aws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CanonicalGrantee;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.Grantee;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.AddPermissionRequest;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;
import com.messagegears.sdk.exception.MessageGearsClientException;

/**
 * Utility class to allow for easy AWS integration. This class
 * allows to easily be uploaded and deleted from S3, as well
 * as managing SQS queue creation and management. Amazon integration
 * is only necessary if consuming from the activity feed or 
 * storing bulk recipient lists on S3. Bulk recipient lists can
 * be stored on any web server as long as they are accessible
 * from either http or https.
 * 
 * @author tjones
 *
 */
public class MessageGearsAwsClient {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageGearsAwsClient.class);

    MessageGearsAwsProperties properties;
    AmazonSQSClient sqs;
    AmazonS3Client s3;
    
    /**
     * Constructor
     * 
     * @param properties The @MessageGearsAwsProperties containing the user credentials.
     */
    public MessageGearsAwsClient (MessageGearsAwsProperties properties) {
        this.properties = properties;
        
        AWSCredentials credentials = new BasicAWSCredentials(properties.getMyAwsAccountKey(), properties.getMyAwsSecretKey());
        
        this.sqs = new AmazonSQSClient(credentials);
        this.s3 = new AmazonS3Client(credentials);
    }
    
    /**
     * Utility method to compress a given file. A new file is created 
     * using the zip algorithm.
     * 
     * @param file The file to be compressed using the zip algorithm.
     * @return A new file created by removing the ".xml" portion of the file name, 
     * and replacing it with ".zip".
     * @throws IOException In the event that a file is not found, or cannot be created.
     */
    public File compressFile(File file) throws IOException {
        String filename = file.getName();
        String compressedFileName = filename.replace(".xml", ".zip");
        File compressedFile = new File(compressedFileName);
        
        FileInputStream inputStream = new FileInputStream(file);
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(compressedFile, false));
        zipOutputStream.putNextEntry(new ZipEntry(filename));
        
        IOUtils.copyLarge(inputStream, zipOutputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(zipOutputStream);
        
        return compressedFile;
    }
    
    /**
     * Utility method to upload a given @InputStream to Amazon S3 in
     * in the user's bucket.
     * 
     * @param inputStream The @InputStream to upload.
     * @param bucketName The Amazon S3 bucket name to upload to.
     * @param key The key to use to identify the file (filename).
     */
    public void putS3File(InputStream inputStream, String bucketName, String key) {
        // Check to see if the file already exists in S3
        ListObjectsRequest listRequest = new ListObjectsRequest().withBucketName(bucketName).withPrefix(key);
        ObjectListing objectListing = listObjectsWithRetry(listRequest);
        if(objectListing.getObjectSummaries().size() > 0) {
            String message = "File " + key + " already exists.";
            LOGGER.warn("putS3File failed: " + message);
            throw new MessageGearsClientException(message);
        }
        
        ObjectMetadata metaData = new ObjectMetadata();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, metaData);
        putWithRetry(putObjectRequest);
        setS3Permission(bucketName, key);
        
        LOGGER.info("putS3File successful: " + key);
    }
    
    /**
     * Utility function to delete a file from the user's S3 Account.
     * 
     * @param bucketName The bucket name containing the file to be deleted.
     * @param key The key identifying the file to be deleted.
     */
    public void deleteS3File (String bucketName, String key) {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, key);
        try {
            deleteWithRetry(deleteObjectRequest);
            LOGGER.info("deleteS3File successful: " + bucketName + "/" + key);
        } catch (AmazonClientException ace) {
            LOGGER.warn("Failed to delete file from S3: " + bucketName + "/" + key);
        }
    }
    
    /**
     * Utility function to create an Amazon SQS queue for use by the 
     * MessageGears platform.
     * 
     * @param queueName A @String containing the queue name to create.
     * @return A @String containing the url to the newly created queue.
     */
    public String createQueue(String queueName) {
        CreateQueueRequest request = new CreateQueueRequest()
            .withQueueName(queueName)
            .withDefaultVisibilityTimeout(properties.getSqsVisibilityTimeoutSecs());

        CreateQueueResult response = sqs.createQueue(request);
        
        addQueuePermission(response.getQueueUrl());
        setMaximumMessageSize(response.getQueueUrl());
        
        LOGGER.info("Create queue successful: " + queueName);
        
        return response.getQueueUrl();
    }
    
    /**
     * Utility function to pull a message from a given SQS Queue (specified in
     * the request).
     * 
     * @param request A @ReceiveMessageRequest specifying which queue to pull from.
     * @return A @ReceiveMessageResult containing a message if available.
     */
    public ReceiveMessageResult receiveMessage(ReceiveMessageRequest request) {
        return sqs.receiveMessage(request);
    }
    
    /**
     * Utility function to delete a message from the user's SQS Queue.
     * 
     * @param request The @DeleteMessageRequest to process.
     */
    public void deleteSqsMessage (DeleteMessageRequest request) {
        sqs.deleteMessage(request);
    }
    
    private void setS3Permission(String bucketName, String key) {
        AccessControlList acl = new AccessControlList();
        acl.setOwner(s3.getS3AccountOwner());
        Grantee grantee = new CanonicalGrantee(properties.getMessageGearsAwsCanonicalId());
        acl.grantPermission(grantee, Permission.Read);
        s3.setObjectAcl(bucketName, key, acl);
    }
    
    private void setMaximumMessageSize(String queueUrl) {
        Map<String, String> attributes = new HashMap<String, String>();
        attributes.put("MaximumMessageSize", "65536");
        SetQueueAttributesRequest request = new SetQueueAttributesRequest().withQueueUrl(queueUrl).withAttributes(attributes);
        sqs.setQueueAttributes(request);
    }
    
    private void addQueuePermission(String queueUrl) {
        AddPermissionRequest permissionRequest = new AddPermissionRequest()
            .withActions("SendMessage")
            .withAWSAccountIds(properties.getMessageGearsAwsAccountId())
            .withLabel("MessageGears Send Permission")
            .withQueueUrl(queueUrl);
    
        sqs.addPermission(permissionRequest);
    }
    
    private void putWithRetry(PutObjectRequest request) {
        for (int i=0; i<5; i++) {
            try {
                s3.putObject(request);
                LOGGER.info("Successfully uploaded file to S3: " + request.toString());
                return;
            } catch (AmazonClientException ace) {
                LOGGER.debug("Failed to upload file to S3: " + ace.getMessage(), ace);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    throw new AmazonClientException("Failed to upload file, thread interrupted during retry.");
                }
            } catch (Exception e) {
                LOGGER.debug("Failed to upload file to S3: " + e.getMessage(), e);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    throw new AmazonClientException("Failed to upload file, thread interrupted during retry.");
                }
            }
        }
        throw new AmazonClientException("Failed to upload file to S3.");
    }
    
    private void deleteWithRetry(DeleteObjectRequest request) {
        for (int i=0; i<5; i++) {
            try {
                s3.deleteObject(request);
                LOGGER.info("Successfully deleted file on S3: " + request.toString());
                return;
            } catch (AmazonClientException ace) {
                LOGGER.debug("Failed to delete file from S3: " + ace.getMessage(), ace);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    throw new AmazonClientException("Failed to delete file, thread interrupted during retry.");
                }
            } catch (Exception e) {
                LOGGER.debug("Failed to delete file from S3: " + e.getMessage(), e);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    throw new AmazonClientException("Failed to delete file, thread interrupted during retry.");
                }
            }
        }
        throw new AmazonClientException("Failed to delete file from S3.");
    }
    
    private ObjectListing listObjectsWithRetry(ListObjectsRequest request) {
        for (int i=0; i<5; i++) {
            try {
                return s3.listObjects(request);
            } catch (AmazonClientException ace) {
                LOGGER.debug("Failed to list objects from S3: " + ace.getMessage(), ace);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    throw new AmazonClientException("Failed to list objects, thread interrupted during retry.");
                }
            } catch (Exception e) {
                LOGGER.debug("Failed to list objects from S3: " + e.getMessage(), e);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    throw new AmazonClientException("Failed to list objects, thread interrupted during retry.");
                }
            }
        }
        throw new AmazonClientException("Failed to list objects from S3.");
    }
}
