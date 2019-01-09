/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

/**
 * SlackService
 *
 * @author brianmichael
 */
public interface SlackService {

    /**
     * Sends a Slack message
     *
     * @param userId user ID
     * @param fromAddress from address
     * @param toAddress to address
     * @param body body
     */
    public void send(
            Long userId,
            String fromAddress,
            String toAddress,
            String body);

}
