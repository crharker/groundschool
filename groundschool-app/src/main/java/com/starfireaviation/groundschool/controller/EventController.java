/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.service.EventService;

import java.util.List;

/**
 * EventController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({
        "/events"
})
public class EventController {

    /**
     * EventService
     */
    @Autowired
    private EventService eventService;

    /**
     * Initializes an instance of <code>EventController</code> with the default data.
     */
    public EventController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>EventController</code> with the default data.
     *
     * @param eventService EventService
     */
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Creates a event
     *
     * @param event Event
     * @return Event
     */
    @PostMapping
    public Event post(@RequestBody Event event) {
        if (event == null) {
            return event;
        }
        return eventService.store(event);
    }

    /**
     * Gets a event
     *
     * @param id Long
     * @return Event
     */
    @GetMapping(path = {
            "/{id}"
    })
    public Event get(@PathVariable("id") long id) {
        return eventService.findById(id);
    }

    /**
     * Updates a user
     *
     * @param event Event
     * @return Event
     */
    @PutMapping
    public Event put(@RequestBody Event event) {
        if (event == null) {
            return event;
        }
        return eventService.store(event);
    }

    /**
     * Deletes a event
     *
     * @param id Long
     * @return Event
     */
    @DeleteMapping(path = {
            "/{id}"
    })
    public Event delete(@PathVariable("id") long id) {
        return eventService.delete(id);
    }

    /**
     * Get all events
     *
     * @return list of Event
     */
    @GetMapping
    public List<Event> list() {
        return eventService.findAllEvents();
    }
}
