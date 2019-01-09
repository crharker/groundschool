/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.sql.EventEntity;
import com.starfireaviation.groundschool.model.sql.EventUserEntity;
import com.starfireaviation.groundschool.repository.EventRepository;
import com.starfireaviation.groundschool.repository.EventUserRepository;
import com.starfireaviation.groundschool.service.EventService;

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
    private EventUserRepository eventUserRepository;

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
            events.add(mapper.map(eventEntity, Event.class));
        }
        return events;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Event findById(long id) {
        return mapper.map(eventRepository.findById(id), Event.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void rsvp(Long eventId, Long userId, boolean confirm) {
        if (eventId == null || userId == null) {
            return;
        }
        List<EventUserEntity> eventUserEntities = eventUserRepository.findByEventId(eventId);
        for (EventUserEntity eventUserEntity : eventUserEntities) {
            if (userId == eventUserEntity.getUserId()) {
                if (confirm) {
                    eventUserEntity.setConfirmed(true);
                    eventUserEntity.setDeclined(false);
                    eventUserEntity.setTime(LocalDateTime.now());
                } else {
                    eventUserEntity.setConfirmed(false);
                    eventUserEntity.setDeclined(true);
                    eventUserEntity.setTime(LocalDateTime.now());
                }
                eventUserRepository.save(eventUserEntity);
            }
            break;
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
        EventUserEntity eventUserEntity = new EventUserEntity();
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
        List<EventUserEntity> eventUserEntities = eventUserRepository.findByEventId(eventId);
        for (EventUserEntity eventUserEntity : eventUserEntities) {
            if (userId == eventUserEntity.getUserId()) {
                eventUserRepository.delete(eventUserEntity);
                break;
            }
        }
    }

}
