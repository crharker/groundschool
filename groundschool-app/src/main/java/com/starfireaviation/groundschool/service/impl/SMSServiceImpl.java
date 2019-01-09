/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.SMSMessage;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.SMSMessageEntity;
import com.starfireaviation.groundschool.properties.SMSProperties;
import com.starfireaviation.groundschool.repository.SMSMessageRepository;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.SMSService;
import com.starfireaviation.groundschool.service.StatisticService;
import com.starfireaviation.groundschool.service.UserService;
import com.starfireaviation.groundschool.util.SMSResponseParser;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * SMSServiceImpl
 *
 * @author brianmichael
 */
@Service
public class SMSServiceImpl implements SMSService {

    /**
     * TN_PATTERN
     */
    private static final Pattern TN_PATTERN = Pattern.compile("\\+1(.+){10}");

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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(
            Long userId,
            Long eventId,
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
        smsMessageRepository.save(new SMSMessageEntity(userId, eventId, questionId, toAddress, new Date(), body, type));
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
    public String receiveMessage(SMSMessage message) {
        Instant start = Instant.now();
        LOGGER.info(String.format("receiveMessage() message received was [%s]", message));
        Long userId = null;
        String response = replyWithNoOpenMessagesMsg();
        final List<SMSMessageEntity> smsMessageEntities = smsMessageRepository.findByTo(
                stripCountryCode(message.getFrom()));
        if (!CollectionUtils.isEmpty(smsMessageEntities)) {
            List<SMSMessageEntity> sortedSMSMessages = sortByTime(smsMessageEntities);
            SMSMessageEntity smsMessageEntity = sortedSMSMessages.get(0);
            if (smsMessageEntity.isOpen()) {
                userId = smsMessageEntity.getUserId();
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
     */
    private static boolean processUserVerifiedResponse(Long userId, SMSMessage message) {
        // Do nothing
        return true;
    }

    /**
     * Process user settings response
     *
     * @param userId user ID
     * @param message to be processed
     * @return success
     */
    private boolean processUserSettingsResponse(Long userId, SMSMessage message) {
        boolean success = true;
        // STOP, CONFIRM, DECLINE, other
        final String body = message.getBody();
        if (body != null) {
            switch (SMSResponseParser.determineResponse(body)) {
                case STOP:
                    handleStop(userId);
                    break;
                case CONFIRM:
                    User user = userService.findById(userId);
                    user.setSmsVerified(true);
                    userService.store(user);
                    notificationService.send(userId, NotificationEventType.USER_VERIFIED);
                    break;
                case DECLINE:
                    handleDecline(userId);
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
     */
    private void handleStop(Long userId) {
        User user = userService.findById(userId);
        user.setSmsEnabled(false);
        userService.store(user);
    }

    /**
     * Handles DECLINE SMSResponseOption
     *
     * @param userId user ID
     */
    private void handleDecline(Long userId) {
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
     */
    private static boolean processUserDeletedResponse(Long userId, SMSMessage message) {
        // Do nothing
        return true;
    }

}
