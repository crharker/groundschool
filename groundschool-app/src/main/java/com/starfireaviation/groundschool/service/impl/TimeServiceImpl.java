/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.Date;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.Constants;
import com.starfireaviation.groundschool.job.NoopJob;
import com.starfireaviation.groundschool.job.RSVPJob;
import com.starfireaviation.groundschool.model.JobType;
import com.starfireaviation.groundschool.service.TimeService;

/**
 * TimeServiceImpl
 *
 * @author brianmichael
 */
@Service
public class TimeServiceImpl implements TimeService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeServiceImpl.class);

    /**
     * Quartz Scheduler
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void add(Long id, boolean cron, Date date, String cronSchedule, JobType type) {
        final String name = type + "-" + UUID.randomUUID().toString();
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(Constants.ID, id);

        final JobDetail job = JobBuilder
                .newJob(getJobClassForType(type))
                .withIdentity(name, id.toString())
                .usingJobData(jobDataMap)
                .build();

        final TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder
                .newTrigger()
                .withIdentity(name, id.toString())
                .forJob(job);
        if (date != null) {
            triggerBuilder.startAt(date);
        }
        if (cron) {
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronSchedule));
        }

        try {
            scheduler.scheduleJob(job, triggerBuilder.build());
        } catch (SchedulerException se) {
            LOGGER.error("SchedulerException", se);
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void remove(Long id) {
        final GroupMatcher<TriggerKey> matcher = GroupMatcher.triggerGroupEquals(id.toString());
        try {
            for (final TriggerKey key : scheduler.getTriggerKeys(matcher)) {
                scheduler.unscheduleJob(key);
            }
        } catch (SchedulerException se) {
            LOGGER.error("SchedulerException", se);
        }
    }

    /**
     * Derives job class from JobType
     *
     * @param type JobType
     * @return class
     */
    private static Class<? extends Job> getJobClassForType(JobType type) {
        Class<? extends Job> className = NoopJob.class;
        switch (type) {
            case RSVP:
                className = RSVPJob.class;
                break;
            default:
                className = NoopJob.class;
        }
        return className;
    }

}
