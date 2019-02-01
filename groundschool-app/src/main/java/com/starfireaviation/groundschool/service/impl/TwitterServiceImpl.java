/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.SMSMessage;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.service.MessageService;
import com.starfireaviation.groundschool.service.StatisticService;

/**
 * TwitterServiceImpl
 *
 * @author brianmichael
 */
@Service("twitterService")
public class TwitterServiceImpl implements MessageService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterServiceImpl.class);

    /**
     * StatisticService
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserDeleteMsg(final User user) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendQuizCompleteMsg(final User user) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRSVPMsg(final User user, final Event event) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventStartMsg(final User user, final Event event) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendQuestionAskedMsg(final User user, final Question question) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventRegisterMsg(final User user, final Event event) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendEventUnregisterMsg(final User user, final Event event) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserSettingsVerifiedMsg(final User user) {
        // Not implemented
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendUserSettingsChangeMsg(final User user) {
        // Not implemented
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
    public void resendUserSettingsChangeMsg(
            final User user,
            String response,
            String originalMessage) {
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
                        StatisticType.TWITTER_MESSAGE_RECEIVED,
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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void sendInviteMsg(User user, String destination) {
        // Not implemented
    }

}
