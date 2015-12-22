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

import com.messagegears.sdk.MessageGearsErrorHandler;
import com.messagegears.sdk.MessageGearsListener;
import com.messagegears.sdk.exception.MessageGearsClientException;
import com.messagegears.sdk.exception.MessageGearsDefaultErrorHandler;
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
    private MessageGearsErrorHandler errorHandler;
    
    /**
     * Constructor.
     * 
     * This method uses the @MessageGearsDefaultErrorHandler.
     * 
     * @param listener The @MessageGearsListener to invoke with each activity item parsed.
     */
    public MessageGearsActivityFileProcessor (MessageGearsListener listener) {
        this.listener = listener;
        this.errorHandler = new MessageGearsDefaultErrorHandler();
    }
    
    /**
     * Constructor.
     * 
     * @param listener The @MessageGearsListener to invoke with each activity item parsed.
     * @param errorHandler The @MessageGearsErrorHandler to use when errors are encountered.
     */
    public MessageGearsActivityFileProcessor (MessageGearsListener listener, MessageGearsErrorHandler errorHandler) {
        this.listener = listener;
        this.errorHandler = errorHandler;
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
            ActivityFileSaxHandler saxHandler = new ActivityFileSaxHandler(listener, errorHandler, activityType);
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
