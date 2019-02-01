/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.QuestionPreference;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.MessageService;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.QuestionService;
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
     * EventService
     */
    @Autowired
    private EventService eventService;

    /**
     * QuestionService
     */
    @Autowired
    private QuestionService questionService;

    /**
     * EmailService
     */
    @Autowired
    @Qualifier("emailService")
    private MessageService emailService;

    /**
     * SMSService
     */
    @Autowired
    @Qualifier("smsService")
    private MessageService smsService;

    /**
     * SlackService
     */
    @Autowired
    @Qualifier("gsSlackService")
    private MessageService slackService;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(
            Long userId,
            Long eventId,
            Long questionId,
            NotificationType notificationType,
            NotificationEventType notificationEventType)
            throws ResourceNotFoundException {
        LOGGER.info(
                String.format(
                        "send() called with userId [%s]; notificationEventType [%s]",
                        userId,
                        notificationEventType));
        switch (notificationEventType) {
            case PASSWORD_RESET:
                passwordReset(userId, notificationType);
                break;
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
                eventRSVP(userId, eventId, notificationType);
                break;
            case EVENT_START:
                eventStart(userId, eventId, notificationType);
                break;
            case EVENT_REGISTER:
                eventRegister(userId, eventId, notificationType);
                break;
            case EVENT_UNREGISTER:
                eventUnregister(userId, eventId, notificationType);
                break;
            case QUESTION_ASKED:
                questionAsked(userId, questionId, notificationType);
                break;
            case QUIZ_COMPLETE:
                quizComplete(userId, notificationType);
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
     * @throws ResourceNotFoundException when no user is found
     */
    private void userSettingsVerified(Long userId, NotificationType notificationType) throws ResourceNotFoundException {
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
     * Sends notification for user password reset
     *
     * @param userId Long
     * @param notificationType NotificationType
     */
    private void passwordReset(Long userId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        if (user == null) {
            LOGGER.warn("passwordReset() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && !user.isEmailVerified()
                && user.isEmailEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendPasswordResetMsg(user);
        }
        if (user.getSms() != null
                && !user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendPasswordResetMsg(user);
        }
        if (user.getSlack() != null
                && !user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendPasswordResetMsg(user);
        }
        LOGGER.info("passwordReset() complete");
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
     * @param eventId Long
     * @param notificationType NotificationType
     */
    private void eventStart(Long userId, Long eventId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        final Event event = eventService.findById(eventId, true);
        if (user == null) {
            LOGGER.warn("eventStart() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && user.getQuestionPreference() == QuestionPreference.EMAIL
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendEventStartMsg(user, event);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && user.getQuestionPreference() == QuestionPreference.SMS
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendEventStartMsg(user, event);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && user.getQuestionPreference() == QuestionPreference.SLACK
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendEventStartMsg(user, event);
        }
    }

    /**
     * Sends notification that a question has been asked
     *
     * @param userId Long
     * @param questionId Long
     * @param notificationType NotificationType
     */
    private void questionAsked(Long userId, Long questionId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        final Question question = questionService.findById(questionId, false);
        if (user == null) {
            LOGGER.warn("questionAsked() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && user.getQuestionPreference() == QuestionPreference.EMAIL
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendQuestionAskedMsg(user, question);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && user.getQuestionPreference() == QuestionPreference.SMS
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendQuestionAskedMsg(user, question);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && user.getQuestionPreference() == QuestionPreference.SLACK
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendQuestionAskedMsg(user, question);
        }
    }

    /**
     * Sends notification that a question has been asked
     *
     * @param userId Long
     * @param notificationType NotificationType
     */
    private void quizComplete(Long userId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        if (user == null) {
            LOGGER.warn("questionAsked() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && user.getQuestionPreference() == QuestionPreference.EMAIL
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            emailService.sendQuizCompleteMsg(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && user.getQuestionPreference() == QuestionPreference.SMS
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendQuizCompleteMsg(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && user.getQuestionPreference() == QuestionPreference.SLACK
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendQuizCompleteMsg(user);
        }
    }

    /**
     * Sends notification to RSVP for an upcoming event
     *
     * @param userId Long
     * @param eventId Long
     * @param notificationType NotificationType
     */
    private void eventRSVP(Long userId, Long eventId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        final Event event = eventService.findById(eventId, true);
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
            emailService.sendEventRSVPMsg(user, event);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendEventRSVPMsg(user, event);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendEventRSVPMsg(user, event);
        }
    }

    /**
     * Sends notification of registration for an event
     *
     * @param userId Long
     * @param eventId Long
     * @param notificationType NotificationType
     */
    private void eventRegister(Long userId, Long eventId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        final Event event = eventService.findById(eventId, true);
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
            emailService.sendEventRegisterMsg(user, event);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendEventRegisterMsg(user, event);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendEventRegisterMsg(user, event);
        }
    }

    /**
     * Sends notification of de-registration from an event
     *
     * @param userId Long
     * @param eventId Long
     * @param notificationType NotificationType
     */
    private void eventUnregister(Long userId, Long eventId, NotificationType notificationType) {
        final User user = userService.findById(userId);
        final Event event = eventService.findById(eventId, true);
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
            emailService.sendEventUnregisterMsg(user, event);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            smsService.sendEventUnregisterMsg(user, event);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            slackService.sendEventUnregisterMsg(user, event);
        }
    }

}
