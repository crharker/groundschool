/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import com.starfireaviation.groundschool.model.SMSMessage;
import com.starfireaviation.groundschool.model.User;

/**
 * SMSService
 *
 * @author brianmichael
 */
public interface SMSService {

    /**
     * Sends an SMS message for user deletion
     *
     * @param user User
     */
    public void sendUserDeleteMsg(final User user);

    /**
     * Sends an SMS message to RSVP for an upcoming event
     *
     * @param user User
     */
    public void sendEventRSVPMsg(final User user);

    /**
     * Sends an SMS message to RSVP for an upcoming event
     *
     * @param user User
     */
    public void sendEventStartMsg(final User user);

    /**
     * Sends an SMS message that a question has been asked
     *
     * @param user User
     */
    public void sendQuestionAskedMsg(final User user);

    /**
     * Sends an SMS message for registering for an upcoming event
     *
     * @param user User
     */
    public void sendEventRegisterMsg(final User user);

    /**
     * Sends an SMS message for unregistering from an upcoming event
     *
     * @param user User
     */
    public void sendEventUnregisterMsg(final User user);

    /**
     * Sends a SMS message for user settings verified
     *
     * @param user User
     */
    public void sendUserSettingsVerifiedMsg(final User user);

    /**
     * Sends a SMS message for user settings changed
     *
     * @param user User
     */
    public void sendUserSettingsChangeMsg(final User user);

    /**
     * Resends a SMS message for user settings changed
     *
     * @param user User
     * @param response given by the user
     * @param originalMessage sent to the user
     */
    public void resendUserSettingsChangeMsg(
            final User user,
            String response,
            String originalMessage);

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
