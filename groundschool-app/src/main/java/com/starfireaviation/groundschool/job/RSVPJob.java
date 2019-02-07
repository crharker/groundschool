/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.starfireaviation.groundschool.Constants;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.NotificationService;

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
     * NotificationService
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        Event event;
        try {
            event = eventService.get(
                    Long.valueOf(jobExecutionContext.getJobDetail().getJobDataMap().getString(Constants.ID)));
            LOGGER.info(String.format("Sending RSVP notifications for %s - %s", event.getId(), event.getTitle()));
            List<Long> eventParticipants = eventService.getAllEventUsers(event.getId());
            if (eventParticipants != null) {
                for (Long userId : eventParticipants) {
                    notificationService.send(
                            userId,
                            event.getId(),
                            null,
                            NotificationType.ALL,
                            NotificationEventType.EVENT_RSVP);
                }
            }
        } catch (NumberFormatException | ResourceNotFoundException e) {
            // Do nothing
        }
    }

}
