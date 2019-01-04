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

import com.starfireaviation.groundschool.service.ExternalDataRetrievalService;

/**
 * MemberDetailsRefreshJob
 *
 * @author brianmichael
 */
@Component
public class MemberDetailsRefreshJob extends QuartzJobBean {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberDetailsRefreshJob.class);

    /**
     * ExternalDataRetrievalService
     */
    @Autowired
    private ExternalDataRetrievalService externalDataRetrievalService;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        LOGGER.info("Rebuilding member details from scheduled task");
        externalDataRetrievalService.rebuildMemberDetails(true);
    }
}
