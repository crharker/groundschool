/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.model.Event;

/**
 * EventService
 *
 * @author brianmichael
 */
public interface EventService {

    /**
     * Creates a event
     *
     * @param event Event
     * @return User
     */
    public Event store(Event event);

    /**
     * Deletes a event
     *
     * @param id Long
     * @return Event
     */
    public Event delete(long id);

    /**
     * Gets all events
     *
     * @return list of Event
     */
    public List<Event> findAllEvents();

    /**
     * Gets a event
     *
     * @param id Long
     * @return Event
     */
    public Event findById(long id);

    /**
     * Register a user for an event
     *
     * @param eventId Event ID
     * @param userId User ID
     */
    public void register(Long eventId, Long userId);

    /**
     * Unregister a user from an event
     *
     * @param eventId Event ID
     * @param userId User ID
     */
    public void unregister(Long eventId, Long userId);

    /**
     * RSVP's a user for an event
     *
     * @param eventId Event ID
     * @param userId User ID
     * @param confirm confirm or decline
     */
    public void rsvp(Long eventId, Long userId, boolean confirm);
}
