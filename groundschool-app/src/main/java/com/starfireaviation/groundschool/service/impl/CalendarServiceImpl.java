/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.Duration;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.service.CalendarService;
import com.starfireaviation.groundschool.service.StatisticService;

/**
 * CalendarServiceImpl
 *
 * @author brianmichael
 */
@Service
public class CalendarServiceImpl implements CalendarService {

    /**
     * StatisticService
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean hasEvent(Long eventId) {
        Instant start = Instant.now();
        boolean found = false;
        Statistic statistic = new Statistic(
                StatisticType.CALENDAR_RETRIEVE_ALL_EVENTS,
                String.format(
                        "Duration [%s]",
                        Duration.between(start, Instant.now())));
        statisticService.store(statistic);
        return found;
    }

}
