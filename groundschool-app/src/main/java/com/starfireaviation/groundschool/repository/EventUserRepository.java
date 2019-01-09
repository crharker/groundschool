/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.EventUserEntity;

/**
 * EventUserRepository
 *
 * @author brianmichael
 */
public interface EventUserRepository extends Repository<EventUserEntity, Long> {

    /**
     * Deletes a event user
     *
     * @param eventUser EventUser
     */
    void delete(EventUserEntity eventUser);

    /**
     * Gets all eventUsers
     *
     * @return list of EventUser
     */
    List<EventUserEntity> findAll();

    /**
     * Gets a eventUser
     *
     * @param id Long
     * @return EventUser
     */
    EventUserEntity findById(long id);

    /**
     * Finds all EventUser by eventId
     *
     * @param eventId event ID
     * @return list of EventUser
     */
    List<EventUserEntity> findByEventId(long eventId);

    /**
     * Finds all EventUser by userId
     *
     * @param userId user ID
     * @return list of EventUser
     */
    List<EventUserEntity> findByUserId(long userId);

    /**
     * Saves a eventUser
     *
     * @param eventUser EventUser
     * @return EventUser
     */
    EventUserEntity save(EventUserEntity eventUser);
}
