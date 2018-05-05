package com.devopstraining.backend.service;

import com.devopstraining.web.domain.frontend.FeedbackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.to.address}")
    private String defaultToAddress;
    /**
     * Create a simple mail message from a Feedback Pojo.
     * @param feedbackPojo The feedback Pojo.
     * @return
     */
    protected SimpleMailMessage prepareSimpleMailMessageFromFeebackPojo(FeedbackPojo feedback){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(defaultToAddress);
        message.setFrom(feedback.getEmail());
        message.setSubject("[devops-training]: Feedback received from " +feedback.getFirstName() + " "
                +feedback.getLastName() + "!");
        message.setText(feedback.getFeedback());
        return message;
    }

    @Override
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo){
        sendGenericEmailMessage(prepareSimpleMailMessageFromFeebackPojo(feedbackPojo));
    }

}
