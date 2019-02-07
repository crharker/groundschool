/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
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
     * HazelcastInstance
     */
    @Autowired
    private HazelcastInstance hazelcastInstance;

    /**
     * EventCache
     */
    private Map<Long, Event> eventCache;

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
     * @param hazelcastInstance HazelcastInstance
     */
    public EventServiceImpl(
            EventRepository eventRepository,
            MapperFacade mapperFacade,
            HazelcastInstance hazelcastInstance) {
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
        if (event.getId() != null) {
            initCache();
            eventCache.remove(event.getId());
        }
        return mapper.map(eventRepository.save(mapper.map(event, EventEntity.class)), Event.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Event delete(long id) throws ResourceNotFoundException {
        Event event = mapper.map(get(id), Event.class);
        if (event != null) {
            eventRepository.delete(mapper.map(event, EventEntity.class));
            initCache();
            eventCache.remove(id);
        }
        return event;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Event> getAll() throws ResourceNotFoundException {
        List<Event> events = new ArrayList<>();
        List<EventEntity> eventEntities = eventRepository.findAll();
        for (EventEntity eventEntity : eventEntities) {
            events.add(get(eventEntity.getId()));
        }
        return events;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Event get(long id) throws ResourceNotFoundException {
        initCache();
        if (eventCache.containsKey(id)) {
            return eventCache.get(id);
        }
        final EventEntity eventEntity = eventRepository.findById(id);
        final Event event = mapper.map(eventEntity, Event.class);
        final List<EventParticipantEntity> EventParticipantEntities = eventUserRepository.findByEventId(id);
        final List<User> participants = new ArrayList<>();
        for (final EventParticipantEntity eventParticipantEntity : EventParticipantEntities) {
            participants.add(userService.get(eventParticipantEntity.getUserId()));
        }
        event.setParticipants(participants);
        eventCache.put(id, event);
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
    public boolean checkin(Long eventId, Long userId, String code) throws ResourceNotFoundException {
        boolean success = false;
        EventParticipantEntity eventUserEntity = eventUserRepository.findByEventAndUserId(eventId, userId);
        Event event = get(eventId);
        if (eventUserEntity != null
                && event != null
                && (!event.isCheckinCodeRequired()
                        || (event.isCheckinCodeRequired()
                                && event.getCheckinCode() != null
                                && event.getCheckinCode().equalsIgnoreCase(code)))) {
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
    public Long isCheckedIn(Long userId) throws ResourceNotFoundException {
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
    public Long getCurrentEvent() throws ResourceNotFoundException {
        Long eventId = null;
        List<Event> events = getAll();
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

    /**
     * Initializes Hazelcast cache
     */
    private void initCache() {
        if (eventCache == null) {
            //hazelcastInstance = Hazelcast.newHazelcastInstance(new Config());
            eventCache = hazelcastInstance.getMap("events");
        }
    }

}
