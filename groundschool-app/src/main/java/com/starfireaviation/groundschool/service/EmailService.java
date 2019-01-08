/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

/**
 * EmailService
 *
 * @author brianmichael
 */
public interface EmailService {

    /**
     * Sends an email
     *
     * @param fromAddress from address
     * @param toAddress to address
     * @param ccAddress cc address
     * @param bccAddress bcc address
     * @param subject subject
     * @param body body
     * @param html message body contains HTML?
     */
    public void send(
            String fromAddress,
            String toAddress,
            String ccAddress,
            String bccAddress,
            String subject,
            String body,
            boolean html);

}
