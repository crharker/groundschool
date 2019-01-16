/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.sql.StatisticEntity;
import com.starfireaviation.groundschool.model.sql.UserQuestionEntity;
import com.starfireaviation.groundschool.repository.StatisticRepository;
import com.starfireaviation.groundschool.repository.UserQuestionRepository;
import com.starfireaviation.groundschool.service.StatisticService;

import ma.glasnost.orika.MapperFacade;

/**
 * StatisticServiceImpl
 *
 * @author brianmichael
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticServiceImpl.class);

    /**
     * QUESTION_ANSWERED_PATTERN
     */
    private static final Pattern QUESTION_ANSWERED_PATTERN = Pattern.compile(
            "Duration \\[(.*?)\\]; Question ID \\[(.*?)\\]; Event ID \\[(.*?)\\]; Quiz ID \\[(.*?)\\]; Answer Given \\[(.*?)\\]; Answered Correctly \\[(.*?)\\]");

    /**
     * StatisticRepository
     */
    @Autowired
    private StatisticRepository statisticRepository;

    /**
     * UserQuestionRepository
     */
    @Autowired
    private UserQuestionRepository userQuestionRepository;

    /**
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * Initializes an instance of <code>StatisticServiceImpl</code> with the default data.
     */
    public StatisticServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>StatisticServiceImpl</code> with the default data.
     *
     * @param statisticRepository StatisticRepository
     * @param mapperFacade MapperFacade
     */
    public StatisticServiceImpl(StatisticRepository statisticRepository, MapperFacade mapperFacade) {
        this.statisticRepository = statisticRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Statistic store(Statistic statistic) {
        Statistic response = null;
        if (statistic == null) {
            return response;
        }
        switch (statistic.getStatisticType()) {
            case QUESTION_ANSWERED:
                UserQuestionEntity userQuestionEntity = mapStatisticToUserQuestionEntity(statistic);
                if (userQuestionEntity != null) {
                    userQuestionEntity = userQuestionRepository.save(userQuestionEntity);
                }
                break;
            default:
                // Do nothing
        }
        return mapper.map(statisticRepository.save(mapper.map(statistic, StatisticEntity.class)), Statistic.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Statistic delete(long id) {
        final Statistic statistic = mapper.map(findById(id), Statistic.class);
        if (statistic != null) {
            statisticRepository.delete(mapper.map(statistic, StatisticEntity.class));
        }
        return statistic;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Statistic> findAllStatistics() {
        final List<Statistic> statistics = new ArrayList<>();
        final List<StatisticEntity> statisticEntities = statisticRepository.findAll();
        for (final StatisticEntity statisticEntity : statisticEntities) {
            statistics.add(mapper.map(statisticEntity, Statistic.class));
        }
        return statistics;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Statistic findById(long id) {
        return mapper.map(statisticRepository.findById(id), Statistic.class);
    }

    /**
     * Maps a Statistic to a UserQuestionEntity
     *
     * @param statistic Statistic
     * @return UserQuestionEntity
     */
    private static UserQuestionEntity mapStatisticToUserQuestionEntity(Statistic statistic) {
        final Matcher matcher = QUESTION_ANSWERED_PATTERN.matcher(statistic.getDescription());
        if (matcher.find()) {
            final UserQuestionEntity userQuestionEntity = new UserQuestionEntity();
            userQuestionEntity.setTime(LocalDateTime.now());
            userQuestionEntity.setUserId(statistic.getUserId());
            userQuestionEntity.setQuestionId(getLongValueDefaulted(matcher.group(2), 0L));
            userQuestionEntity.setEventId(getLongValueDefaulted(matcher.group(3), 0L));
            userQuestionEntity.setQuizId(getLongValueDefaulted(matcher.group(4), 0L));
            userQuestionEntity.setAnswerGiven(matcher.group(5));
            userQuestionEntity.setAnsweredCorrectly(Boolean.parseBoolean(matcher.group(6)));
            return userQuestionEntity;
        }
        return null;
    }

    /**
     * Gets long value from string
     *
     * @param toBeParsed string to be parsed
     * @param defaultValue default value
     * @return value
     */
    private static long getLongValueDefaulted(String toBeParsed, long defaultValue) {
        long value = defaultValue;
        try {
            value = Long.parseLong(toBeParsed);
        } catch (NumberFormatException nfe) {
            // Do nothing
        }
        return value;
    }
}
