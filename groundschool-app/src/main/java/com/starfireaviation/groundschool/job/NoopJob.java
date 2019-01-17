/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.job;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * NoopJob
 *
 * @author brianmichael
 */
@Component
public class NoopJob extends QuartzJobBean {

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        // Do nothing
    }

}
