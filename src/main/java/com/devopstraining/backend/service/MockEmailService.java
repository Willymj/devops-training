package com.devopstraining.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;


public class MockEmailService extends AbstractEmailService {

    /** The application logger */
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MockEmailService.class);


    @Override
    public void sendGenericEmailMessage(SimpleMailMessage message) {
        LOG.debug("Simulating and email service...");
        LOG.info(message.toString());
        LOG.debug("Email sent.");

    }
}
