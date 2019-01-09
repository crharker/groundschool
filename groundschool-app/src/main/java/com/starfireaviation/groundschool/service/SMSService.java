/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import com.starfireaviation.groundschool.model.NotificationEventType;
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
     * @param userId user ID
     * @param eventId event ID
     * @param quizId quiz ID
     * @param questionId question ID
     * @param type NotificationEventType
     * @param fromAddress from address
     * @param toAddress to address
     * @param body body
     */
    public void send(
            Long userId,
            Long eventId,
            Long quizId,
            Long questionId,
            NotificationEventType type,
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

    /**
     * Closes all open SMS messages
     *
     * @param userId user ID
     */
    public void closeAllMessages(Long userId);
}
