/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
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
     * @throws ResourceNotFoundException when the event is not found
     */
    public Event delete(long id) throws ResourceNotFoundException;

    /**
     * Gets all events
     *
     * @return list of Event
     * @throws ResourceNotFoundException when the event is not found
     */
    public List<Event> getAll() throws ResourceNotFoundException;

    /**
     * Gets a event
     *
     * @param id Long
     * @return Event
     * @throws ResourceNotFoundException when the event is not found
     */
    public Event get(long id) throws ResourceNotFoundException;

    /**
     * Register a user for an event
     *
     * @param eventId Event ID
     * @param userId User ID
     * @throws ResourceNotFoundException when the event or user is not found
     */
    public void register(Long eventId, Long userId) throws ResourceNotFoundException;

    /**
     * Unregister a user from an event
     *
     * @param eventId Event ID
     * @param userId User ID
     * @throws ResourceNotFoundException when the event or user is not found
     */
    public void unregister(Long eventId, Long userId) throws ResourceNotFoundException;

    /**
     * RSVP's a user for an event
     *
     * @param eventId Event ID
     * @param userId User ID
     * @param confirm confirm or decline
     * @throws ResourceNotFoundException when the event or user is not found
     */
    public void rsvp(Long eventId, Long userId, boolean confirm) throws ResourceNotFoundException;

    /**
     * Checks in a user for an event
     *
     * @param eventId Event ID
     * @param userId User ID
     * @param code checkin code
     * @return checkin success
     * @throws ResourceNotFoundException when the event or user is not found
     */
    public boolean checkin(Long eventId, Long userId, String code) throws ResourceNotFoundException;

    /**
     * Did the user check in to an event?
     *
     * @param eventId Event ID
     * @param userId User ID
     * @return checked in?
     * @throws ResourceNotFoundException when the event or user is not found
     */
    public boolean didCheckIn(Long eventId, Long userId) throws ResourceNotFoundException;

    /**
     * Retrieves all users for an event
     *
     * @param eventId Event ID
     * @return list of User ID
     */
    public List<Long> getAllEventUsers(Long eventId);

    /**
     * Retrieves all users for an event who have checked in
     *
     * @param eventId Event ID
     * @return list of User ID
     */
    public List<Long> getAllEventCheckedInUsers(Long eventId);

    /**
     * Returns the current (started but not completed) event. If no event is found, null is returned
     *
     * @return Event ID
     * @throws ResourceNotFoundException when event is not found
     */
    public Long getCurrentEvent() throws ResourceNotFoundException;

    /**
     * Is the user checked in to the current event?
     *
     * @param userId User ID
     * @return Event ID for the event in which user is checked in
     * @throws ResourceNotFoundException when the user is not found
     */
    public Long isCheckedIn(Long userId) throws ResourceNotFoundException;

    /**
     * Is the user registered for an event?
     *
     * @param eventId Event ID
     * @param userId User ID
     * @return whether or not user is registered
     * @throws ResourceNotFoundException when the event or user is not found
     */
    public boolean isRegistered(Long eventId, Long userId) throws ResourceNotFoundException;
}
