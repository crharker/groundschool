/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;

/**
 * NotificationService
 *
 * @author brianmichael
 */
public interface NotificationService {

    /**
     * Sends a notification
     *
     * @param userId Long
     * @param notificationType NotificationType
     * @param notificationEventType NotificationEventType
     * @throws ResourceNotFoundException when no user is found
     */
    public void send(Long userId, NotificationType notificationType, NotificationEventType notificationEventType)
            throws ResourceNotFoundException;

    /**
     * Re-sends a notification
     *
     * @param userId Long
     * @param notificationType NotificationType
     * @param notificationEventType NotificationEventType
     * @param response given by user
     * @param originalMessage to be resent
     */
    public void resend(
            Long userId,
            NotificationType notificationType,
            NotificationEventType notificationEventType,
            String response,
            String originalMessage);

    /**
     * Invite
     *
     * @param userId Long
     * @param email Email address of user being invited
     */
    public void invite(Long userId, String email);
}
