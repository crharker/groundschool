/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.EventEntity;
import com.starfireaviation.groundschool.model.sql.EventParticipantEntity;
import com.starfireaviation.groundschool.repository.EventRepository;
import com.starfireaviation.groundschool.repository.EventParticipantRepository;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.UserService;

import ma.glasnost.orika.MapperFacade;

/**
 * EventServiceImpl
 *
 * @author brianmichael
 */
@Service
public class EventServiceImpl implements EventService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    /**
     * EventRepository
     */
    @Autowired
    private EventRepository eventRepository;

    /**
     * EventUserRepository
     */
    @Autowired
    private EventParticipantRepository eventUserRepository;

    /**
     * UserService
     */
    @Autowired
    private UserService userService;

    /**
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * Initializes an instance of <code>EventServiceImpl</code> with the default data.
     */
    public EventServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>EventServiceImpl</code> with the default data.
     *
     * @param eventRepository EventRepository
     * @param mapperFacade MapperFacade
     */
    public EventServiceImpl(EventRepository eventRepository, MapperFacade mapperFacade) {
        this.eventRepository = eventRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Event store(Event event) {
        if (event == null) {
            return event;
        }
        return mapper.map(eventRepository.save(mapper.map(event, EventEntity.class)), Event.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Event delete(long id) {
        Event event = mapper.map(findById(id), Event.class);
        if (event != null) {
            eventRepository.delete(mapper.map(event, EventEntity.class));
        }
        return event;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Event> findAllEvents() {
        List<Event> events = new ArrayList<>();
        List<EventEntity> eventEntities = eventRepository.findAll();
        for (EventEntity eventEntity : eventEntities) {
            Event event = mapper.map(eventEntity, Event.class);
            List<EventParticipantEntity> EventParticipantEntities = eventUserRepository.findByEventId(
                    eventEntity.getId());
            List<User> participants = new ArrayList<>();
            for (EventParticipantEntity eventParticipantEntity : EventParticipantEntities) {
                participants.add(userService.findById(eventParticipantEntity.getUserId()));
            }
            event.setParticipants(participants);
            events.add(event);
        }
        return events;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Event findById(long id) {
        EventEntity eventEntity = eventRepository.findById(id);
        LOGGER.info(String.format("retrieved lessonPlanId [%s] for event [%s]", eventEntity.getLessonPlanId(), id));
        Event event = mapper.map(eventEntity, Event.class);
        List<EventParticipantEntity> EventParticipantEntities = eventUserRepository.findByEventId(id);
        List<User> participants = new ArrayList<>();
        for (EventParticipantEntity eventParticipantEntity : EventParticipantEntities) {
            participants.add(userService.findById(eventParticipantEntity.getUserId()));
        }
        event.setParticipants(participants);
        if (event.getParticipants() != null) {
            LOGGER.info(
                    String.format(
                            "event [%s] has [%s] participants and lessonPlanId [%s]",
                            id,
                            event.getParticipants().size(),
                            event.getLessonPlanId()));
        } else {
            LOGGER.info(
                    String.format(
                            "event [%s] has lessonPlanId [%s]",
                            id,
                            event.getParticipants().size(),
                            event.getLessonPlanId()));
        }
        return event;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void rsvp(Long eventId, Long userId, boolean confirm) {
        if (eventId == null || userId == null) {
            return;
        }
        List<EventParticipantEntity> eventUserEntities = eventUserRepository.findByEventId(eventId);
        if (CollectionUtils.isEmpty(eventUserEntities)) {
            if (confirm) {
                register(eventId, userId);
            }
            eventUserEntities = eventUserRepository.findByEventId(eventId);
        }
        if (!CollectionUtils.isEmpty(eventUserEntities)) {
            for (EventParticipantEntity eventUserEntity : eventUserEntities) {
                if (userId == eventUserEntity.getUserId()) {
                    if (confirm) {
                        eventUserEntity.setConfirmed(true);
                        eventUserEntity.setDeclined(false);
                        eventUserEntity.setConfirmationTime(LocalDateTime.now());
                    } else {
                        eventUserEntity.setConfirmed(false);
                        eventUserEntity.setDeclined(true);
                        eventUserEntity.setConfirmationTime(LocalDateTime.now());
                    }
                    eventUserRepository.save(eventUserEntity);
                }
                break;
            }
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void register(Long eventId, Long userId) {
        if (eventId == null || userId == null) {
            return;
        }
        EventParticipantEntity eventUserEntity = new EventParticipantEntity();
        eventUserEntity.setEventId(eventId);
        eventUserEntity.setUserId(userId);
        eventUserRepository.save(eventUserEntity);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void unregister(Long eventId, Long userId) {
        if (eventId == null || userId == null) {
            return;
        }
        List<EventParticipantEntity> eventUserEntities = eventUserRepository.findByEventId(eventId);
        for (EventParticipantEntity eventUserEntity : eventUserEntities) {
            if (userId == eventUserEntity.getUserId()) {
                eventUserRepository.delete(eventUserEntity);
                break;
            }
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean checkin(Long eventId, Long userId, String code) {
        boolean success = false;
        EventParticipantEntity eventUserEntity = eventUserRepository.findByEventAndUserId(eventId, userId);
        Event event = findById(eventId);
        if (eventUserEntity != null
                && event != null
                && event.getCheckinCode() != null
                && event.getCheckinCode().equalsIgnoreCase(code)) {
            eventUserEntity.setCheckedIn(true);
            eventUserEntity.setCheckinTime(LocalDateTime.now());
            eventUserRepository.save(eventUserEntity);
            success = true;
        }
        return success;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean didCheckIn(Long eventId, Long userId) {
        if (eventId != null && userId != null) {
            EventParticipantEntity eventUserEntity = eventUserRepository.findByEventAndUserId(eventId, userId);
            if (eventUserEntity != null && eventUserEntity.getCheckedIn() != null) {
                return eventUserEntity.getCheckedIn().booleanValue();
            }
        }
        return false;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Long> getAllEventUsers(Long eventId) {
        List<Long> userIds = new ArrayList<>();
        List<EventParticipantEntity> eventUserEntities = eventUserRepository.findByEventId(eventId);
        if (eventUserEntities != null) {
            for (EventParticipantEntity eventUserEntity : eventUserEntities) {
                userIds.add(eventUserEntity.getUserId());
            }
        }
        return userIds;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Long> getAllEventCheckedInUsers(Long eventId) {
        List<Long> userIds = new ArrayList<>();
        for (Long userId : getAllEventUsers(eventId)) {
            if (didCheckIn(eventId, userId)) {
                userIds.add(userId);
            }
        }
        return userIds;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Long isCheckedIn(Long userId) {
        Long currentEventId = getCurrentEvent();
        Long eventId = null;
        if (didCheckIn(currentEventId, userId)) {
            eventId = currentEventId;
        }
        return eventId;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Long getCurrentEvent() {
        Long eventId = null;
        List<Event> events = findAllEvents();
        for (Event event : events) {
            if (event.isStarted() && !event.isCompleted()) {
                eventId = event.getId();
            }
        }
        return eventId;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean isRegistered(Long eventId, Long userId) {
        boolean registered = false;
        if (eventId == null || userId == null) {
            return registered;
        }
        if (eventUserRepository.findByEventAndUserId(eventId, userId) != null) {
            registered = true;
        }
        return registered;
    }

}
