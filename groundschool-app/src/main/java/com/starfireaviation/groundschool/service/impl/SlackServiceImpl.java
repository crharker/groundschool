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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starfireaviation.groundschool.model.Message;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.properties.SlackProperties;
import com.starfireaviation.groundschool.service.MessageService;
import com.starfireaviation.groundschool.service.StatisticService;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import me.ramswaroop.jbot.core.slack.models.RichMessage;

/**
 * SlackServiceImpl
 *
 * @author brianmichael
 */
@Service("gsSlackService")
public class SlackServiceImpl implements MessageService {

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
     * RestTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

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

            RichMessage richMessage = new RichMessage(
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_rsvp.ftl"),
                            getModelForUser(user)));
            richMessage.setUsername(slackProperties.getFromAddress());
            richMessage.setChannel("@" + user.getSlack());

            send(user.getId(), richMessage);
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

            RichMessage richMessage = new RichMessage(
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_start.ftl"),
                            getModelForUser(user)));
            richMessage.setUsername(slackProperties.getFromAddress());
            richMessage.setChannel("@" + user.getSlack());

            send(user.getId(), richMessage);
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

            RichMessage richMessage = new RichMessage(
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("question.ftl"),
                            getModelForUser(user)));
            richMessage.setUsername(slackProperties.getFromAddress());
            richMessage.setChannel("@" + user.getSlack());

            send(user.getId(), richMessage);
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

            RichMessage richMessage = new RichMessage(
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_register.ftl"),
                            getModelForUser(user)));
            richMessage.setUsername(slackProperties.getFromAddress());
            richMessage.setChannel("@" + user.getSlack());

            send(user.getId(), richMessage);
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

            RichMessage richMessage = new RichMessage(
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_unregister.ftl"),
                            getModelForUser(user)));
            richMessage.setUsername(slackProperties.getFromAddress());
            richMessage.setChannel("@" + user.getSlack());

            send(user.getId(), richMessage);
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

            RichMessage richMessage = new RichMessage(
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_delete.ftl"),
                            getModelForUser(user)));
            richMessage.setUsername(slackProperties.getFromAddress());
            richMessage.setChannel("@" + user.getSlack());

            send(user.getId(), richMessage);
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

            RichMessage richMessage = new RichMessage(
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_settings_verified.ftl"),
                            getModelForUser(user)));
            richMessage.setUsername(slackProperties.getFromAddress());
            richMessage.setChannel("@" + user.getSlack());

            send(user.getId(), richMessage);
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

            RichMessage richMessage = new RichMessage(
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_verify_settings.ftl"),
                            getModelForUser(user)));
            richMessage.setUsername(slackProperties.getFromAddress());
            richMessage.setChannel("@" + user.getSlack());

            send(user.getId(), richMessage);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendPasswordResetMsg(User user) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendInviteMsg(User user, String destination) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void resendUserSettingsChangeMsg(User user, String response, String originalMessage) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public String receiveMessage(Message message) {
        String response = null;
        Instant start = Instant.now();
        LOGGER.info(String.format("receiveMessage() message received was [%s]", message));
        Statistic statistic = new Statistic(
                StatisticType.SLACK_MESSAGE_RECEIVED,
                String.format(
                        "Duration [%s]; Source [%s]; Message [%s]",
                        Duration.between(start, Instant.now()),
                        message.getFrom(),
                        message.getBody()));
        statisticService.store(statistic);
        return response;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void closeAllMessages(Long userId) {
        // Not implemented
    }

    /**
     * Sends a Slack message
     *
     * @param userId User ID
     * @param richMessage RichMessage
     */
    private void send(Long userId, RichMessage richMessage) {
        Instant start = Instant.now();
        try {
            LOGGER.info(
                    String.format(
                            "Sending (RichMessage): [%s] to [%s]",
                            new ObjectMapper().writeValueAsString(richMessage),
                            slackProperties.getWebhookUrl()));
        } catch (JsonProcessingException e) {
            LOGGER.debug("Error parsing RichMessage: ", e);
        }
        try {
            restTemplate.postForEntity(slackProperties.getWebhookUrl(), richMessage.encodedMessage(), String.class);
        } catch (RestClientException e) {
            LOGGER.error("Error posting to Slack Incoming Webhook: " + e.getMessage());
        }
        Statistic statistic = new Statistic(
                StatisticType.SLACK_MESSAGE_SENT,
                String.format(
                        "Duration [%s]; Message [%s]; Channel [%s]; Username [%s]",
                        Duration.between(start, Instant.now()),
                        richMessage.getText(),
                        richMessage.getChannel(),
                        richMessage.getUsername()));
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
