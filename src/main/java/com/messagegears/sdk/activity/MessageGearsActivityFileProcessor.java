package com.messagegears.sdk.activity;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.messagegears.sdk.MessageGearsListener;
import com.messagegears.sdk.exception.MessageGearsClientException;
import com.messagegears.sdk.model.ActivityType;

/**
 * This class is responsible for setting up the @ActivityFileSaxHandler
 * and the parsing factory in order to parse a given activity file as a 
 * stream.
 * 
 * @author tjones
 *
 */
public class MessageGearsActivityFileProcessor {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageGearsActivityFileProcessor.class);

    private MessageGearsListener listener;
    
    /**
     * Constructor.
     * 
     * @param listener The @MessageGearsListener to invoke with each activity item parsed.
     */
    public MessageGearsActivityFileProcessor (MessageGearsListener listener) {
        this.listener = listener;
    }
    
    /**
     * Call to start processing the given @InputStream that contains activity 
     * of the @ActivityType type.
     * 
     * @param inputStream The @InputStream to be parsed.
     * @param activityType The @ActivityType contained in the stream.
     */
    public void process(InputStream inputStream, ActivityType activityType) {
        try {
            // Create a new handler
            ActivityFileSaxHandler saxHandler = new ActivityFileSaxHandler(listener, activityType);
            // Parse the list
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // Turn on validation
            factory.setValidating(true);
            SAXParser parser = factory.newSAXParser();
            XMLReader xr = parser.getXMLReader();
            xr.setContentHandler(saxHandler);
            xr.setErrorHandler(saxHandler);
            xr.parse(new InputSource(inputStream));
            LOGGER.debug("Stream contained " + saxHandler.getNumItems() + " activity items.");
        } catch (ParserConfigurationException pce) {
            LOGGER.debug(pce.getMessage(), pce);
            throw new MessageGearsClientException("Parser Configuration Error");
        } catch (SAXException saxe) {
            LOGGER.debug(saxe.getMessage(), saxe);
            throw new MessageGearsClientException("Error Parsing Input Source");
        } catch (IOException ioe) {
            LOGGER.debug(ioe.getMessage(), ioe);
            throw new MessageGearsClientException("Error Parsing Input Source");
        }
    }
}
