/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import com.starfireaviation.groundschool.model.NotificationEventType;

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

}
