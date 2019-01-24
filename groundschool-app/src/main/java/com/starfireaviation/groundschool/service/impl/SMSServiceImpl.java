/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.CollectionUtils;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.SMSResponseOption;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.SMSMessageEntity;
import com.starfireaviation.groundschool.properties.SMSProperties;
import com.starfireaviation.groundschool.repository.SMSMessageRepository;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.MessageService;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.StatisticService;
import com.starfireaviation.groundschool.service.UserService;
import com.starfireaviation.groundschool.util.SMSResponseParser;
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
public class SMSServiceImpl implements MessageService {

    /**
     * TN_PATTERN
     */
    private static final Pattern TN_PATTERN = Pattern.compile(".*\\+1(\\d{10}).*");

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SMSServiceImpl.class);

    /**
     * SMSMessageRepository
     */
    @Autowired
    private SMSMessageRepository smsMessageRepository;

    /**
     * SMSProperties
     */
    @Autowired
    private SMSProperties smsProperties;

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
     * NotificationService
     */
    @Autowired
    private NotificationService notificationService;

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
                            getModelForUser(user)));
        } catch (IOException | TemplateException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRSVPMsg(final User user) {
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
                            getModelForUser(user)));
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
            Map<String, Object> model = getModelForUser(user);
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
            Map<String, Object> model = getModelForUser(user);
            model.put("code", user.getCode());
            send(
                    user.getId(),
                    null,
                    null,
                    null,
                    NotificationEventType.USER_SETTINGS,
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
        Message message = Message.creator(new PhoneNumber(toAddress), new PhoneNumber(fromAddress), body).create();
        LOGGER.info(String.format("Status [%s]", message.getStatus()));
        smsMessageRepository.save(
                new SMSMessageEntity(userId, eventId, quizId, questionId, toAddress, new Date(), body, type));
        Statistic statistic = new Statistic(
                StatisticType.SMS_MESSAGE_SENT,
                String.format(
                        "Duration [%s]; Destination [%s]; Type [%s]; Message [%s]",
                        Duration.between(start, Instant.now()),
                        toAddress,
                        type,
                        body));
        statistic.setUserId(userId);
        statisticService.store(statistic);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public String receiveMessage(com.starfireaviation.groundschool.model.Message message) {
        Instant start = Instant.now();
        LOGGER.info(String.format("receiveMessage() message received was [%s]", message));
        Long userId = null;
        Long eventId = null;
        String response = replyWithNoOpenMessagesMsg();
        try {
            final List<SMSMessageEntity> smsMessageEntities = smsMessageRepository.findByTo(
                    stripCountryCode(message.getFrom()));
            if (!CollectionUtils.isEmpty(smsMessageEntities)) {
                List<SMSMessageEntity> sortedSMSMessages = sortByTime(smsMessageEntities);
                SMSMessageEntity smsMessageEntity = sortedSMSMessages.get(0);
                if (smsMessageEntity.isOpen()) {
                    userId = smsMessageEntity.getUserId();
                    eventId = smsMessageEntity.getEventId();
                    closeAllMessages(userId);
                    boolean success = false;
                    switch (smsMessageEntity.getNotificationEventType()) {
                        case USER_SETTINGS:
                            success = processUserSettingsResponse(userId, message);
                            break;
                        case USER_VERIFIED:
                            success = processUserVerifiedResponse(userId, message);
                            break;
                        case USER_DELETE:
                            success = processUserDeletedResponse(userId, message);
                            break;
                        case EVENT_RSVP:
                            success = processEventRSVPResponse(eventId, userId, message);
                            break;
                        default:
                            break;
                    }
                    if (!success) {
                        notificationService.resend(
                                userId,
                                NotificationType.SMS,
                                smsMessageEntity.getNotificationEventType(),
                                message.getBody(),
                                smsMessageEntity.getMessage());
                    }
                } else {
                    final String body = message.getBody();
                    if (body != null && SMSResponseOption.STOP == SMSResponseParser.determineResponse(body)) {
                        handleStop(userId);
                    }
                }
            }
            Statistic statistic = new Statistic(
                    StatisticType.SMS_MESSAGE_RECEIVED,
                    String.format(
                            "Duration [%s]; Source [%s]; Message [%s]",
                            Duration.between(start, Instant.now()),
                            message.getFrom(),
                            message.getBody()));
            statistic.setUserId(userId);
            statisticService.store(statistic);
        } catch (ResourceNotFoundException e) {
            LOGGER.warn(String.format("No user found for user ID [%s]", userId));
        }
        return response;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void closeAllMessages(Long userId) {
        Instant start = Instant.now();
        List<SMSMessageEntity> smsMessageEntities = smsMessageRepository.findByUserId(userId);
        for (SMSMessageEntity smsMessageEntity : smsMessageEntities) {
            if (smsMessageEntity.isOpen()) {
                smsMessageEntity.setOpen(false);
                smsMessageRepository.save(smsMessageEntity);
            }
        }
        Statistic statistic = new Statistic(
                StatisticType.SMS_ALL_MESSAGES_CLOSED,
                String.format(
                        "Duration [%s];",
                        Duration.between(start, Instant.now())));
        statistic.setUserId(userId);
        statisticService.store(statistic);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendInviteMsg(User user, String destination) {
        // Not implemented
    }

    /**
     * Sorts SMSMessageEntity by time in reverse order (latest message first)
     *
     * @param smsMessageEntities list of SMSMessageEntity
     * @return sorted list
     */
    private static List<SMSMessageEntity> sortByTime(List<SMSMessageEntity> smsMessageEntities) {
        Map<Date, SMSMessageEntity> map = new TreeMap<>(Collections.reverseOrder());
        for (SMSMessageEntity smsMessageEntity : smsMessageEntities) {
            map.put(smsMessageEntity.getTime(), smsMessageEntity);
        }
        List<SMSMessageEntity> response = new ArrayList<>();
        for (Date time : map.keySet()) {
            response.add(map.get(time));
        }
        return response;
    }

    /**
     * Strips the country code from a phone number
     *
     * @param from number
     * @return phone number minus country code
     */
    private static String stripCountryCode(String from) {
        // TODO not quite working right
        LOGGER.info(String.format("stripCountryCode() called with [%s]", from));
        if (from == null || from.length() == 10) {
            return from;
        }
        String response = from;
        Matcher matcher = TN_PATTERN.matcher(from);
        if (matcher.find()) {
            response = matcher.group(1);
        }
        LOGGER.info(String.format("stripCountryCode() returning [%s]", response));
        return response;
    }

    /**
     * @return no open messages message
     */
    private static String replyWithNoOpenMessagesMsg() {
        // Do nothing
        return null;
    }

    /**
     * Process user verified response
     *
     * @param userId user ID
     * @param message to be processed
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    private boolean processUserVerifiedResponse(Long userId, com.starfireaviation.groundschool.model.Message message)
            throws ResourceNotFoundException {
        final String body = message.getBody();
        if (body != null && SMSResponseOption.STOP == SMSResponseParser.determineResponse(body)) {
            handleStop(userId);
        }
        return true;
    }

    /**
     * Process user settings response
     *
     * @param userId user ID
     * @param message to be processed
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    private boolean processUserSettingsResponse(Long userId, com.starfireaviation.groundschool.model.Message message)
            throws ResourceNotFoundException {
        boolean success = true;
        User user = null;
        // STOP, CONFIRM, DECLINE, other
        final String body = message.getBody();
        if (body != null) {
            switch (SMSResponseParser.determineResponse(body)) {
                case STOP:
                    handleStop(userId);
                    break;
                case CONFIRM:
                    user = userService.findById(userId);
                    user.setSmsVerified(true);
                    userService.store(user);
                    notificationService.send(userId, NotificationType.SMS, NotificationEventType.USER_VERIFIED);
                    break;
                case DECLINE:
                    user = userService.findById(userId);
                    user.setSmsEnabled(false);
                    userService.store(user);
                    break;
                default:
                    success = false;
            }
        }
        return success;
    }

    /**
     * Handles STOP SMSResponseOption
     *
     * @param userId user ID
     * @throws ResourceNotFoundException when no user is found
     */
    private void handleStop(Long userId) throws ResourceNotFoundException {
        User user = userService.findById(userId);
        user.setSmsEnabled(false);
        userService.store(user);
    }

    /**
     * Process user deleted response
     *
     * @param userId user ID
     * @param message to be processed
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    private boolean processUserDeletedResponse(Long userId, com.starfireaviation.groundschool.model.Message message)
            throws ResourceNotFoundException {
        final String body = message.getBody();
        if (body != null && SMSResponseOption.STOP == SMSResponseParser.determineResponse(body)) {
            handleStop(userId);
        }
        return true;
    }

    /**
     * Process event RSVP response
     *
     * @param eventId event ID
     * @param userId user ID
     * @param message to be processed
     * @return success
     * @throws ResourceNotFoundException when no user is found
     */
    private boolean processEventRSVPResponse(
            Long eventId,
            Long userId,
            com.starfireaviation.groundschool.model.Message message) throws ResourceNotFoundException {
        boolean success = true;
        // STOP, CONFIRM, DECLINE, other
        final String body = message.getBody();
        if (body != null) {
            switch (SMSResponseParser.determineResponse(body)) {
                case STOP:
                    handleStop(userId);
                    break;
                case CONFIRM:
                    eventService.rsvp(eventId, userId, true);
                    break;
                case DECLINE:
                    eventService.rsvp(eventId, userId, false);
                    break;
                default:
                    success = false;
            }
        }
        return success;
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
