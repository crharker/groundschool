/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.SMSMessage;
import com.starfireaviation.groundschool.properties.SMSProperties;
import com.starfireaviation.groundschool.service.SMSService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * SMSServiceImpl
 *
 * @author brianmichael
 */
@Service
public class SMSServiceImpl implements SMSService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SMSServiceImpl.class);

    /**
     * SMSProperties
     */
    @Autowired
    private SMSProperties smsProperties;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(
            String fromAddress,
            String toAddress,
            String body) {
        LOGGER.info(
                String.format(
                        "Sending... fromAddress [%s]; toAddress [%s]; body [%s]",
                        fromAddress,
                        toAddress,
                        body));
        Twilio.init(smsProperties.getAccountSid(), smsProperties.getAuthId());
        Message message = Message.creator(new PhoneNumber(toAddress), new PhoneNumber(fromAddress), body).create();
        LOGGER.info(String.format("Status [%s]", message.getStatus()));
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public String receiveMessage(SMSMessage message) {
        LOGGER.info(String.format("receiveMessage() message received was [%s]", message));
        //com.twilio.twiml.messaging.Body body = new com.twilio.twiml.messaging.Body.Builder(
        //        "The Robots are coming! Head for the hills!")
        //                .build();
        //com.twilio.twiml.messaging.Message sms = new com.twilio.twiml.messaging.Message.Builder()
        //        .body(body)
        //        .build();
        //MessagingResponse twiml = new MessagingResponse.Builder()
        //        .message(sms)
        //        .build();
        //return twiml.toXml();
        return "The Robots are coming! Head for the hills!";
    }

}
