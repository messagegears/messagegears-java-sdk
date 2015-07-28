package com.messagegears.sdk.output;

import com.messagegears.sdk.v3_1.AccountSummaryResponse;
import com.messagegears.sdk.v3_1.Attachment;
import com.messagegears.sdk.v3_1.BouncedMessageActivity;
import com.messagegears.sdk.v3_1.BulkJobError;
import com.messagegears.sdk.v3_1.BulkJobSubmitResponse;
import com.messagegears.sdk.v3_1.BulkJobSummaryResponse;
import com.messagegears.sdk.v3_1.ClickActivity;
import com.messagegears.sdk.v3_1.CreateAccountResponse;
import com.messagegears.sdk.v3_1.DeliveredMessageActivity;
import com.messagegears.sdk.v3_1.JobErrorActivity;
import com.messagegears.sdk.v3_1.MessagePreviewResponse;
import com.messagegears.sdk.v3_1.OpenActivity;
import com.messagegears.sdk.v3_1.RenderError;
import com.messagegears.sdk.v3_1.RenderErrorActivity;
import com.messagegears.sdk.v3_1.RequestError;
import com.messagegears.sdk.v3_1.RequestErrors;
import com.messagegears.sdk.v3_1.SpamAssassinRule;
import com.messagegears.sdk.v3_1.SpamComplaintActivity;
import com.messagegears.sdk.v3_1.ThumbnailResponse;
import com.messagegears.sdk.v3_1.TransactionalContent;
import com.messagegears.sdk.v3_1.TransactionalJobSubmitResponse;
import com.messagegears.sdk.v3_1.UnsubActivity;
import com.messagegears.sdk.v3_1.UpdateAccountResponse;
import com.messagegears.sdk.v3_1.types.ResultType;

public class ScreenWriter {

    public static void print(OpenActivity activity) {
        System.out.println("***** Open Event Received");
        System.out.println("getActivityId(): " + activity.getActivityId());
        System.out.println("getCorrelationId(): " + activity.getCorrelationId());
        System.out.println("getEmailAddress(): " + activity.getEmailAddress());
        System.out.println("getIpAddress(): " + activity.getIpAddress());
        System.out.println("getRecipientId(): " + activity.getRecipientId());
        System.out.println("getRequestId(): " + activity.getRequestId());
        System.out.println("getTimestamp(): " + activity.getTimestamp());
        System.out.println("getUserAgent(): " + activity.getUserAgent());
        System.out.println();
    }
    
    public static void print(ClickActivity activity) {
        System.out.println("***** Click Event Received: " + activity.getActivityId());
        System.out.println("getActivityId(): " + activity.getActivityId());
        System.out.println("getCorrelationId(): " + activity.getCorrelationId());
        System.out.println("getEmailAddress(): " + activity.getEmailAddress());
        System.out.println("getIpAddress(): " + activity.getIpAddress());
        System.out.println("getRecipientId(): " + activity.getRecipientId());
        System.out.println("getRequestId(): " + activity.getRequestId());
        System.out.println("getTimestamp(): " + activity.getTimestamp());
        System.out.println("Url: " + activity.getUrl());
        System.out.println("UrlName: " + activity.getUrlName());
        System.out.println("getUserAgent(): " + activity.getUserAgent());
        System.out.println();
    }
    
    public static void print(BouncedMessageActivity activity) {
        System.out.println("***** Bounce Event Received");
        System.out.println("getActivityId(): " + activity.getActivityId());
        System.out.println("Category: " + activity.getCategory());
        System.out.println("CategoryCode: " + activity.getCategoryCode());
        System.out.println("getCorrelationId(): " + activity.getCorrelationId());
        System.out.println("Details: " + activity.getDetails());
        System.out.println("getEmailAddress(): " + activity.getEmailAddress());
        System.out.println("getIpAddress(): " + activity.getIpAddress());
        System.out.println("MessageSize: " + activity.getMessageSize());
        System.out.println("getRecipientId(): " + activity.getRecipientId());
        System.out.println("getRequestId(): " + activity.getRequestId());
        System.out.println("getTimestamp(): " + activity.getTimestamp());
        System.out.println();
    }
    
    public static void print(DeliveredMessageActivity activity) {
        System.out.println("***** Delivery Event Received");
        System.out.println("getActivityId(): " + activity.getActivityId());
        System.out.println("getCorrelationId(): " + activity.getCorrelationId());
        System.out.println("getEmailAddress(): " + activity.getEmailAddress());
        System.out.println("getIpAddress(): " + activity.getIpAddress());
        System.out.println("MessageSize: " + activity.getMessageSize());
        System.out.println("getRecipientId(): " + activity.getRecipientId());
        System.out.println("getRequestId(): " + activity.getRequestId());
        System.out.println("getTimestamp(): " + activity.getTimestamp());
        System.out.println();
    }
    
    public static void print(SpamComplaintActivity activity) {
        System.out.println("***** Spam Complaint Event Received");
        System.out.println("getActivityId(): " + activity.getActivityId());
        System.out.println("getCorrelationId(): " + activity.getCorrelationId());
        System.out.println("getEmailAddress(): " + activity.getEmailAddress());
        System.out.println("getIpAddress(): " + activity.getIpAddress());
        System.out.println("Isp: " + activity.getIsp());
        System.out.println("getRecipientId(): " + activity.getRecipientId());
        System.out.println("getRequestId(): " + activity.getRequestId());
        System.out.println("Subject: " + activity.getSubject());
        System.out.println("getTimestamp(): " + activity.getTimestamp());
        System.out.println();
    }
    
    public static void print(JobErrorActivity activity) {
        System.out.println("***** Job Error Event Received");
        System.out.println("getActivityId(): " + activity.getActivityId());
        System.out.println("getCorrelationId(): " + activity.getCorrelationId());
        System.out.println("JobError: " + activity.getJobError());
        System.out.println("getRequestId(): " + activity.getRequestId());
        System.out.println("getTimestamp(): " + activity.getTimestamp());
        System.out.println();
    }
    
    public static void print(RenderErrorActivity activity) {
        System.out.println("***** Render Error Event Received");
        System.out.println("getActivityId(): " + activity.getActivityId());
        System.out.println("getCorrelationId(): " + activity.getCorrelationId());
        System.out.println("getEmailAddress(): " + activity.getEmailAddress());
        System.out.println("getRecipientId(): " + activity.getRecipientId());
        System.out.println("RenderErrors: " + activity.getRenderErrors());
        System.out.println("getRequestId(): " + activity.getRequestId());
        System.out.println("getTimestamp(): " + activity.getTimestamp());
        System.out.println();
    }
    
    public static void print(UnsubActivity activity) {
        System.out.println("***** Unsub Event Received");
        System.out.println("getActivityId(): " + activity.getActivityId());
        System.out.println("getCorrelationId(): " + activity.getCorrelationId());
        System.out.println("getEmailAddress(): " + activity.getEmailAddress());
        System.out.println("getIpAddress(): " + activity.getIpAddress());
        System.out.println("getRecipientId(): " + activity.getRecipientId());
        System.out.println("getRequestId(): " + activity.getRequestId());
        System.out.println("getTimestamp(): " + activity.getTimestamp());
        System.out.println("getUserAgent(): " + activity.getUserAgent());
        System.out.println();
    }
    
    /**
     * A utility function designed to pretty-print a @TransactionalJobSubmitResponse
     * to standard out.
     * @param response The @TransactionalJobSubmitResponse to print.
     */
    public static void printResponse(TransactionalJobSubmitResponse response) {
        printResponse(response.getResult(), response.getRequestErrors());
    }
    
    /**
     * A utility function designed to pretty-print a @BulkJobSubmitResponse
     * to standard out.
     * @param response The @BulkJobSubmitResponse to print.
     */
    public static void printResponse(BulkJobSubmitResponse response) {
        printResponse(response.getResult(), response.getRequestErrors());
    }    
    
    /**
     * A utility function designed to pretty-print a UpdateAccountResponse
     * to standard out.
     * @param response The UpdateAccountResponse to print.
     */
    public static void printResponse(UpdateAccountResponse response) {
        printResponse(response.getResult(), response.getRequestErrors());
    }

    /**
     * A utility function designed to pretty-print a CreateAccountResponse
     * to standard out.
     * @param response The CreateAccountResponse to print.
     */
    public static void printResponse(CreateAccountResponse response) {
        printResponse(response.getResult(), response.getRequestErrors());
        if (response.getResult() == ResultType.REQUEST_SUCCESSFUL) {
            System.out.println("New Account ID: " + response.getAccount().getId());
        }
    }

    /**
     * A utility function designed to pretty-print a @ThumbnailResponse
     * to standard out.
     * @param response The @ThumbnailResponse to print.
     */
    public static void printResponse(ThumbnailResponse response) {
        printResponse(response.getResult(), response.getRequestErrors());
        if (response.getResult() == ResultType.REQUEST_SUCCESSFUL) {
            System.out.println("Image URL: " + response.getImageUrl());
        }
    }

    /**
     * A utility function designed to pretty-print a @AccountSummaryResponse
     * to standard out.
     * @param response The @AccountSummaryResponse to print.
     */
    public static void printResponse(AccountSummaryResponse response) {
        printResponse(response.getResult(), response.getRequestErrors());
        if (response.getResult() == ResultType.REQUEST_SUCCESSFUL) {
            System.out.println("AccountSummary For: " + response.getAccountSummary().getActivityDate());
            System.out.println("Messages: " + response.getAccountSummary().getMessages());
            System.out.println("Deliveries: " + response.getAccountSummary().getDeliveries());
            System.out.println("Bounces: " + response.getAccountSummary().getBounces());
            System.out.println("Opens: " + response.getAccountSummary().getOpens());
            System.out.println("Clicks: " + response.getAccountSummary().getClicks());
            System.out.println("JobErrors: " + response.getAccountSummary().getJobErrors());
            System.out.println("RenderErrors: " + response.getAccountSummary().getRenderErrors());
            System.out.println("Complaints: " + response.getAccountSummary().getSpamComplaints());
            System.out.println("Unsubscribes: " + response.getAccountSummary().getUnsubscribes());
        }
    }
    
    /**
     * A utility function designed to pretty-print a @BulkJobSummaryResponse
     * to standard out.
     * @param response The @BulkJobSummaryResponse to print.
     */
    public static void printResponse(BulkJobSummaryResponse response) {
        printResponse(response.getResult(), response.getRequestErrors());
        if (response.getResult() == ResultType.REQUEST_SUCCESSFUL) {
            System.out.println("BulkJobSummary For: " + response.getBulkJobSummary().getBulkJobRequestId());
            System.out.println("CorrelationId: " + response.getBulkJobSummary().getCorrelationId());
            
            if (response.getBulkJobSummary().getBulkJobErrors() == null) {
                System.out.println("Messages: " + response.getBulkJobSummary().getMessages());
                System.out.println("Deliveries: " + response.getBulkJobSummary().getDeliveries());
                System.out.println("Bounces: " + response.getBulkJobSummary().getBounces());
                System.out.println("Opens: " + response.getBulkJobSummary().getOpens());
                System.out.println("Clicks: " + response.getBulkJobSummary().getClicks());
                System.out.println("RenderErrors: " + response.getBulkJobSummary().getRenderErrors());
                System.out.println("Complaints: " + response.getBulkJobSummary().getSpamComplaints());
                System.out.println("Unsubscribes: " + response.getBulkJobSummary().getUnsubscribes());
            } else {
                for (BulkJobError error : response.getBulkJobSummary().getBulkJobErrors().getBulkJobError()) {
                    System.out.println("Error: " + error.getErrorCode() + " - " + error.getErrorMessage());
                }
            }
        }
    }
    
    /**
     * A utility function designed to pretty-print a @MessagePreviewResponse
     * to standard out.
     * @param response The @MessagePreviewResponse to print.
     */
    public static void printResponse(MessagePreviewResponse response) {
        printResponse(response.getResult(), response.getRequestErrors());      
        if (response.getResult() == ResultType.REQUEST_SUCCESSFUL) {             
            if (response.getRenderErrors() == null) {
                System.out.println("FromName: " + response.getPreviewContent().getFromName() +
                                  " <" + response.getPreviewContent().getFromAddress() + ">");
                System.out.println("OnBehalfOf: " + response.getPreviewContent().getOnBehalfOfName() +
                                  " <" + response.getPreviewContent().getOnBehalfOfAddress() + ">");
                System.out.println("ReplyTo: " + response.getPreviewContent().getReplyToAddress());
                System.out.println("Subject: " + response.getPreviewContent().getSubjectLine());
                System.out.println("HtmlContent: " + response.getPreviewContent().getHtmlContent());
                System.out.println("TextContent: " + response.getPreviewContent().getTextContent());
                System.out.println("SpamAssassinReport:");
                System.out.println("SpamAssassinScore: " + response.getPreviewContent().getSpamAssassinReport().getScore());
                for (SpamAssassinRule rule : response.getPreviewContent().getSpamAssassinReport().getSpamAssassinRules().getSpamAssassinRule()) {
                    System.out.println(rule.getPoints() + " Points - " + rule.getRuleName() + " : " + rule.getDescription());
                }
            } else {
                for (RenderError error : response.getRenderErrors().getRenderError()) {
                    System.err.println("RenderError: " + error.getErrorCode() + " - " + error.getErrorMessage());
                }
            }
        } else {
            for (RequestError error : response.getRequestErrors().getRequestError()) {
                System.err.println("RequestError: " + error.getErrorCode() + " - " + error.getErrorMessage());
            }
        }
    }
    
    /**
     * A utility function designed to pretty-print a @TransactionalContent
     * to standard out.
     * @param response The @TransactionalContent to print.
     */
    public static void printResponse(TransactionalContent response) {
        printResponse(response.getResult(), response.getRequestErrors());      
        if (response.getResult() == ResultType.REQUEST_SUCCESSFUL) {             
            if (response.getRenderErrors() == null) {
                System.out.println("FromName: " + response.getFromName() +
                                  " <" + response.getFromAddress() + ">");
                System.out.println("Subject: " + response.getSubjectLine());
                System.out.println("HtmlContent: " + response.getHtmlContent());
                System.out.println("TextContent: " + response.getTextContent());
                System.out.println("Attachments:");
                for (Attachment attachment : response.getAttachments().getAttachment()) {
                	System.out.println("            Name: " + attachment.getName());
                	System.out.println("    Content type: " + attachment.getContentType());
                }
            } else {
                for (RenderError error : response.getRenderErrors().getRenderError()) {
                    System.err.println("RenderError: " + error.getErrorCode() + " - " + error.getErrorMessage());
                }
            }
        } else {
            for (RequestError error : response.getRequestErrors().getRequestError()) {
                System.err.println("RequestError: " + error.getErrorCode() + " - " + error.getErrorMessage());
            }
        }
    }
    
    private static void printResponse(ResultType result, RequestErrors requestErrors) {
        if(result == ResultType.REQUEST_SUCCESSFUL) {
            System.out.println(ResultType.REQUEST_SUCCESSFUL);
        } else {
            System.out.println(ResultType.REQUEST_FAILED);
            System.out.println("Number of Errors: " + requestErrors.getRequestErrorCount());
            for (RequestError error : requestErrors.getRequestError()) {
                System.out.println(error.getErrorCode() + ":" + error.getErrorMessage());
            }
        }
    }
}
