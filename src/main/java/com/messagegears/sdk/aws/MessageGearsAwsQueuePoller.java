package com.messagegears.sdk.aws;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.messagegears.sdk.MessageGearsListener;
import com.messagegears.sdk.exception.MessageGearsClientException;
import com.messagegears.sdk.v3_1.ActivityItems;
import com.messagegears.sdk.v3_1.BouncedMessageActivity;
import com.messagegears.sdk.v3_1.ClickActivity;
import com.messagegears.sdk.v3_1.DeliveredMessageActivity;
import com.messagegears.sdk.v3_1.JobErrorActivity;
import com.messagegears.sdk.v3_1.OpenActivity;
import com.messagegears.sdk.v3_1.RenderErrorActivity;
import com.messagegears.sdk.v3_1.SmsDeliveredActivity;
import com.messagegears.sdk.v3_1.SmsDeliveryFailureActivity;
import com.messagegears.sdk.v3_1.SmsInboundActivity;
import com.messagegears.sdk.v3_1.SmsRenderErrorActivity;
import com.messagegears.sdk.v3_1.SpamComplaintActivity;
import com.messagegears.sdk.v3_1.UnsubActivity;

public class MessageGearsAwsQueuePoller {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageGearsAwsQueuePoller.class);

    private static final int DEFAULT_WAIT_BETWEEN_RECEIVE = 10000;
    private boolean isRunning = false;
    
    MessageGearsAwsProperties mgAwsProperties;
    MessageGearsAwsClient client;
    MessageGearsListener listener;

    public MessageGearsAwsQueuePoller(MessageGearsAwsProperties mgAwsProperties, 
                                      MessageGearsListener listener) {
        this.mgAwsProperties = mgAwsProperties;
        client = new MessageGearsAwsClient(mgAwsProperties);
        this.listener = listener;
    }
    
    public void stop() {
        this.isRunning = false;
    }
    
    public boolean isActive() {
        return this.isRunning;
    }

    public void start() {
        this.isRunning = true;
        while (isRunning) {
            ReceiveMessageRequest request = new ReceiveMessageRequest();
            request.setQueueUrl(mgAwsProperties.getMyAwsEventQueueUrl());
            ReceiveMessageResult receiveMessageResult = client.receiveMessage(request);
            List<Message> messages = receiveMessageResult.getMessages();
            if (messages != null && (messages.size() > 0)) {
                for (Message message : messages) {
                    LOGGER.debug("Found item on queue: " + message.getBody());
                    ActivityItems items = getActivityItems(message);
                    dispatchItems(items);
                    deleteMessage(message);
                }
            } else {
                try {
                    LOGGER.info("No items found on queue.  Sleeping for " + DEFAULT_WAIT_BETWEEN_RECEIVE + " ms...");
                    Thread.sleep(DEFAULT_WAIT_BETWEEN_RECEIVE);
                } catch (InterruptedException e) {
                    isRunning = false;
                }
            }
        }
    }
    
    private void deleteMessage(Message message) {
        DeleteMessageRequest request = new DeleteMessageRequest();
        request.setQueueUrl(mgAwsProperties.getMyAwsEventQueueUrl());
        request.setReceiptHandle(message.getReceiptHandle());
        client.deleteSqsMessage(request);
    }
    
    private ActivityItems getActivityItems(Message message) {
        Reader reader = new StringReader(message.getBody());
        try {
            ActivityItems activityItems = ActivityItems.unmarshal(reader);
            return activityItems;
        } catch (ValidationException ve) {
            LOGGER.debug(ve.getMessage(), ve);
            throw new MessageGearsClientException("Failed to unmarshal message: " + message.getBody());
        } catch (MarshalException me) {
            LOGGER.debug(me.getMessage(), me);
            throw new MessageGearsClientException("Failed to unmarshal message: " + message.getBody());
        }    
    }
    
    private void dispatchItems(ActivityItems items) {
        if(items.getBouncedMessageActivity() != null) {
            for(BouncedMessageActivity bounce : items.getBouncedMessageActivity()) {
                LOGGER.info("Received a bounce message belonging to job: " + bounce.getRequestId());
                listener.onBounce(bounce);
            }
        }

        if(items.getClickActivity() != null) {
            for (ClickActivity click : items.getClickActivity()) {
                LOGGER.info("Received a click message belonging to job: " + click.getRequestId());
                listener.onClick(click);
            }
        }
        
        if(items.getDeliveredMessageActivity() != null) {
            for (DeliveredMessageActivity delivery : items.getDeliveredMessageActivity()) {
                LOGGER.info("Received a delivery message belonging to job: " + delivery.getRequestId());
                listener.onDelivery(delivery);
            }
        }

        if(items.getJobErrorActivity() != null) {
            for (JobErrorActivity jobError : items.getJobErrorActivity()) {
                LOGGER.info("Received a job error message belonging for job: " + jobError.getRequestId());
                listener.onJobError(jobError);
            }
        }

        if(items.getOpenActivity() != null) {
            for (OpenActivity open : items.getOpenActivity()) {
                LOGGER.info("Received an open message belonging for job: " + open.getRequestId());
                listener.onOpen(open);
            }
        }

        if(items.getRenderErrorActivity() != null) {
            for (RenderErrorActivity renderError : items.getRenderErrorActivity()) {
                LOGGER.info("Received a render error message belonging to job: " + renderError.getRequestId());
                listener.onRenderError(renderError);
            }
        }

        if(items.getSpamComplaintActivity() != null) {
            for (SpamComplaintActivity spamComplaint : items.getSpamComplaintActivity()) {
                LOGGER.info("Received a spam complaint message belonging to job: " + spamComplaint.getRequestId());
                listener.onSpamComplaint(spamComplaint);
            }
        }

        if(items.getUnsubActivity() != null) {
            for (UnsubActivity unsub : items.getUnsubActivity()) {
                LOGGER.info("Received an unsub message belonging to job: " + unsub.getRequestId());
                listener.onUnsub(unsub);
            }       
        }
        
        if(items.getSmsDeliveredActivity() != null) {
            for (SmsDeliveredActivity smsDeliveredActivity : items.getSmsDeliveredActivity()) {
                LOGGER.info("Received an smsDeliveredActivity message belonging to job: " + smsDeliveredActivity.getRequestId());
                listener.onSmsDeliveryActivity(smsDeliveredActivity);
            }       
        }
        
        if(items.getSmsDeliveryFailureActivity() != null) {
            for (SmsDeliveryFailureActivity smsDeliveryFailureActivity : items.getSmsDeliveryFailureActivity()) {
                LOGGER.info("Received an smsDeliveryFailureActivity message belonging to job: " + smsDeliveryFailureActivity.getRequestId());
                listener.onSmsDeliveryFailureActivity(smsDeliveryFailureActivity);
            }       
        }
        
        if(items.getSmsRenderErrorActivity() != null) {
            for (SmsRenderErrorActivity smsRenderErrorActivity : items.getSmsRenderErrorActivity()) {
                LOGGER.info("Received an smsRenderErrorActivity message belonging to job: " + smsRenderErrorActivity.getRequestId());
                listener.onSmsRenderErrorActivity(smsRenderErrorActivity);
            }       
        }
        
        if(items.getSmsInboundActivity() != null) {
            for (SmsInboundActivity smsInboundActivity : items.getSmsInboundActivity()) {
                LOGGER.info("Received an smsInboundActivity message: " + smsInboundActivity.getSmsBody());
                listener.onSmsInboundActivity(smsInboundActivity);
            }       
        }
    }


}
