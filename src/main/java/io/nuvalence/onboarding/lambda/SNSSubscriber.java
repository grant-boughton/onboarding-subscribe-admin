package io.nuvalence.onboarding.lambda;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class SNSSubscriber {
    private String topicARN;
    private AmazonSNS snsClient;

    public SNSSubscriber(String topicARN, AmazonSNS snsClient){
        this.topicARN = topicARN;
        this.snsClient = snsClient;
    }

    public String subscribePhone(String phoneNumber){
        SubscribeRequest request = new SubscribeRequest(this.topicARN, "sms", phoneNumber);
        SubscribeResult result = this.snsClient.subscribe(request);
        return result.toString();
    }
}
