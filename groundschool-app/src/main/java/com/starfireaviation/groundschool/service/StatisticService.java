/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;

/**
 * StatisticService
 *
 * @author brianmichael
 */
public interface StatisticService {

    /**
     * Creates a statistic
     *
     * @param statistic Statistic
     * @return Statistic
     */
    public Statistic store(final Statistic statistic);

    /**
     * Deletes a statistic
     *
     * @param id Long
     * @return Statistic
     */
    public Statistic delete(final long id);

    /**
     * Gets all statistics
     *
     * @return list of Statistic
     */
    public List<Statistic> findAllStatistics();

    /**
     * Gets a statistic
     *
     * @param id Long
     * @return Statistic
     */
    public Statistic findById(final long id);

    /**
     * Gets all statistics using the provided criteria
     *
     * @param userId Long
     * @param statisticType StatisticType
     * @return list of Statistic
     */
    public List<Statistic> findByUserId(final long userId, final StatisticType statisticType);

    /**
     * Gets all statistics using the provided criteria
     *
     * @param quizId Long
     * @param statisticType StatisticType
     * @return list of Statistic
     */
    public List<Statistic> findByQuizId(final long quizId, final StatisticType statisticType);

    /**
     * Gets all statistics using the provided criteria
     *
     * @param questionId Long
     * @param statisticType StatisticType
     * @return list of Statistic
     */
    public List<Statistic> findByQuestionId(final long questionId, final StatisticType statisticType);

    /**
     * Gets all statistics using the provided criteria
     *
     * @param eventId Long
     * @param statisticType StatisticType
     * @return list of Statistic
     */
    public List<Statistic> findByEventId(final long eventId, final StatisticType statisticType);

}
