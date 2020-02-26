package io.nuvalence.onboarding.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

import java.util.Map;

public class EventHandler implements RequestHandler<Map<String,Object>, Map<String,Object>> {
    private final String snsARN = "arn:aws:sns:us-east-1:116621101481:grant-onboarding-topic";
    private final AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();
    private final SNSSubscriber subscriber = new SNSSubscriber(snsARN,snsClient);

    public Map<String,Object> handleRequest(Map<String,Object> input, Context context) {
        Map<String,Object> request = (Map<String,Object>) input.get("request");
        Map<String,String> userAttributes = (Map<String,String>) request.get("userAttributes");

        if(userAttributes.get("custom:isAdmin").equals("true")){
            String subscribeResult = subscriber.subscribePhone(userAttributes.get("phone_number"));
            System.out.println(subscribeResult);
        }

        System.out.println(userAttributes.toString());
        return input;
    }
}
