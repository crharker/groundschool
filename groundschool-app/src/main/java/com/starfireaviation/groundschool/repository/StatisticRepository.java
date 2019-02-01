/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.StatisticEntity;

/**
 * StatisticRepository
 *
 * @author brianmichael
 */
public interface StatisticRepository extends Repository<StatisticEntity, Long> {

    /**
     * Deletes a statistic
     *
     * @param statistic Statistic
     */
    void delete(StatisticEntity statistic);

    /**
     * Gets all statistics
     *
     * @return list of Statistic
     */
    List<StatisticEntity> findAll();

    /**
     * Gets a statistic
     *
     * @param id Long
     * @return Statistic
     */
    StatisticEntity findById(final long id);

    /**
     * Gets statistics for a user
     *
     * @param userId Long
     * @return list of Statistic
     */
    List<StatisticEntity> findByUserId(final long userId);

    /**
     * Gets statistics for an event
     *
     * @param eventId Long
     * @return list of Statistic
     */
    List<StatisticEntity> findByEventId(final long eventId);

    /**
     * Gets statistics for a question
     *
     * @param questionId Long
     * @return list of Statistic
     */
    List<StatisticEntity> findByQuestionId(final long questionId);

    /**
     * Gets statistics for a quiz
     *
     * @param quizId Long
     * @return list of Statistic
     */
    List<StatisticEntity> findByQuizId(final long quizId);

    /**
     * Saves a statistic
     *
     * @param statistic Statistic
     * @return Statistic
     */
    StatisticEntity save(StatisticEntity statistic);
}
