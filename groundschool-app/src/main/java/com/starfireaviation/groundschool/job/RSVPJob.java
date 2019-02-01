/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.starfireaviation.groundschool.Constants;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.service.EventService;

/**
 * RSVPJob
 *
 * @author brianmichael
 */
@Component
public class RSVPJob extends QuartzJobBean {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RSVPJob.class);

    /**
     * EventService
     */
    @Autowired
    private EventService eventService;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        final Event event = eventService.findById(
                Long.valueOf(jobExecutionContext.getJobDetail().getJobDataMap().getString(Constants.ID)),
                true);

        LOGGER.info(String.format("Sending RSVP notifications for %s - %s", event.getId(), event.getTitle()));
        //emailService.rsvp(event);
        //slackService.rsvp(event);
        //smsService.rsvp(event);
    }

}
