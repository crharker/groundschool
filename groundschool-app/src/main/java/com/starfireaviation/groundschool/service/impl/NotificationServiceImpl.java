/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.properties.EmailProperties;
import com.starfireaviation.groundschool.properties.SMSProperties;
import com.starfireaviation.groundschool.properties.SlackProperties;
import com.starfireaviation.groundschool.service.EmailService;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.SMSService;
import com.starfireaviation.groundschool.service.SlackService;
import com.starfireaviation.groundschool.service.UserService;

/**
 * NotificationServiceImpl
 *
 * @author brianmichael
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    /**
     * UserService
     */
    @Autowired
    private UserService userService;

    /**
     * EmailService
     */
    @Autowired
    private EmailService emailService;

    /**
     * SMSService
     */
    @Autowired
    private SMSService smsService;

    /**
     * SlackService
     */
    @Autowired
    private SlackService slackService;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private SMSProperties smsProperties;

    @Autowired
    private SlackProperties slackProperties;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(Long userId, NotificationEventType notificationEventType) {
        switch (notificationEventType) {
            case USER_SETTINGS:
                userSettings(userId);
                break;
            case USER_VERIFIED:
                userVerified(userId);
                break;
            case USER_DELETE:
                userDelete(userId);
                break;
            default:
                break;
        }
    }

    /**
     * Sends notification for user deletion
     *
     * @param userId Long
     */
    private void userDelete(Long userId) {
        final User user = userService.findById(userId);
        if (user.getEmail() != null && user.isEmailVerified()) {
            // TODO templatize subject and body
            emailService.send(
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    "Test subject",
                    "Test body");
        }
        if (user.getSms() != null && user.isSmsVerified()) {
            smsService.send(smsProperties.getFromAddress(), user.getSms(), "Test body");
        }
        if (user.getSlack() != null && user.isSlackVerified()) {
            slackService.send(slackProperties.getFromAddress(), user.getSlack(), "Test body");
        }
    }

    /**
     * Sends notification for user settings verification
     *
     * @param userId Long
     */
    private void userVerified(Long userId) {
        final User user = userService.findById(userId);
        if (user.getEmail() != null && user.isEmailVerified()) {
            // TODO templatize subject and body
            emailService.send(
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    "Test subject",
                    "Test body");
        }
        if (user.getSms() != null && user.isSmsVerified()) {
            smsService.send(smsProperties.getFromAddress(), user.getSms(), "Test body");
        }
        if (user.getSlack() != null && user.isSlackVerified()) {
            slackService.send(slackProperties.getFromAddress(), user.getSlack(), "Test body");
        }
    }

    /**
     * Sends notification for user settings change
     *
     * @param userId Long
     */
    private void userSettings(Long userId) {
        final User user = userService.findById(userId);
        if (user.getEmail() != null && user.isEmailVerified()) {
            // TODO templatize subject and body
            emailService.send(
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    "Test subject",
                    "Test body");
        }
        if (user.getSms() != null && user.isSmsVerified()) {
            smsService.send(smsProperties.getFromAddress(), user.getSms(), "Test body");
        }
        if (user.getSlack() != null && user.isSlackVerified()) {
            slackService.send(slackProperties.getFromAddress(), user.getSlack(), "Test body");
        }
    }

}
