package com.messagegears.sdk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.messagegears.sdk.exception.MessageGearsClientException;
import com.messagegears.sdk.exception.MessageGearsServiceException;
import com.messagegears.sdk.model.Attachment;
import com.messagegears.sdk.model.Header;
import com.messagegears.sdk.model.request.AccountActivityRequest;
import com.messagegears.sdk.model.request.AccountSummaryRequest;
import com.messagegears.sdk.model.request.BaseJobRequest;
import com.messagegears.sdk.model.request.BulkCampaignSubmitRequest;
import com.messagegears.sdk.model.request.BulkJobSubmitRequest;
import com.messagegears.sdk.model.request.BulkJobSummaryRequest;
import com.messagegears.sdk.model.request.CreateAccountRequest;
import com.messagegears.sdk.model.request.JobRequest;
import com.messagegears.sdk.model.request.MessagePreviewRequest;
import com.messagegears.sdk.model.request.RestRequestParam;
import com.messagegears.sdk.model.request.ThumbnailRequest;
import com.messagegears.sdk.model.request.TransactionalCampaignSubmitRequest;
import com.messagegears.sdk.model.request.TransactionalJobSubmitRequest;
import com.messagegears.sdk.model.request.UpdateAccountRequest;
import com.messagegears.sdk.v3_1.AccountSummaryResponse;
import com.messagegears.sdk.v3_1.BulkJobSubmitResponse;
import com.messagegears.sdk.v3_1.BulkJobSummaryResponse;
import com.messagegears.sdk.v3_1.CreateAccountResponse;
import com.messagegears.sdk.v3_1.MessagePreviewResponse;
import com.messagegears.sdk.v3_1.ThumbnailResponse;
import com.messagegears.sdk.v3_1.TransactionalJobSubmitResponse;
import com.messagegears.sdk.v3_1.UpdateAccountResponse;

/**
 * The MessageGears Java API implementation.
 * 
 * This class is designed to easily allow integration with the 
 * MessageGears API. 
 * 
 * @author tjones
 *
 */
public class MessageGearsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageGearsClient.class);
    
    private static final SimpleDateFormat activityDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat activityDateMonthlyFormat = new SimpleDateFormat("yyyy-MM");
    
    private MessageGearsProperties properties;

    /**
     * Constructs a new MessageGears client using the specified properties.
     * 
     * @param properties The account credentials and any other items of data required to use the service.
     */
    public MessageGearsClient(MessageGearsProperties properties) {
        this.properties = properties;
    }

    public CreateAccountResponse createAccount (CreateAccountRequest request) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        // Add the account name
        addParam(params, RestRequestParam.NAME, request.getName());
        // Add the auto tracking flag to the request
        addParam(params, RestRequestParam.AUTOTRACK, request.isAutoTrack());
        // Add the URL Append to the request.
        addParam(params, RestRequestParam.URL_APPEND, request.getUrlAppend());
        // Add the Custom Tracking Domain to the request.
        addParam(params, RestRequestParam.CUSTOM_TRACKING_DOMAIN, request.getCustomTrackingDomain());
        // Submit the request
        String xmlResponse = invoke(params);
        // Parse the response
        Reader reader = new StringReader(xmlResponse);
        CreateAccountResponse response;
        try {
            // Unmarshal
            response = CreateAccountResponse.unmarshal(reader);
        } catch (Exception e) {
            throw new MessageGearsClientException(e);
        }

        return response;
    }

    public UpdateAccountResponse updateAccount (UpdateAccountRequest request) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        // Add the account name
        addParam(params, RestRequestParam.NAME, request.getName());
        // Add the auto tracking flag to the request
        addParam(params, RestRequestParam.AUTOTRACK, request.isAutoTrack());
        // Add the URL Append to the request.
        addParam(params, RestRequestParam.URL_APPEND, request.getUrlAppend());
        // Add the Custom Tracking Domain to the request.
        addParam(params, RestRequestParam.CUSTOM_TRACKING_DOMAIN, request.getCustomTrackingDomain());
        // Submit the request
        String xmlResponse = invoke(params);
        // Parse the response
        Reader reader = new StringReader(xmlResponse);
        UpdateAccountResponse response;
        try {
            // Unmarshal
            response = UpdateAccountResponse.unmarshal(reader);
        } catch (Exception e) {
            throw new MessageGearsClientException(e);
        }

        return response;
    }

    public ThumbnailResponse thumbnail (ThumbnailRequest request) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        // Add the image content to the request
        addParam(params, RestRequestParam.CONTENT, request.getContent());
        // Add the image id to the request
        addParam(params, RestRequestParam.IMAGE_ID, request.getImageId());
        // Add the image size to the request.
        addParam(params, RestRequestParam.IMAGE_SIZE, request.getThumbnailSize().name());
        // Submit the request
        String xmlResponse = invoke(params);
        // Parse the response
        Reader reader = new StringReader(xmlResponse);
        ThumbnailResponse response;
        try {
            // Unmarshal
            response = ThumbnailResponse.unmarshal(reader);
        } catch (Exception e) {
            throw new MessageGearsClientException(e);
        }

        return response;
    }
    
    public TransactionalJobSubmitResponse transactionalCampaignSubmit (TransactionalCampaignSubmitRequest request) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        // Add the recipient xml to the request (single recipient only).
        addParam(params, RestRequestParam.RECIPIENT_XML, request.getRecipientXml());
        // Add the standard job request params
        addBaseJobRequestParams(params, request);
        // add the campaign id
        addParam(params, RestRequestParam.CAMPAIGN_ID, Long.toString(request.getCampaignId()));
        // Submit the request
        String xmlResponse = invoke(params);
        // Parse the response
        Reader reader = new StringReader(xmlResponse);
        TransactionalJobSubmitResponse response;
        try {
            // Unmarshal
            response = TransactionalJobSubmitResponse.unmarshal(reader);
        } catch (Exception e) {
            throw new MessageGearsClientException(e);
        }

        return response;
    }

    /**
     * Submits a Transactional Job (to a single recipient) for processing.
     * @param request A @TransactionalJobSubmitRequest
     * @return A @TransactionalJobSubmitResponse
     */
    public TransactionalJobSubmitResponse transactionalJobSubmit (TransactionalJobSubmitRequest request) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        // Add the recipient xml to the request (single recipient only).
        addParam(params, RestRequestParam.RECIPIENT_XML, request.getRecipientXml());
        // Add the standard job request params
        addJobRequestParams(params, request);
        // Submit the request
        String xmlResponse = invoke(params);
        // Parse the response
        Reader reader = new StringReader(xmlResponse);
        TransactionalJobSubmitResponse response;
        try {
            // Unmarshal
            response = TransactionalJobSubmitResponse.unmarshal(reader);
        } catch (Exception e) {
            throw new MessageGearsClientException(e);
        }

        return response;
    }
    
    public BulkJobSubmitResponse bulkJobSubmit (BulkJobSubmitRequest request) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        // Add the recipient list xml url, which points to the location of the recipipent list
        addParam(params, RestRequestParam.RECIPIENT_LIST_XML_URL, request.getRecipientListXmlUrl());
        // Add optional context xml data 
        addParam(params, RestRequestParam.CONTEXT_DATA_XML, request.getContextDataXml());
        // Add the standard job request params
        addJobRequestParams(params, request);
        // Submit the request
        String xmlResponse = invoke(params);
        // Parse the response
        Reader reader = new StringReader(xmlResponse);
        BulkJobSubmitResponse response;
        try {
            // Unmarshal
            response = BulkJobSubmitResponse.unmarshal(reader);
        } catch (Exception e) {
            throw new MessageGearsClientException(e);
        }

        return response;
    }
    
    public BulkJobSubmitResponse bulkCampaignSubmit (BulkCampaignSubmitRequest request) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        // Add the recipient list xml url, which points to the location of the recipipent list
        addParam(params, RestRequestParam.RECIPIENT_LIST_XML_URL, request.getRecipientListXmlUrl());
        // Add optional context xml data 
        addParam(params, RestRequestParam.CONTEXT_DATA_XML, request.getContextDataXml());
        // Add the standard job request params
        addBaseJobRequestParams(params, request);
        // add the campaign id
        addParam(params, RestRequestParam.CAMPAIGN_ID, Long.toString(request.getCampaignId()));
        // Submit the request
        String xmlResponse = invoke(params);
        // Parse the response
        Reader reader = new StringReader(xmlResponse);
        BulkJobSubmitResponse response;
        try {
            // Unmarshal
            response = BulkJobSubmitResponse.unmarshal(reader);
        } catch (Exception e) {
            throw new MessageGearsClientException(e);
        }

        return response;
    }
    
    /**
     * Allows you to submit your message content for rendering without actually sending
     * an email message. This can be used to determine if the template and recipient 
     * data contain any rendering errors. The response also contains a SpamAssassin spam
     * score based on the content of the template. This data can be used to determine if
     * the message content may trigger any spam filtering rules when it is actually sent.
     * 
     * @param request A @MessagePreviewRequest
     * @return A @MessagePreviewResponse
     */
    public MessagePreviewResponse messagePreview (MessagePreviewRequest request) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        // Add the standard job request params
        addJobRequestParams(params, request);
        // Add the recipient xml to the request (single recipient only).
        addParam(params, RestRequestParam.RECIPIENT_XML, request.getRecipientXml());
        // Add optional context xml data
        addParam(params, RestRequestParam.CONTEXT_DATA_XML, request.getContextDataXml());

        // Submit the request
        String xmlResponse = invoke(params);
        // Parse the response
        Reader reader = new StringReader(xmlResponse);
        MessagePreviewResponse response;
        try {
            // Unmarshal
            response = MessagePreviewResponse.unmarshal(reader);
        } catch (Exception e) {
            throw new MessageGearsClientException(e);
        }

        return response;
    }
    
    /**
     * Used to return a summary of total account activity (clicks, bounces, opens, deliveries, etc...) 
     * for a given day.
     * 
     * @param request A @AccountSummaryRequest.
     * @return A @AccountSummaryResponse.
     */
    public AccountSummaryResponse accountSummary (AccountSummaryRequest request) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        
        if(request.isMonthly()) {
            addParam(params, RestRequestParam.ACTIVITY_DATE, activityDateMonthlyFormat.format(request.getDate()));
        } else {
            addParam(params, RestRequestParam.ACTIVITY_DATE, activityDateFormat.format(request.getDate()));
        }
        
        // Submit the request
        String xmlResponse = invoke(params);
        // Parse the response
        Reader reader = new StringReader(xmlResponse);
        AccountSummaryResponse response;
        try {
            // Unmarshal
            response = AccountSummaryResponse.unmarshal(reader);
        } catch (Exception e) {
            throw new MessageGearsClientException(e);
        }

        return response;
    }
    
    /**
     * Used to return a summary of total activity (clicks, bounces, opens, deliveries, etc...) 
     * for a given bulk job by supplying the RequestId.
     * 
     * @param request A @BulkJobSummaryRequest.
     * @return A @BulkJobSummaryResponse.
     */
    public BulkJobSummaryResponse bulkJobSummary (BulkJobSummaryRequest request) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        addParam(params, RestRequestParam.BULK_REQUEST_ID, request.getRequestId());
        addParam(params, RestRequestParam.BULK_CORRELATION_ID, request.getCorrelationId());
        // Submit the request
        String xmlResponse = invoke(params);
        // Parse the response
        Reader reader = new StringReader(xmlResponse);
        BulkJobSummaryResponse response;
        try {
            // Unmarshal
            response = BulkJobSummaryResponse.unmarshal(reader);
        } catch (Exception e) {
            throw new MessageGearsClientException(e);
        }

        return response;
    }
    
    /**
     * Used to download the detailed xml activity report for a given 
     * @ActivityType for a given date. The current date's activity
     * file will not be available until after 8 am the following day.
     * 
     * @param request A @AccountActivityRequest.
     * @return A @AccountActivityResponse.
     */
    public String accountActivity(AccountActivityRequest request) {
        String fileName = properties.getDownloadDirectory() + activityDateFormat.format(request.getDate()) +
                "_" + request.getActivityType() + ".xml";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // Add your account credentials to the request
        addCredentials(params);
        // Add the action param to the request
        addParam(params, RestRequestParam.ACTION, request.getRequestType().getAction());
        addParam(params, RestRequestParam.ACTIVITY_DATE, activityDateFormat.format(request.getDate()));
        addParam(params, RestRequestParam.ACTIVITY_TYPE, request.getActivityType().name());

        LOGGER.info("Starting activity file download for date: " + activityDateFormat.format(request.getDate()));
        // Submit the request
        invoke(params, new File(fileName));
        LOGGER.info("Download complete.");

        return fileName;
    }
    
    private void addJobRequestParams(List<NameValuePair> params, JobRequest request) {
    	addBaseJobRequestParams(params, request);
    	
        addParam(params, RestRequestParam.TEXT_TEMPLATE, request.getTextTemplate());
        addParam(params, RestRequestParam.FROM_ADDRESS, request.getFromAddress());
        addParam(params, RestRequestParam.FROM_NAME, request.getFromName());
        addParam(params, RestRequestParam.SUBJECT_LINE, request.getSubjectLine());
        addParam(params, RestRequestParam.HTML_TEMPLATE, request.getHtmlTemplate());
        
        if (request.getTemplateLanguage() != null) {
            addParam(params, RestRequestParam.TEMPLATE_LANGUAGE, request.getTemplateLanguage().name());
        }
        
        if (request.getCharacterSet() != null) {
            addParam(params, RestRequestParam.CHARACTER_SET, request.getCharacterSet());
        }

        addParam(params, RestRequestParam.REPLY_TO_ADDRESS, request.getReplyToAddress());
        addParam(params, RestRequestParam.ON_BEHALF_OF_ADDRESS, request.getOnBehalfOfAddress());
        addParam(params, RestRequestParam.ON_BEHALF_OF_NAME, request.getOnBehalfOfName());
        addParam(params, RestRequestParam.AUTOTRACK, request.isAutoTrack());
        addParam(params, RestRequestParam.URL_APPEND, request.getUrlAppend());
        addParam(params, RestRequestParam.CUSTOM_TRACKING_DOMAIN, request.getCustomTrackingDomain());
        addParam(params, RestRequestParam.UNSUBSCRIBE_HEADER, request.isUnsubscribeHeader());

        addAttachments(params, request.getAttachments());
        addHeaders(params, request.getHeaders());
    }
    
    private void addBaseJobRequestParams(List<NameValuePair> params, BaseJobRequest request) {     
        addParam(params, RestRequestParam.NOTIFICATION_EMAIL_ADDRESS, request.getNotificationEmailAddress());
        addParam(params, RestRequestParam.CORRELATION_ID, request.getCorrelationId());
        addParam(params, RestRequestParam.JOB_CATEGORY, request.getJobCategory());
    }
    
    private String invoke(List<NameValuePair> params) {
        HttpResponse httpResponse = submitHttpRequest(params);
        String xmlResponse;
        try {
            xmlResponse = IOUtils.toString(httpResponse.getEntity().getContent());
        } catch (IOException e) {
            throw new MessageGearsClientException(e);
        }
        return xmlResponse;
    }
    
    private void invoke(List<NameValuePair> params, File outputFile) {
        try {
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            HttpResponse httpResponse = submitHttpRequest(params);
            IOUtils.copy(httpResponse.getEntity().getContent(), outputStream);
        } catch (FileNotFoundException fnfe) {
            LOGGER.debug("Error accessing the download directory specified in your properties file.");
            throw new MessageGearsClientException("Could not write account activity results.");
        } catch (IOException ioe) {
            LOGGER.debug(ioe.getMessage(), ioe);
            throw new MessageGearsClientException("Could not write account activity results.");
        }
    }
    
    private HttpResponse submitHttpRequest(List<NameValuePair> params) {
        UrlEncodedFormEntity entity;

        try {
            entity = new UrlEncodedFormEntity(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new MessageGearsClientException(e);
        }

        HttpPost httpPost = new HttpPost(properties.getMessageGearsEndPoint());
        httpPost.setEntity(entity);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = null;

        try {
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                throw new MessageGearsServiceException("Http Status Code: " + httpResponse.getStatusLine().getStatusCode() + " " + IOUtils.toString(httpResponse.getEntity().getContent()));
            }
        } catch (IOException e) {
            throw new MessageGearsClientException(e);
        }
        return httpResponse;
    }
    
    private void addCredentials(List<NameValuePair> params) {
        addParam(params, RestRequestParam.ACCOUNT_ID, properties.getMyMessageGearsAccountId());
        addParam(params, RestRequestParam.API_KEY, properties.getMyMessageGearsApiKey());
    }
    
    private void addParam(List<NameValuePair> params, RestRequestParam param, String value) {
        addParam(params, param.getParamName(), value);
    }
    
    private void addParam(List<NameValuePair> params, String param, String value) {
        params.add(new BasicNameValuePair(param, value));
    }
    
    private void addParam(List<NameValuePair> params, RestRequestParam param, boolean value) {
        if (value) {
            params.add(new BasicNameValuePair(param.getParamName(), "true"));
        } else {
            params.add(new BasicNameValuePair(param.getParamName(), "false"));
        }
    }
    
    private void addAttachments(List<NameValuePair> params, List<Attachment> attachments) {
        for (int i = 0; i < attachments.size(); i++) {
            addParam(params, RestRequestParam.ATTACHMENT_URL.getParamName() + "." + Integer.toString(i + 1), attachments.get(i).getUrl());
            addParam(params, RestRequestParam.ATTACHMENT_NAME.getParamName() + "." + Integer.toString(i + 1), attachments.get(i).getName());
            addParam(params, RestRequestParam.ATTACHMENT_CONTENT_TYPE.getParamName() + "." +
                            Integer.toString(i + 1), attachments.get(i).getContentType());
        }
    }
    
    private void addHeaders(List<NameValuePair> params, List<Header> headers) {
        for (int i = 0; i < headers.size(); i++) {
            addParam(params, RestRequestParam.HEADER_NAME.getParamName() + "." + Integer.toString(i + 1), headers.get(i).getName());
            addParam(params, RestRequestParam.HEADER_VALUE.getParamName() + "." + Integer.toString(i + 1), headers.get(i).getValue());
        }
    }
}
