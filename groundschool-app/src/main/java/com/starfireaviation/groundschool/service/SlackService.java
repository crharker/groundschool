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
     * @param fromAddress from address
     * @param toAddress to address
     * @param body body
     */
    public void send(
            String fromAddress,
            String toAddress,
            String body);

}
