/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.model.Statistic;

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
    public Statistic store(Statistic statistic);

    /**
     * Deletes a statistic
     *
     * @param id Long
     * @return Statistic
     */
    public Statistic delete(long id);

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
    public Statistic findById(long id);

}
