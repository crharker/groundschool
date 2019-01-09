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

import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.service.SlackService;
import com.starfireaviation.groundschool.service.StatisticService;

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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void send(
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

}
