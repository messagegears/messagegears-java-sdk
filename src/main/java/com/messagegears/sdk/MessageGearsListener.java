package com.messagegears.sdk;

import com.messagegears.sdk.v3_1.BouncedMessageActivity;
import com.messagegears.sdk.v3_1.ClickActivity;
import com.messagegears.sdk.v3_1.DeliveredMessageActivity;
import com.messagegears.sdk.v3_1.InboundEmailActivity;
import com.messagegears.sdk.v3_1.JobErrorActivity;
import com.messagegears.sdk.v3_1.OpenActivity;
import com.messagegears.sdk.v3_1.RenderErrorActivity;
import com.messagegears.sdk.v3_1.RequestActivity;
import com.messagegears.sdk.v3_1.SmsDeliveredActivity;
import com.messagegears.sdk.v3_1.SmsDeliveryFailureActivity;
import com.messagegears.sdk.v3_1.SmsInboundActivity;
import com.messagegears.sdk.v3_1.SmsRenderErrorActivity;
import com.messagegears.sdk.v3_1.SpamComplaintActivity;
import com.messagegears.sdk.v3_1.UnsubActivity;

/**
 * Interface for processing individual activity elements.
 * 
 * This interface can be used when processing the event feed or
 * processing the daily xml activity file.
 * 
 * @author tjones
 *
 */
public interface MessageGearsListener {

    public void onClick(ClickActivity activity);
    
    public void onOpen(OpenActivity activity);
    
    public void onBounce(BouncedMessageActivity activity);
    
    public void onDelivery(DeliveredMessageActivity activity);
    
    public void onSpamComplaint(SpamComplaintActivity activity);
    
    public void onJobError(JobErrorActivity activity);
    
    public void onRenderError(RenderErrorActivity activity);
    
    public void onInboundEmail(InboundEmailActivity activity);
    
    public void onUnsub(UnsubActivity activity);
    
    public void onSmsDeliveryActivity(SmsDeliveredActivity activity);
    
    public void onSmsDeliveryFailureActivity(SmsDeliveryFailureActivity activity);
    
    public void onSmsRenderErrorActivity(SmsRenderErrorActivity activity);
    
    public void onSmsInboundActivity(SmsInboundActivity activity);
    
    public void onRequestActivity(RequestActivity activity);
    
}
