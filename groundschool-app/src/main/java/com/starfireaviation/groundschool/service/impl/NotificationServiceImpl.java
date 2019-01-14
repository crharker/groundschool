/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.User;
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
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

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

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(Long userId, NotificationType notificationType, NotificationEventType notificationEventType) {
        LOGGER.info(
                String.format(
                        "send() called with userId [%s]; notificationEventType [%s]",
                        userId,
                        notificationEventType));
        switch (notificationEventType) {
            case USER_SETTINGS:
                userSettings(userId, notificationType);
                break;
            case USER_VERIFIED:
                userSettingsVerified(userId, notificationType);
                break;
            case USER_DELETE:
                userDelete(userId, notificationType);
                break;
            case EVENT_RSVP:
                eventRSVP(userId, notificationType);
                break;
            case EVENT_START:
                eventStart(userId, notificationType);
                break;
            case EVENT_REGISTER:
                eventRegister(userId, notificationType);
                break;
            case EVENT_UNREGISTER:
                eventUnregister(userId, notificationType);
                break;
            case QUESTION_ASKED:
                questionAsked(userId, notificationType);
                break;
            default:
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void resend(
            Long userId,
            NotificationType notificationType,
            NotificationEventType notificationEventType,
            String response,
            String originalMessage) {
        if (NotificationType.SMS == notificationType && NotificationEventType.USER_SETTINGS == notificationEventType) {
            smsService.resendUserSettingsChangeMsg(userService.findById(userId), response, originalMessage);
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void invite(Long userId, String email) {
        if (userId == null) {
            return;
        }
        emailService.sendInviteMsg(userService.findById(userId), email);
    }

    /**
     * Sends notification for user settings verification
     *
     * @param userId Long
     * @param notificationType NotificationType
     */
    private void userSettingsVerified(Long userId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        if (user == null) {
            LOGGER.warn("userSettingsVerified() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendUserSettingsVerifiedMsg(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            user.setSmsVerified(true);
            userService.store(user);
            smsService.sendUserSettingsVerifiedMsg(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendUserSettingsVerifiedMsg(user);
        }
    }

    /**
     * Sends notification for user settings change
     *
     * @param userId Long
     * @param notificationType NotificationType
     */
    private void userSettings(Long userId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        if (user == null) {
            LOGGER.warn("userSettings() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && !user.isEmailVerified()
                && user.isEmailEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendUserSettingsChangeMsg(user);
        }
        if (user.getSms() != null
                && !user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendUserSettingsChangeMsg(user);
        }
        if (user.getSlack() != null
                && !user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendUserSettingsChangeMsg(user);
        }
        LOGGER.info("userSettings() complete");
    }

    /**
     * Sends notification for user deletion
     *
     * @param userId Long
     * @param notificationType NotificationType
     */
    private void userDelete(Long userId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        if (user == null) {
            LOGGER.warn("userDelete() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendUserDeleteMsg(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendUserDeleteMsg(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendUserDeleteMsg(user);
        }
    }

    /**
     * Sends notification that an event has started
     *
     * @param userId Long
     * @param notificationType NotificationType
     */
    private void eventStart(Long userId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        if (user == null) {
            LOGGER.warn("eventStart() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && user.isQuestionsViaEmail()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendEventStartMsg(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && user.isQuestionsViaSMS()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendEventStartMsg(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && user.isQuestionsViaSlack()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendEventStartMsg(user);
        }
    }

    /**
     * Sends notification that a question has been asked
     *
     * @param userId Long
     * @param notificationType NotificationType
     */
    private void questionAsked(Long userId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        if (user == null) {
            LOGGER.warn("questionAsked() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && user.isQuestionsViaEmail()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendQuestionAskedMsg(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && user.isQuestionsViaSMS()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendQuestionAskedMsg(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && user.isQuestionsViaSlack()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendQuestionAskedMsg(user);
        }
    }

    /**
     * Sends notification to RSVP for an upcoming event
     *
     * @param userId Long
     * @param notificationType NotificationType
     */
    private void eventRSVP(Long userId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        if (user == null) {
            LOGGER.warn("eventRSVP() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendEventRSVPMsg(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendEventRSVPMsg(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendEventRSVPMsg(user);
        }
    }

    /**
     * Sends notification of registration for an event
     *
     * @param userId Long
     * @param notificationType NotificationType
     */
    private void eventRegister(Long userId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        if (user == null) {
            LOGGER.warn("eventRegister() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendEventRegisterMsg(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendEventRegisterMsg(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendEventRegisterMsg(user);
        }
    }

    /**
     * Sends notification of de-registration from an event
     *
     * @param userId Long
     * @param notificationType NotificationType
     */
    private void eventUnregister(Long userId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        if (user == null) {
            LOGGER.warn("eventUnregister() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendEventUnregisterMsg(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendEventUnregisterMsg(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendEventUnregisterMsg(user);
        }
    }

}
