/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

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
     * @param notificationEventType NotificationEventType
     */
    public void send(Long userId, NotificationEventType notificationEventType);

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
}
