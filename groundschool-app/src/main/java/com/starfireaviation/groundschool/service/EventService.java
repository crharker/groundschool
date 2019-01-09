/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.User;

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
     * RSVP's a user for an event
     *
     * @param event Event
     * @param user User
     * @param confirm confirm or decline
     */
    public void rsvp(Event event, User user, boolean confirm);
}
