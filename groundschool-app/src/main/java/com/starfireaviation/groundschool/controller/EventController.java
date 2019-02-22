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

import com.starfireaviation.groundschool.exception.AccessDeniedException;
import com.starfireaviation.groundschool.exception.InvalidPayloadException;
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
import com.starfireaviation.groundschool.validation.EventValidator;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * EventController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
     * MAX_UPCOMING_COUNT
     */
    public static final int MAX_UPCOMING_COUNT = 10;

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
     * EventValidator
     */
    @Autowired
    private EventValidator eventValidator;

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
     * @throws ResourceNotFoundException when no event is found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     * @throws InvalidPayloadException when invalid data is provided
     */
    @PostMapping
    public Event post(@RequestBody Event event, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException, InvalidPayloadException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        eventValidator.validate(event);
        eventValidator.access(principal);
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
     * @throws ResourceNotFoundException when address is not found
     */
    @GetMapping(path = {
            "/{eventId}"
    })
    public Event get(@PathVariable("eventId") long eventId, Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        Event event = eventService.get(eventId);
        event.setAddress(addressService.findByEventId(event.getId()));
        return event;
    }

    /**
     * Updates a user
     *
     * @param event Event
     * @param principal Principal
     * @return Event
     * @throws ResourceNotFoundException when no event is found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @PutMapping
    public Event put(@RequestBody Event event, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        eventValidator.access(principal);
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
     * @throws ResourceNotFoundException when event is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @DeleteMapping(path = {
            "/{eventId}"
    })
    public Event delete(@PathVariable("eventId") long eventId, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        eventValidator.access(principal);
        return eventService.delete(eventId);
    }

    /**
     * Get all events
     *
     * @param principal Principal
     *
     * @return list of Event
     * @throws ResourceNotFoundException when address is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @GetMapping
    public List<Event> list(Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        eventValidator.access(principal);
        List<Event> events = eventService.getAll();
        for (Event event : events) {
            event.setAddress(addressService.findByEventId(event.getId()));
        }
        return events;
    }

    /**
     * Get X upcoming events
     *
     * @param count number of events to be returned
     * @param principal Principal
     * @return list of Event
     * @throws ResourceNotFoundException when address is not found
     */
    @GetMapping(path = {
            "/upcoming/{count}"
    })
    public List<Event> upcoming(@PathVariable("count") int count, Principal principal)
            throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        int actualCount = count;
        if (actualCount > MAX_UPCOMING_COUNT) {
            actualCount = MAX_UPCOMING_COUNT;
        }
        List<Event> upcomingEvents = eventService
                .getAll()
                .stream()
                .filter(event -> event.getStartTime().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Event::getStartTime))
                .limit(actualCount)
                .collect(Collectors.toList());
        for (Event event : upcomingEvents) {
            event.setAddress(addressService.findByEventId(event.getId()));
        }

        return upcomingEvents;
    }

    /**
     * RSVP's a user for an event
     *
     * @param eventId event ID
     * @param userId user ID
     * @param confirm confirm or decline
     * @param type NotificationType
     * @throws ResourceNotFoundException when event or user is not found
     */
    @GetMapping(path = {
            "/{eventId}/rsvp/{userId}/{confirm}/{type}"
    })
    public void rsvp(
            @PathVariable("eventId") long eventId,
            @PathVariable("userId") long userId,
            @PathVariable("confirm") boolean confirm,
            @PathVariable("type") NotificationType type) throws ResourceNotFoundException {
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
        if (!eventService.isRegistered(eventId, userId)) {
            eventService.register(eventId, userId);
            notificationService.send(userId, eventId, null, NotificationType.ALL, NotificationEventType.EVENT_REGISTER);
        }
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
     * @throws ResourceNotFoundException when lesson plan is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @PostMapping(path = {
            "/{eventId}/assign/lessonplan/{lessonPlanId}"
    })
    public void assignLessonPlan(
            @PathVariable("eventId") long eventId,
            @PathVariable("lessonPlanId") long lessonPlanId,
            Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        eventValidator.access(principal);
        //final LessonPlan lessonPlan =
        lessonPlanService.get(lessonPlanId);
    }

    /**
     * Unassigns a lesson plan from an event
     *
     * @param eventId Event ID
     * @param lessonPlanId LessonPlan ID
     * @param principal Principal
     * @throws ResourceNotFoundException when lesson plan is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @PostMapping(path = {
            "/{eventId}/unassign/lessonplan/{lessonPlanId}"
    })
    public void unassignLessonPlan(
            @PathVariable("eventId") long eventId,
            @PathVariable("lessonPlanId") long lessonPlanId,
            Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        eventValidator.access(principal);
        //final LessonPlan lessonPlan =
        lessonPlanService.get(lessonPlanId);
    }

    /**
     * Gets an event's checkin code, if assigned
     *
     * @param eventId Long
     * @param principal Principal
     * @return Event's checkin code
     * @throws ResourceNotFoundException when event is not found
     */
    @GetMapping(path = {
            "/{eventId}/checkincode"
    })
    public String getCheckinCode(@PathVariable("eventId") long eventId, Principal principal)
            throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        String code = null;
        Event event = eventService.get(eventId);
        if (event != null) {
            code = event.getCheckinCode();
        }
        return code;
    }

    /**
     * Checkin
     *
     * @param userId User ID
     * @param eventId Event ID
     * @param code checkin code
     * @param principal Principal
     * @return checkin success
     * @throws ResourceNotFoundException when event or user is not found
     */
    @PostMapping(path = {
            "/{eventId}/checkin/{userId}/{code}"
    })
    public boolean checkin(
            @PathVariable("eventId") long eventId,
            @PathVariable("userId") long userId,
            @PathVariable("code") String code,
            Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return eventService.checkin(eventId, userId, code);
    }

    /**
     * Starts an event
     *
     * @param eventId Long
     * @param principal Principal
     * @return started event
     * @throws ResourceNotFoundException when event is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @PostMapping(path = {
            "/{eventId}/start"
    })
    public Event start(@PathVariable("eventId") long eventId, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        eventValidator.access(principal);
        Event event = eventService.get(eventId);
        if (event != null && !event.isStarted()) {
            event.setStarted(true);
            event.setStartTime(LocalDateTime.now(ZoneOffset.UTC));
            event.setCheckinCode(CodeGenerator.generateCode(4));
            event = eventService.store(event);
            List<Long> eventParticipants = eventService.getAllEventUsers(eventId);
            for (Long userId : eventParticipants) {
                try {
                    notificationService.send(
                            userId,
                            eventId,
                            null,
                            NotificationType.ALL,
                            NotificationEventType.EVENT_START);
                } catch (ResourceNotFoundException e) {
                    // Do nothing
                }
            }
        }
        return event;
    }

    /**
     * Complete's an event
     *
     * @param eventId Long
     * @param principal Principal
     * @return completed event
     * @throws ResourceNotFoundException when event is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @PostMapping(path = {
            "/{eventId}/complete"
    })
    public Event complete(@PathVariable("eventId") long eventId, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        eventValidator.access(principal);
        Event event = eventService.get(eventId);
        if (event != null && event.isStarted()) {
            event.setCompleted(true);
            event.setCompletedTime(LocalDateTime.now());
            event.setCheckinCode(null);
            event = eventService.store(event);
        }
        return event;
    }

}
