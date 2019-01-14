/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import com.starfireaviation.groundschool.model.Message;
import com.starfireaviation.groundschool.model.User;

/**
 * SMSService
 *
 * @author brianmichael
 */
public interface MessageService {

    /**
     * Sends a message for user deletion
     *
     * @param user User
     */
    public void sendUserDeleteMsg(final User user);

    /**
     * Sends a message to RSVP for an upcoming event
     *
     * @param user User
     */
    public void sendEventRSVPMsg(final User user);

    /**
     * Sends a message to RSVP for an upcoming event
     *
     * @param user User
     */
    public void sendEventStartMsg(final User user);

    /**
     * Sends a message that a question has been asked
     *
     * @param user User
     */
    public void sendQuestionAskedMsg(final User user);

    /**
     * Sends a message for registering for an upcoming event
     *
     * @param user User
     */
    public void sendEventRegisterMsg(final User user);

    /**
     * Sends a message for unregistering from an upcoming event
     *
     * @param user User
     */
    public void sendEventUnregisterMsg(final User user);

    /**
     * Sends a message for user settings verified
     *
     * @param user User
     */
    public void sendUserSettingsVerifiedMsg(final User user);

    /**
     * Sends a message for user settings changed
     *
     * @param user User
     */
    public void sendUserSettingsChangeMsg(final User user);

    /**
     * Sends an invitation
     *
     * @param user User
     * @param destination address
     */
    public void sendInviteMsg(final User user, String destination);

    /**
     * Resends a message for user settings changed
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
     * Receives a message and returns response
     *
     * @param message received
     * @return response
     */
    public String receiveMessage(Message message);

    /**
     * Closes all open messages
     *
     * @param userId user ID
     */
    public void closeAllMessages(Long userId);
}
