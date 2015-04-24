package com.messagegears.sdk.model.request;

import java.util.ArrayList;
import java.util.List;

import com.messagegears.sdk.model.Attachment;
import com.messagegears.sdk.model.Header;
import com.messagegears.sdk.model.TemplateLanguage;

public abstract class JobRequest extends BaseJobRequest {

    private TemplateLanguage templateLanguage = TemplateLanguage.FREEMARKER;
    
    private String fromAddress;
    private String fromName;
    private String subjectLine;
    private String htmlTemplate;
    private String textTemplate;
    private String characterSet;
    private String replyToAddress;
    private String onBehalfOfAddress;
    private String onBehalfOfName;
    private List<Attachment> attachments = new ArrayList<Attachment>();
    private boolean autoTrack = false;
    private String urlAppend;
    private String customTrackingDomain;
    private boolean unsubscribeHeader = false;
    private List<Header> headers = new ArrayList<Header>();
    private String templateLibrary;
    private String jobCategory;

    /**
     * @return the templateLanguage
     */
    public TemplateLanguage getTemplateLanguage() {
        return templateLanguage;
    }
    
    /**
     * @param templateLanguage the templateLanguage to set
     */
    public void setTemplateLanguage(TemplateLanguage templateLanguage) {
        this.templateLanguage = templateLanguage;
    }
    
    /**
     * @return the fromAddress
     */
    public String getFromAddress() {
        return fromAddress;
    }
    
    /**
     * @param fromAddress the fromAddress to set
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
    
    /**
     * @return the fromName
     */
    public String getFromName() {
        return fromName;
    }
    
    /**
     * @param fromName the fromName to set
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
    
    /**
     * @return the subjectLine
     */
    public String getSubjectLine() {
        return subjectLine;
    }
    
    /**
     * @param subjectLine the subjectLine to set
     */
    public void setSubjectLine(String subjectLine) {
        this.subjectLine = subjectLine;
    }
    
    /**
     * @return the htmlTemplate
     */
    public String getHtmlTemplate() {
        return htmlTemplate;
    }
    
    /**
     * @param htmlTemplate the htmlTemplate to set
     */
    public void setHtmlTemplate(String htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
    }
    
    /**
     * @return the textTemplate
     */
    public String getTextTemplate() {
        return textTemplate;
    }
    
    /**
     * @param textTemplate the textTemplate to set
     */
    public void setTextTemplate(String textTemplate) {
        this.textTemplate = textTemplate;
    }
    
    /**
     * @return the characterSet
     */
    public String getCharacterSet() {
        return characterSet;
    }
    
    /**
     * @param characterSet the characterSet to set
     */
    public void setCharacterSet(String characterSet) {
        this.characterSet = characterSet;
    }
    
    /**
     * @return the replyToAddress
     */
    public String getReplyToAddress() {
        return replyToAddress;
    }
    
    /**
     * @param replyToAddress the replyToAddress to set
     */
    public void setReplyToAddress(String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }
    
    /**
     * @return the onBehalfOfAddress
     */
    public String getOnBehalfOfAddress() {
        return onBehalfOfAddress;
    }
    
    /**
     * @param onBehalfOfAddress the onBehalfOfAddress to set
     */
    public void setOnBehalfOfAddress(String onBehalfOfAddress) {
        this.onBehalfOfAddress = onBehalfOfAddress;
    }
    
    /**
     * @return the onBehalfOfName
     */
    public String getOnBehalfOfName() {
        return onBehalfOfName;
    }
    
    /**
     * @param onBehalfOfName the onBehalfOfName to set
     */
    public void setOnBehalfOfName(String onBehalfOfName) {
        this.onBehalfOfName = onBehalfOfName;
    }
    
    /**
     * @return the attachments
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }
    
    /**
     * @param attachments the attachments to set
     */
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
    
    /**
     * @return the autoTrack
     */
    public boolean isAutoTrack() {
        return autoTrack;
    }
    
    /**
     * @param autoTrack the autoTrack to set
     */
    public void setAutoTrack(boolean autoTrack) {
        this.autoTrack = autoTrack;
    }
    
    /**
     * @return the urlAppend
     */
    public String getUrlAppend() {
        return urlAppend;
    }
    
    /**
     * @param urlAppend the urlAppend to set
     */
    public void setUrlAppend(String urlAppend) {
        this.urlAppend = urlAppend;
    }
    
    /**
     * @return the customTrackingDomain
     */
    public String getCustomTrackingDomain() {
        return customTrackingDomain;
    }
    
    /**
     * @param customTrackingDomain the customTrackingDomain to set
     */
    public void setCustomTrackingDomain(String customTrackingDomain) {
        this.customTrackingDomain = customTrackingDomain;
    }
    
    /**
     * @return the unsubscribeHeader
     */
    public boolean isUnsubscribeHeader() {
        return unsubscribeHeader;
    }
    
    /**
     * @param unsubscribeHeader the unsubscribeHeader to set
     */
    public void setUnsubscribeHeader(boolean unsubscribeHeader) {
        this.unsubscribeHeader = unsubscribeHeader;
    }
    
    /**
     * @return the headers
     */
    public List<Header> getHeaders() {
        return headers;
    }
    
    /**
     * @param headers the headers to set
     */
    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public String getTemplateLibrary() {
        return templateLibrary;
    }

    public void setTemplateLibrary(String templateLibrary) {
        this.templateLibrary = templateLibrary;
    }

    /**
     * @return the jobCategory
     */
    public String getJobCategory() {
        return jobCategory;
    }

    /**
     * @param jobCategory the jobCategory to set
     */
    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }
}
