/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starfireaviation.groundschool.model.Message;
import com.starfireaviation.groundschool.service.MessageService;

/**
 * NotificationController
 *
 * @author brianmichael
 */
@RestController
@RequestMapping({
        "/notifications"
})
public class NotificationController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    /**
     * SMSService
     */
    @Autowired
    @Qualifier("smsService")
    private MessageService smsService;

    /**
     * Receives a SMS message
     *
     * @param message received
     * @return response
     */
    @PostMapping(path = {
            "/sms"
    })
    public String sms(@RequestBody String message) {
        LOGGER.info(String.format("sms() called with [%s]", message));
        return smsService.receiveMessage(new Message(message));
    }

}
