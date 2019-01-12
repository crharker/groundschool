/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

/**
 * CalendarService
 *
 * @author brianmichael
 */
public interface CalendarService {

    /**
     * Adds event details to a Google calendar
     *
     * @param eventId Event ID
     * @return calendar URL for event
     */
    public String add(Long eventId);

    /**
     * Checks if the calendar has an entry for the event provided
     *
     * @param eventId Event ID
     * @return event exists on the calendar or not
     */
    public boolean hasEvent(Long eventId);

}
