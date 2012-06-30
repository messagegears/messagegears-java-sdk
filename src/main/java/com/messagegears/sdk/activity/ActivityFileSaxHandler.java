package com.messagegears.sdk.activity;

import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.lang.StringEscapeUtils;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.messagegears.sdk.MessageGearsListener;
import com.messagegears.sdk.exception.MessageGearsClientException;
import com.messagegears.sdk.model.ActivityType;
import com.messagegears.sdk.v3_1.BouncedMessageActivity;
import com.messagegears.sdk.v3_1.ClickActivity;
import com.messagegears.sdk.v3_1.DeliveredMessageActivity;
import com.messagegears.sdk.v3_1.JobErrorActivity;
import com.messagegears.sdk.v3_1.OpenActivity;
import com.messagegears.sdk.v3_1.RenderErrorActivity;
import com.messagegears.sdk.v3_1.SpamComplaintActivity;
import com.messagegears.sdk.v3_1.UnsubActivity;

/**
 * A XML Sax parsing handler for streaming XML processing.
 * 
 * This class can be used to parse daily activity file
 * or activity feed bundles.
 * 
 * This class parses individual Activity items from a given
 * @InputStream, so it is safe to use with Activity bundles of 
 * any size.
 * 
 * @author tjones
 *
 */
public class ActivityFileSaxHandler extends DefaultHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityFileSaxHandler.class);

    private MessageGearsListener listener;
    private ActivityType activityType;
    private String activityXml = "";
    private boolean inActivityItems = false;
    private long numItems = 0;

    /**
     * Constructor.
     * 
     * @param listener The @MessageGearsListener to invoke with each item parsed.
     * @param activityType The @ActivityType to expect. Currently this is limited to 
     * a single activity per InputStream for efficiency, but this limitation may be
     * removed if needed in the future.
     */
    public ActivityFileSaxHandler(MessageGearsListener listener, ActivityType activityType) {
        this.listener = listener;
        this.activityType = activityType;
    }
    
    @Override()
    public void startDocument() {
        numItems = 0;
    }
    
    @Override()
    public void endDocument() {
        LOGGER.debug("Parsing Complete. Number of Items Found: " + numItems);
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        if(!qName.equals("ActivityItems")) {
            if (inActivityItems) {
                activityXml = activityXml + "<" + qName + ">";
            }
        } else {
            inActivityItems = true;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) {
        if (inActivityItems) {
            String string = new String(ch);
            String substring = string.substring(start, start + length);
            activityXml = activityXml + StringEscapeUtils.escapeXml(substring);
        }
    }
    
    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        if (inActivityItems) {
            if(!qName.equals("ActivityItems")) {
                activityXml = activityXml + "</" + qName + ">";
                if(qName.equals(activityType.getXmlTag())) {
                    numItems++;
                    processElement(activityXml);
                    activityXml = "";
                }
            } else {
                inActivityItems = false;
            }
        }
    }
    
    /**
     * Returns the number of items parsed.
     * 
     * @return long containing the number of items parsed.
     */
    public long getNumItems() {
        return numItems;
    }
    
    private void processElement(String xml) {
        try {
            Reader reader = new StringReader(xml);
            if (activityType.equals(ActivityType.BOUNCES)) {
                listener.onBounce(BouncedMessageActivity.unmarshal(reader));
            } else if (activityType.equals(ActivityType.CLICKS)) {
                listener.onClick(ClickActivity.unmarshal(reader));
            } else if (activityType.equals(ActivityType.DELIVERIES)) {
                listener.onDelivery(DeliveredMessageActivity.unmarshal(reader));
            } else if (activityType.equals(ActivityType.JOB_ERRORS)) {
                listener.onJobError(JobErrorActivity.unmarshal(reader));
            } else if (activityType.equals(ActivityType.OPENS)) {
                listener.onOpen(OpenActivity.unmarshal(reader));
            } else if (activityType.equals(ActivityType.RENDER_ERRORS)) {
                listener.onRenderError(RenderErrorActivity.unmarshal(reader));
            } else if (activityType.equals(ActivityType.SPAM_COMPLAINTS)) {
                listener.onSpamComplaint(SpamComplaintActivity.unmarshal(reader));
            } else if (activityType.equals(ActivityType.UNSUBSCRIBES)) {
                listener.onUnsub(UnsubActivity.unmarshal(reader));
            } else {
                throw new MessageGearsClientException ("Unrecognized ActivityType: " + activityType);
            }
        } catch (MarshalException me) {
            LOGGER.debug(me.getMessage(), me);
            throw new MessageGearsClientException ("Failed to unmarshal string: " + xml);
        } catch (ValidationException ve) {
            LOGGER.debug(ve.getMessage(), ve);
            throw new MessageGearsClientException ("Failed to unmarshal string: " + xml);
        }
    }
}
