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
    public void send(Long userId, NotificationEventType notificationEventType) {
        switch (notificationEventType) {
            case USER_SETTINGS:
                userSettings(userId);
                break;
            case USER_VERIFIED:
                userSettingsVerified(userId);
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
        if (user == null) {
            return;
        }
        if (user.getEmail() != null && user.isEmailVerified() && user.isEmailEnabled()) {
            try {
                freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
                emailService.send(
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
        if (user.getSms() != null && user.isSmsVerified() && user.isSmsEnabled()) {
            try {
                freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
                smsService.send(
                        smsProperties.getFromAddress(),
                        user.getSms(),
                        FreeMarkerTemplateUtils.processTemplateIntoString(
                                freemarkerConfig.getTemplate("user_delete.ftl"),
                                getModelForUser(user)));
            } catch (IOException | TemplateException e) {
                LOGGER.warn(e.getMessage());
            }
        }
        if (user.getSlack() != null && user.isSlackVerified() && user.isSlackEnabled()) {
            try {
                freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
                slackService.send(
                        slackProperties.getFromAddress(),
                        user.getSms(),
                        FreeMarkerTemplateUtils.processTemplateIntoString(
                                freemarkerConfig.getTemplate("user_delete.ftl"),
                                getModelForUser(user)));
            } catch (IOException | TemplateException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    /**
     * Sends notification for user settings verification
     *
     * @param userId Long
     */
    private void userSettingsVerified(Long userId) {
        final User user = userService.findById(userId);
        if (user == null) {
            return;
        }
        if (user.getEmail() != null && user.isEmailVerified() && user.isEmailEnabled()) {
            try {
                freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
                emailService.send(
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
        if (user.getSms() != null && user.isSmsVerified() && user.isSmsEnabled()) {
            try {
                freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
                smsService.send(
                        smsProperties.getFromAddress(),
                        user.getSms(),
                        FreeMarkerTemplateUtils.processTemplateIntoString(
                                freemarkerConfig.getTemplate("user_settings_verified.ftl"),
                                getModelForUser(user)));
            } catch (IOException | TemplateException e) {
                LOGGER.warn(e.getMessage());
            }
        }
        if (user.getSlack() != null && user.isSlackVerified() && user.isSlackEnabled()) {
            try {
                freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
                slackService.send(
                        slackProperties.getFromAddress(),
                        user.getSms(),
                        FreeMarkerTemplateUtils.processTemplateIntoString(
                                freemarkerConfig.getTemplate("user_settings_verified.ftl"),
                                getModelForUser(user)));
            } catch (IOException | TemplateException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    /**
     * Sends notification for user settings change
     *
     * @param userId Long
     */
    private void userSettings(Long userId) {
        final User user = userService.findById(userId);
        if (user == null) {
            return;
        }
        if (user.getEmail() != null && user.isEmailVerified() && user.isEmailEnabled()) {
            try {
                freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
                emailService.send(
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
        if (user.getSms() != null && user.isSmsVerified() && user.isSmsEnabled()) {
            try {
                freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
                smsService.send(
                        smsProperties.getFromAddress(),
                        user.getSms(),
                        FreeMarkerTemplateUtils.processTemplateIntoString(
                                freemarkerConfig.getTemplate("user_verify_settings.ftl"),
                                getModelForUser(user)));
            } catch (IOException | TemplateException e) {
                LOGGER.warn(e.getMessage());
            }
        }
        if (user.getSlack() != null && user.isSlackVerified() && user.isSlackEnabled()) {
            try {
                freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
                slackService.send(
                        slackProperties.getFromAddress(),
                        user.getSms(),
                        FreeMarkerTemplateUtils.processTemplateIntoString(
                                freemarkerConfig.getTemplate("user_verify_settings.ftl"),
                                getModelForUser(user)));
            } catch (IOException | TemplateException e) {
                LOGGER.warn(e.getMessage());
            }
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
