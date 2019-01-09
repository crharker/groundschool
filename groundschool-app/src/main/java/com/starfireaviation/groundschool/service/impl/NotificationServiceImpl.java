/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.properties.EmailProperties;
import com.starfireaviation.groundschool.properties.SMSProperties;
import com.starfireaviation.groundschool.properties.SlackProperties;
import com.starfireaviation.groundschool.service.EmailService;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.SMSService;
import com.starfireaviation.groundschool.service.SlackService;
import com.starfireaviation.groundschool.service.UserService;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

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
     * EmailProperties
     */
    @Autowired
    private EmailProperties emailProperties;

    /**
     * SMSProperties
     */
    @Autowired
    private SMSProperties smsProperties;

    /**
     * SlackProperties
     */
    @Autowired
    private SlackProperties slackProperties;

    /**
     * FreeMarker Configuration
     */
    @Autowired
    private Configuration freemarkerConfig;

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
            resendUserSettingsChangeSMS(userService.findById(userId), response, originalMessage);
        }
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
            sendUserSettingsVerifiedEmail(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            sendUserSettingsVerifiedSMS(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            sendUserSettingsVerifiedSlackMessage(user);
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
            sendUserSettingsChangeEmail(user);
        }
        if (user.getSms() != null
                && !user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            sendUserSettingsChangeSMS(user);
        }
        if (user.getSlack() != null
                && !user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            sendUserSettingsChangeSlackMessage(user);
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
            sendUserDeleteEmail(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            sendUserDeleteSMS(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            sendUserDeleteSlackMessage(user);
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
            LOGGER.warn("userDelete() returning as no user was found");
            return;
        }
        if (user.getEmail() != null
                && user.isEmailVerified()
                && user.isEmailEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.EMAIL)) {
            sendEventRSVPEmail(user);
        }
        if (user.getSms() != null
                && user.isSmsVerified()
                && user.isSmsEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SMS)) {
            sendEventRSVPSMS(user);
        }
        if (user.getSlack() != null
                && user.isSlackVerified()
                && user.isSlackEnabled()
                && (notificationType == null
                        || notificationType == NotificationType.ALL
                        || notificationType == NotificationType.SLACK)) {
            sendEventRSVPSlackMessage(user);
        }
    }

    /**
     * Sends a slack message to RSVP for an upcoming event
     *
     * @param user User
     */
    private void sendEventRSVPSlackMessage(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            slackService.send(
                    user.getId(),
                    slackProperties.getFromAddress(),
                    user.getSlack(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_rsvp.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends a slack message for user deletion
     *
     * @param user User
     */
    private void sendUserDeleteSlackMessage(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            slackService.send(
                    user.getId(),
                    slackProperties.getFromAddress(),
                    user.getSlack(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_delete.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends an SMS message for user deletion
     *
     * @param user User
     */
    private void sendUserDeleteSMS(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            smsService.send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.USER_DELETE,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_delete.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends an SMS message to RSVP for an upcoming event
     *
     * @param user User
     */
    private void sendEventRSVPSMS(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            smsService.send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.EVENT_RSVP,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_rsvp.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends an email for user deletion
     *
     * @param user User
     */
    private void sendUserDeleteEmail(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            emailService.send(
                    user.getId(),
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_delete_subject.ftl"),
                            getModelForUser(user)),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_delete_body.ftl"),
                            getModelForUser(user)),
                    true);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends an email to RSVP for an upcoming event
     *
     * @param user User
     */
    private void sendEventRSVPEmail(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            emailService.send(
                    user.getId(),
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_rsvp_subject.ftl"),
                            getModelForUser(user)),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_rsvp_body.ftl"),
                            getModelForUser(user)),
                    true);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends a Slack message for user settings verified
     *
     * @param user User
     */
    private void sendUserSettingsVerifiedSlackMessage(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            slackService.send(
                    user.getId(),
                    slackProperties.getFromAddress(),
                    user.getSlack(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_settings_verified.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends a SMS message for user settings verified
     *
     * @param user User
     */
    private void sendUserSettingsVerifiedSMS(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            smsService.send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.USER_VERIFIED,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_settings_verified.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends an email message for user settings verified
     *
     * @param user User
     */
    private void sendUserSettingsVerifiedEmail(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            emailService.send(
                    user.getId(),
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_settings_verified_subject.ftl"),
                            getModelForUser(user)),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_settings_verified_body.ftl"),
                            getModelForUser(user)),
                    true);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends a Slack message for user settings changed
     *
     * @param user User
     */
    private void sendUserSettingsChangeSlackMessage(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            slackService.send(
                    user.getId(),
                    slackProperties.getFromAddress(),
                    user.getSlack(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_verify_settings.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends a SMS message for user settings changed
     *
     * @param user User
     */
    private void sendUserSettingsChangeSMS(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            smsService.send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.USER_SETTINGS,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_verify_settings.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Resends a SMS message for user settings changed
     *
     * @param user User
     * @param response given by the user
     * @param originalMessage sent to the user
     */
    private void resendUserSettingsChangeSMS(
            final User user,
            String response,
            String originalMessage) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            Map<String, Object> model = getModelForUser(user);
            model.put("response", response);
            model.put("original_message", originalMessage);
            smsService.send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.USER_SETTINGS,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("resend_header.ftl"),
                            model));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Sends an email message for user settings changed
     *
     * @param user User
     */
    private void sendUserSettingsChangeEmail(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            emailService.send(
                    user.getId(),
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_verify_settings_subject.ftl"),
                            getModelForUser(user)),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_verify_settings_body.ftl"),
                            getModelForUser(user)),
                    true);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Builds model from User information
     *
     * @param user User
     * @return model
     */
    private static Map<String, Object> getModelForUser(final User user) {
        Map<String, Object> model = new HashMap<>();
        model.put("firstName", user.getFirstName());
        model.put("lastName", user.getLastName());
        model.put("userId", user.getId());
        model.put("host", "http://localhost:8080");
        return model;
    }

}
