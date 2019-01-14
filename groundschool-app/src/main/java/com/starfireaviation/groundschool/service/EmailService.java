/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import com.starfireaviation.groundschool.model.User;

/**
 * EmailService
 *
 * @author brianmichael
 */
public interface EmailService {

    /**
     * Sends an email for user deletion
     *
     * @param user User
     */
    public void sendUserDeleteMsg(final User user);

    /**
     * Sends an email to RSVP for an upcoming event
     *
     * @param user User
     */
    public void sendEventRSVPMsg(final User user);

    /**
     * Sends an email that an event has started
     *
     * @param user User
     */
    public void sendEventStartMsg(final User user);

    /**
     * Sends an email that a question has been asked
     *
     * @param user User
     */
    public void sendQuestionAskedMsg(final User user);

    /**
     * Sends an email for registering for an upcoming event
     *
     * @param user User
     */
    public void sendEventRegisterMsg(final User user);

    /**
     * Sends an email for unregistering from an upcoming event
     *
     * @param user User
     */
    public void sendEventUnregisterMsg(final User user);

    /**
     * Sends an email message for user settings verified
     *
     * @param user User
     */
    public void sendUserSettingsVerifiedMsg(final User user);

    /**
     * Sends an email message for user settings changed
     *
     * @param user User
     */
    public void sendUserSettingsChangeMsg(final User user);

    /**
     * Sends an invitation email
     *
     * @param user User
     * @param email email address
     */
    public void sendInviteMsg(final User user, String email);
}
