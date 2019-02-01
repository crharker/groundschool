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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.SMSMessage;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.properties.EmailProperties;
import com.starfireaviation.groundschool.service.MessageService;
import com.starfireaviation.groundschool.service.StatisticService;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * EmailServiceImpl
 *
 * @author brianmichael
 */
@Service("emailService")
public class EmailServiceImpl implements MessageService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    /**
     * MailSender
     */
    @Autowired
    private JavaMailSender mailSender;

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
     * EmailProperties
     */
    @Autowired
    private EmailProperties emailProperties;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserDeleteMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            send(
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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRSVPMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            send(
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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventStartMsg(final User user, final Event event) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            send(
                    user.getId(),
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_start_subject.ftl"),
                            getModelForUser(user)),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_start_body.ftl"),
                            getModelForUser(user)),
                    true);
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            send(
                    user.getId(),
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("question_subject.ftl"),
                            getModelForUser(user)),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("question_body.ftl"),
                            getModelForUser(user)),
                    true);
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            send(
                    user.getId(),
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_register_subject.ftl"),
                            getModelForUser(user)),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_register_body.ftl"),
                            getModelForUser(user)),
                    true);
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            send(
                    user.getId(),
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_unregister_subject.ftl"),
                            getModelForUser(user)),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("event_unregister_body.ftl"),
                            getModelForUser(user)),
                    true);
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            send(
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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserSettingsChangeMsg(final User user) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            send(
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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendInviteMsg(User user, String destination) {
        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            send(
                    user.getId(),
                    emailProperties.getFromAddress(),
                    destination,
                    null,
                    null,
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("invite_subject.ftl"),
                            getModelForUser(user)),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("invite_body.ftl"),
                            getModelForUser(user)),
                    true);
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
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
            send(
                    user.getId(),
                    emailProperties.getFromAddress(),
                    user.getEmail(),
                    null,
                    null,
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("password_reset_subject.ftl"),
                            getModelForUser(user)),
                    FreeMarkerTemplateUtils.processTemplateIntoString(
                            freemarkerConfig.getTemplate("password_reset_body.ftl"),
                            getModelForUser(user)),
                    true);
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
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
    public String receiveMessage(SMSMessage message) {
        String response = null;
        Instant start = Instant.now();
        LOGGER.info(String.format("receiveMessage() message received was [%s]", message));
        statisticService.store(
                new Statistic(
                        null,
                        null,
                        null,
                        null,
                        StatisticType.EMAIL_MESSAGE_RECEIVED,
                        String.format(
                                "Duration [%s]; Source [%s]; Message [%s]",
                                Duration.between(start, Instant.now()),
                                message.getFrom(),
                                message.getBody())));
        return response;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void clearMessageHistory(Long userId) {
        // Not implemented
    }

    /**
     * Sends an email
     *
     * @param userId user ID
     * @param fromAddress from address
     * @param toAddress to address
     * @param ccAddress cc address
     * @param bccAddress bcc address
     * @param subject subject
     * @param body body
     * @param html message body contains HTML?
     */
    private void send(
            Long userId,
            String fromAddress,
            String toAddress,
            String ccAddress,
            String bccAddress,
            String subject,
            String body,
            boolean html) {
        Instant start = Instant.now();
        LOGGER.info(
                String.format(
                        "Sending... fromAddress [%s]; toAddress [%s]; ccAddress [%s]; bccAddress [%s]; subject [%s]; body [%s]",
                        fromAddress,
                        toAddress,
                        ccAddress,
                        bccAddress,
                        subject,
                        body));
        try {
            final MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg);
            mimeMessageHelper.setTo(toAddress);
            if (ccAddress != null) {
                mimeMessageHelper.setCc(ccAddress);
            }
            if (bccAddress != null) {
                mimeMessageHelper.setBcc(bccAddress);
            }
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, html);
            mailSender.send(msg);
        } catch (MailException | MessagingException ex) {
            LOGGER.error(ex.getMessage());
        }
        statisticService.store(
                new Statistic(
                        userId,
                        null,
                        null,
                        null,
                        StatisticType.EMAIL_MESSAGE_SENT,
                        String.format(
                                "Duration [%s]; Destination [%s]; Subject [%s]; Body [%s]",
                                Duration.between(start, Instant.now()),
                                toAddress,
                                subject,
                                body)));
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
        model.put("code", user.getCode());
        model.put("host", "http://localhost:8080");
        return model;
    }

}
