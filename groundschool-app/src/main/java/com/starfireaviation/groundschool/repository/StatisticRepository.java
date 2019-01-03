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
    StatisticEntity findById(long id);

    /**
     * Saves a statistic
     *
     * @param statistic Statistic
     * @return Statistic
     */
    StatisticEntity save(StatisticEntity statistic);
}
