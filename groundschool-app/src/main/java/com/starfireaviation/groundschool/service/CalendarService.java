/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;

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
     * @throws ResourceNotFoundException when event is not found
     */
    public String add(Long eventId) throws ResourceNotFoundException;

    /**
     * Checks if the calendar has an entry for the event provided
     *
     * @param eventId Event ID
     * @return event exists on the calendar or not
     * @throws ResourceNotFoundException when event is not found
     */
    public boolean hasEvent(Long eventId) throws ResourceNotFoundException;

    /**
     * Removes event from a Google calendar
     *
     * @param eventId Event ID
     * @return success
     * @throws ResourceNotFoundException when event is not found
     */
    public boolean delete(Long eventId) throws ResourceNotFoundException;

}
