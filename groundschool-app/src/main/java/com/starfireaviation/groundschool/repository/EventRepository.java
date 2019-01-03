/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.EventEntity;

/**
 * EventRepository
 *
 * @author brianmichael
 */
public interface EventRepository extends Repository<EventEntity, Long> {

    /**
     * Deletes a event
     *
     * @param user Event
     */
    void delete(EventEntity user);

    /**
     * Gets all events
     *
     * @return list of Events
     */
    List<EventEntity> findAll();

    /**
     * Gets a event
     *
     * @param id Long
     * @return Event
     */
    EventEntity findById(long id);

    /**
     * Saves a event
     *
     * @param user Event
     * @return Event
     */
    EventEntity save(EventEntity user);
}
