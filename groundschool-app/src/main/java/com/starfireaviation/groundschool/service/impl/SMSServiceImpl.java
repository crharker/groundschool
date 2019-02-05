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
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.starfireaviation.groundschool.exception.InvalidPayloadException;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.SMSMessage;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.MessageEntity;
import com.starfireaviation.groundschool.properties.SMSProperties;
import com.starfireaviation.groundschool.service.StatisticService;
import com.starfireaviation.groundschool.util.TemplateUtil;
import com.starfireaviation.groundschool.validation.ResponseValidator;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * SMSServiceImpl
 *
 * @author brianmichael
 */
@Service("smsService")
public class SMSServiceImpl extends AbstractMessageServiceImpl {

    /**
     * SMSProperties
     */
    @Autowired
    private SMSProperties smsProperties;

    /**
     * StatisticService
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * FreeMarker Configuration
     */
    @Autowired
    private Configuration freemarkerConfig;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserDeleteMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.USER_DELETE,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_delete.ftl"),
                            TemplateUtil.getModel(user, null, null)));
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.QUIZ_COMPLETE,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("quiz_complete.ftl"),
                            TemplateUtil.getModel(user, null, null)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRSVPMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.EVENT_RSVP,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_rsvp.ftl"),
                            TemplateUtil.getModel(user, event, null)));
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.EVENT_START,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_start.ftl"),
                            TemplateUtil.getModel(user, event, null)));
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");

            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.QUESTION_ASKED,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("question.ftl"),
                            TemplateUtil.getModel(user, null, question)));
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.EVENT_REGISTER,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_register.ftl"),
                            TemplateUtil.getModel(user, event, null)));
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.EVENT_UNREGISTER,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_unregister.ftl"),
                            TemplateUtil.getModel(user, event, null)));
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.USER_VERIFIED,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_settings_verified.ftl"),
                            TemplateUtil.getModel(user, null, null)));
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.USER_SETTINGS,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("user_verify_settings.ftl"),
                            TemplateUtil.getModel(user, null, null)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void resendUserSettingsChangeMsg(
            final User user,
            String response,
            String originalMessage) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            Map<String, Object> model = TemplateUtil.getModel(user, null, null);
            model.put("response", response);
            model.put("original_message", originalMessage);
            send(
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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendPasswordResetMsg(User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/sms");
            Map<String, Object> model = TemplateUtil.getModel(user, null, null);
            model.put("code", user.getCode());
            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.PASSWORD_RESET,
                    smsProperties.getFromAddress(),
                    user.getSms(),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("password_reset.ftl"),
                            model));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public String receiveMessage(SMSMessage message) {
        String response = null;
        LOGGER.info(String.format("receiveMessage() message received was [%s]", message));
        try {
            ResponseValidator.validate(message.getBody());
        } catch (InvalidPayloadException e) {
            return response;
        }

        processUserResponse(stripCountryCode(message.getFrom()), message.getBody(), NotificationType.SMS);
        return response;
    }

    /**
     * Sends an SMS
     *
     * @param userId user ID
     * @param eventId event ID
     * @param quizId quiz ID
     * @param questionId question ID
     * @param type NotificationEventType
     * @param fromAddress from address
     * @param toAddress to address
     * @param body body
     */
    private void send(
            Long userId,
            Long eventId,
            Long quizId,
            Long questionId,
            NotificationEventType type,
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
        Twilio.init(smsProperties.getAccountSid(), smsProperties.getAuthId());
        Message.creator(new PhoneNumber(toAddress), new PhoneNumber(fromAddress), body).create();
        messageRepository.save(
                new MessageEntity(userId, eventId, quizId, questionId, toAddress, new Date(), body, type));
        statisticService.store(
                new Statistic(
                        userId,
                        eventId,
                        quizId,
                        questionId,
                        StatisticType.SMS_MESSAGE_SENT,
                        String.format(
                                "Duration [%s]; Destination [%s]; Type [%s]; Message [%s]",
                                Duration.between(start, Instant.now()),
                                toAddress,
                                type,
                                body)));
    }

}
