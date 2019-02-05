/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.starfireaviation.groundschool.exception.InvalidPayloadException;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.MessageEntity;
import com.starfireaviation.groundschool.properties.SlackProperties;
import com.starfireaviation.groundschool.service.StatisticService;
import com.starfireaviation.groundschool.util.TemplateUtil;
import com.starfireaviation.groundschool.validation.ResponseValidator;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * SlackServiceImpl
 *
 * @author brianmichael
 */
@Service("gsSlackService")
public class SlackServiceImpl extends AbstractMessageServiceImpl implements SlackMessagePostedListener {

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
     * SlackSession
     */
    private SlackSession slackSession = null;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRSVPMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("event_rsvp.ftl"),
                    TemplateUtil.getModel(user, event, null));
            send(user, event, null, null, message, NotificationEventType.EVENT_RSVP);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventStartMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("event_start.ftl"),
                    TemplateUtil.getModel(user, event, null));
            send(user, event, null, null, message, NotificationEventType.EVENT_START);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendQuestionAskedMsg(final User user, final Question question) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("question.ftl"),
                    TemplateUtil.getModel(user, null, question));
            send(user, null, null, question, message, NotificationEventType.QUESTION_ASKED);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRegisterMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("event_register.ftl"),
                    TemplateUtil.getModel(user, event, null));
            send(user, event, null, null, message, NotificationEventType.EVENT_REGISTER);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventUnregisterMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("event_unregister.ftl"),
                    TemplateUtil.getModel(user, event, null));
            send(user, event, null, null, message, NotificationEventType.EVENT_UNREGISTER);
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

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("user_delete.ftl"),
                    TemplateUtil.getModel(user, null, null));
            send(user, null, null, null, message, NotificationEventType.USER_DELETE);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendQuizCompleteMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("quiz_complete.ftl"),
                    TemplateUtil.getModel(user, null, null));
            send(user, null, null, null, message, NotificationEventType.QUIZ_COMPLETE);
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

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("user_settings_verified.ftl"),
                    TemplateUtil.getModel(user, null, null));
            send(user, null, null, null, message, NotificationEventType.USER_VERIFIED);
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

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("user_verify_settings.ftl"),
                    TemplateUtil.getModel(user, null, null));
            send(user, null, null, null, message, NotificationEventType.USER_SETTINGS);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendPasswordResetMsg(User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/slack");

            final String message = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfig.getTemplate("password_reset.ftl"),
                    TemplateUtil.getModel(user, null, null));
            send(user, null, null, null, message, NotificationEventType.PASSWORD_RESET);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void onEvent(SlackMessagePosted event, SlackSession session) {
        // Ignore bot user messages
        if (session.sessionPersona().getId().equals(event.getSender().getId())) {
            return;
        }
        final String message = event.getMessageContent();
        try {
            ResponseValidator.validate(message);
        } catch (InvalidPayloadException e) {
            return;
        }
        final String user = event.getUser().getUserName();
        LOGGER.info(
                String.format(
                        "Slack message received: user [%s]; message [%s]",
                        user,
                        message));
        processUserResponse(user, message, NotificationType.SLACK);
    }

    /**
     * Disconnects SlackSession
     */
    public void shutdownSlackSession() {
        if (slackSession != null && slackSession.isConnected()) {
            try {
                slackSession.disconnect();
            } catch (IOException e) {
                LOGGER.warn("Unable to disconnect SlackSession", e);
            }
        }
    }

    /**
     * Initializes SlackSession
     */
    private void initSlackSession() {
        if (slackSession == null || !slackSession.isConnected()) {
            if (slackSession == null) {
                slackSession = SlackSessionFactory.createWebSocketSlackSession(slackProperties.getToken());
            }
            if (!slackSession.isConnected()) {
                try {
                    slackSession.connect();
                    slackSession.addMessagePostedListener(this);
                } catch (IOException e) {
                    LOGGER.warn("Unable to connect to Slack", e);
                }
            }
        }
    }

    /**
     * Sends message to Slack
     *
     * @param user User
     * @param event Event
     * @param quiz Quiz
     * @param question Question
     * @param message message to be sent
     * @param notificationEventType NotificationEventType
     */
    private void send(
            final User user,
            final Event event,
            final Quiz quiz,
            final Question question,
            final String message,
            NotificationEventType notificationEventType) {
        Instant start = Instant.now();
        initSlackSession();
        final SlackUser slackUser = slackSession.findUserByUserName(user.getSlack());
        slackSession.sendMessageToUser(slackUser, message, null);
        messageRepository.save(
                new MessageEntity(
                        user.getId(),
                        event == null ? null : event.getId(),
                        quiz == null ? null : quiz.getId(),
                        question == null ? null : question.getId(),
                        user.getSlack(),
                        new Date(),
                        message,
                        notificationEventType));
        statisticService.store(
                new Statistic(
                        user.getId(),
                        event == null ? null : event.getId(),
                        quiz == null ? null : quiz.getId(),
                        question == null ? null : question.getId(),
                        StatisticType.SLACK_MESSAGE_SENT,
                        String.format(
                                "Duration [%s]; Message [%s]; Channel [%s]",
                                Duration.between(start, Instant.now()),
                                message,
                                user.getSlack())));
    }

}
