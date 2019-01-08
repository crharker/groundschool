/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starfireaviation.groundschool.model.SMSMessage;
import com.starfireaviation.groundschool.service.SMSService;

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
     * SMSService
     */
    @Autowired
    private SMSService smsService;

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
        return smsService.receiveMessage(new SMSMessage(message));
    }

}
