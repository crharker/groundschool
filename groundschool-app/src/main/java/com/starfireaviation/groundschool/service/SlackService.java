/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import com.starfireaviation.groundschool.model.User;

/**
 * SlackService
 *
 * @author brianmichael
 */
public interface SlackService {

    /**
     * Sends a slack message to RSVP for an upcoming event
     *
     * @param user User
     */
    public void sendEventRSVPMsg(final User user);

    /**
     * Sends a slack message that an event has started
     *
     * @param user User
     */
    public void sendEventStartMsg(final User user);

    /**
     * Sends a slack message that a question has been asked
     *
     * @param user User
     */
    public void sendQuestionAskedMsg(final User user);

    /**
     * Sends a slack message for registering for an upcoming event
     *
     * @param user User
     */
    public void sendEventRegisterMsg(final User user);

    /**
     * Sends a slack message for unregistering from an upcoming event
     *
     * @param user User
     */
    public void sendEventUnregisterMsg(final User user);

    /**
     * Sends a slack message for user deletion
     *
     * @param user User
     */
    public void sendUserDeleteMsg(final User user);

    /**
     * Sends a Slack message for user settings verified
     *
     * @param user User
     */
    public void sendUserSettingsVerifiedMsg(final User user);

    /**
     * Sends a Slack message for user settings changed
     *
     * @param user User
     */
    public void sendUserSettingsChangeMsg(final User user);

}
