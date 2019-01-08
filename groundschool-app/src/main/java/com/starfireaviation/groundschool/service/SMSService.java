/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import com.starfireaviation.groundschool.model.SMSMessage;

/**
 * SMSService
 *
 * @author brianmichael
 */
public interface SMSService {

    /**
     * Sends an SMS
     *
     * @param fromAddress from address
     * @param toAddress to address
     * @param body body
     */
    public void send(
            String fromAddress,
            String toAddress,
            String body);

    /**
     * Receives a SMS message and returns response
     *
     * @param message received
     * @return response
     */
    public String receiveMessage(SMSMessage message);
}
