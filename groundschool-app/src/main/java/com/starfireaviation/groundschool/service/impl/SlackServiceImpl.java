/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.properties.SlackProperties;
import com.starfireaviation.groundschool.service.SlackService;
import com.starfireaviation.groundschool.service.StatisticService;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * SlackServiceImpl
 *
 * @author brianmichael
 */
@Service
public class SlackServiceImpl implements SlackService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SlackServiceImpl.class);

    /**
     * StatisticService
     */
    @Autowired
    private StatisticService statisticService;

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
    public void sendEventRSVPMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            send(
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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventStartMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            send(
                    user.getId(),
                    slackProperties.getFromAddress(),
                    user.getSlack(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_start.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendQuestionAskedMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            send(
                    user.getId(),
                    slackProperties.getFromAddress(),
                    user.getSlack(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("question.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRegisterMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            send(
                    user.getId(),
                    slackProperties.getFromAddress(),
                    user.getSlack(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_register.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventUnregisterMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            send(
                    user.getId(),
                    slackProperties.getFromAddress(),
                    user.getSlack(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_unregister.ftl"),
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserDeleteMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            send(
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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserSettingsVerifiedMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            send(
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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserSettingsChangeMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");
            send(
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
     * Sends a Slack message
     *
     * @param userId user ID
     * @param fromAddress from address
     * @param toAddress to address
     * @param body body
     */
    private void send(
            Long userId,
            String fromAddress,
            String toAddress,
            String body) {
        Instant start = Instant.now();
        LOGGER.info(
                String.format(
                        "Sending... fromAddress [%s]; toAddress [%s]; body [%s]",
                        fromAddress,
                        toAddress,
                        body));
        Statistic statistic = new Statistic(
                StatisticType.SLACK_MESSAGE_SENT,
                String.format(
                        "Duration [%s]; Destination [%s]; Message [%s]",
                        Duration.between(start, Instant.now()),
                        toAddress,
                        body));
        statistic.setUserId(userId);
        statisticService.store(statistic);
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
