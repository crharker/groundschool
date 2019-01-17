/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

/**
 * ExternalDataRetrievalService
 *
 * @author brianmichael
 */
public interface ExternalDataRetrievalService {

    /**
     * Rebuilds member details
     *
     * @param scheduleNextRun whether or not to schedule the next run
     */
    public void rebuildMemberDetails(boolean scheduleNextRun);
}
