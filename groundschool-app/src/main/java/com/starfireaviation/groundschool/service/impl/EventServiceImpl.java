/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.User;
import com.starfireaviation.groundschool.model.sql.EventEntity;
import com.starfireaviation.groundschool.repository.EventRepository;
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
    public void rsvp(Event event, User user, boolean confirm) {
        // TODO Auto-generated method stub
    }

}
