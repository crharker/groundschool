/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Address;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.service.AddressService;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.LessonPlanService;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.util.CodeGenerator;

import java.security.Principal;
import java.time.LocalDateTime;
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
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    /**
     * EventService
     */
    @Autowired
    private EventService eventService;

    /**
     * AddressService
     */
    @Autowired
    private AddressService addressService;

    /**
     * LessonPlanService
     */
    @Autowired
    private LessonPlanService lessonPlanService;

    /**
     * NotificationService
     */
    @Autowired
    private NotificationService notificationService;

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
     * @param principal Principal
     * @return Event
     */
    @PostMapping
    public Event post(@RequestBody Event event, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (event == null) {
            return event;
        }
        Event response = eventService.store(event);
        Address address = event.getAddress();
        if (address != null) {
            response.setAddress(addressService.store(response.getId(), address));
        }
        return response;
    }

    /**
     * Gets a event
     *
     * @param eventId Long
     * @param principal Principal
     * @return Event
     */
    @GetMapping(path = {
            "/{eventId}"
    })
    public Event get(@PathVariable("eventId") long eventId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        Event event = eventService.findById(eventId);
        event.setAddress(addressService.findByEventId(event.getId()));
        return event;
    }

    /**
     * Updates a user
     *
     * @param event Event
     * @param principal Principal
     * @return Event
     */
    @PutMapping
    public Event put(@RequestBody Event event, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (event == null) {
            return event;
        }
        Event response = eventService.store(event);
        Address address = event.getAddress();
        if (address != null) {
            response.setAddress(addressService.store(response.getId(), address));
        }
        return response;
    }

    /**
     * Deletes a event
     *
     * @param eventId Long
     * @param principal Principal
     * @return Event
     */
    @DeleteMapping(path = {
            "/{eventId}"
    })
    public Event delete(@PathVariable("eventId") long eventId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return eventService.delete(eventId);
    }

    /**
     * Get all events
     *
     * @param principal Principal
     *
     * @return list of Event
     */
    @GetMapping
    public List<Event> list(Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        List<Event> events = eventService.findAllEvents();
        for (Event event : events) {
            event.setAddress(addressService.findByEventId(event.getId()));
        }
        return events;
    }

    /**
     * RSVP's a user for an event
     *
     * @param eventId event ID
     * @param userId user ID
     * @param confirm confirm or decline
     * @param type NotificationType
     */
    @GetMapping(path = {
            "/{eventId}/rsvp/{userId}/{confirm}/{type}"
    })
    public void rsvp(
            @PathVariable("eventId") long eventId,
            @PathVariable("userId") long userId,
            @PathVariable("confirm") boolean confirm,
            @PathVariable("type") NotificationType type) {
        eventService.rsvp(eventId, userId, confirm);
    }

    /**
     * Registers a user for an event
     *
     * @param eventId event ID
     * @param userId user ID
     * @throws ResourceNotFoundException when event nor user is found
     */
    @GetMapping(path = {
            "/{eventId}/register/{userId}"
    })
    public void register(
            @PathVariable("eventId") long eventId,
            @PathVariable("userId") long userId) throws ResourceNotFoundException {
        eventService.register(eventId, userId);
        notificationService.send(userId, eventId, null, NotificationType.ALL, NotificationEventType.EVENT_REGISTER);
    }

    /**
     * Unregisters a user from an event
     *
     * @param eventId event ID
     * @param userId user ID
     * @throws ResourceNotFoundException when event nor user is found
     */
    @GetMapping(path = {
            "/{eventId}/unregister/{userId}"
    })
    public void unregister(
            @PathVariable("eventId") long eventId,
            @PathVariable("userId") long userId) throws ResourceNotFoundException {
        eventService.unregister(eventId, userId);
        notificationService.send(userId, eventId, null, NotificationType.ALL, NotificationEventType.EVENT_UNREGISTER);
    }

    /**
     * Assigns a lesson plan to an event
     *
     * @param eventId Event ID
     * @param lessonPlanId LessonPlan ID
     * @param principal Principal
     */
    @PostMapping(path = {
            "/{eventId}/assign/lessonplan/{lessonPlanId}"
    })
    public void assignLessonPlan(
            @PathVariable("eventId") long eventId,
            @PathVariable("lessonPlanId") long lessonPlanId,
            Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        //final LessonPlan lessonPlan =
        lessonPlanService.findById(lessonPlanId);
    }

    /**
     * Unassigns a lesson plan from an event
     *
     * @param eventId Event ID
     * @param lessonPlanId LessonPlan ID
     * @param principal Principal
     */
    @PostMapping(path = {
            "/{eventId}/unassign/lessonplan/{lessonPlanId}"
    })
    public void unassignLessonPlan(
            @PathVariable("eventId") long eventId,
            @PathVariable("lessonPlanId") long lessonPlanId,
            Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        //final LessonPlan lessonPlan =
        lessonPlanService.findById(lessonPlanId);
    }

    /**
     * Gets an event's checkin code, if assigned
     *
     * @param eventId Long
     * @param principal Principal
     * @return Event's checkin code
     */
    @GetMapping(path = {
            "/{eventId}/checkincode"
    })
    public String getCheckinCode(@PathVariable("eventId") long eventId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        String code = null;
        Event event = eventService.findById(eventId);
        if (event != null) {
            code = event.getCheckinCode();
        }
        return code;
    }

    /**
     * Starts an event
     *
     * @param eventId Long
     * @param principal Principal
     * @return started event
     */
    @PostMapping(path = {
            "/{eventId}/start"
    })
    public Event start(@PathVariable("eventId") long eventId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        Event event = eventService.findById(eventId);
        if (event != null && !event.isStarted()) {
            event.setStarted(true);
            event.setStartTime(LocalDateTime.now());
            event.setCheckinCode(CodeGenerator.generateCode(4));
            event = eventService.store(event);
        }
        return event;
    }

    /**
     * Complete's an event
     *
     * @param eventId Long
     * @param principal Principal
     * @return completed event
     */
    @PostMapping(path = {
            "/{eventId}/complete"
    })
    public Event complete(@PathVariable("eventId") long eventId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        Event event = eventService.findById(eventId);
        if (event != null && event.isStarted()) {
            event.setCompleted(true);
            event.setCompletedTime(LocalDateTime.now());
            event.setCheckinCode(null);
            event = eventService.store(event);
        }
        return event;
    }

}
