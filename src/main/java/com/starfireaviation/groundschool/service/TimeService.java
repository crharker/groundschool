/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.Date;

import com.starfireaviation.groundschool.model.JobType;

/**
 * TimeService
 *
 * @author brianmichael
 */
public interface TimeService {

    /**
     * Adds a schedule
     *
     * @param id Long
     * @param cron schedule type
     * @param date time at which to start job
     * @param cronSchedule string
     * @param type JobType
     */
    void add(Long id, boolean cron, Date date, String cronSchedule, JobType type);

    /**
     * Removes a schedule
     *
     * @param id Long
     */
    void remove(Long id);
}
