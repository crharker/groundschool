/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.EventInstructorEntity;

/**
 * EventUserRepository
 *
 * @author brianmichael
 */
public interface EventInstructorRepository extends Repository<EventInstructorEntity, Long> {

    /**
     * Deletes a event instructor
     *
     * @param instructor EventUser
     */
    void delete(EventInstructorEntity instructor);

    /**
     * Gets all instructors
     *
     * @return list of EventUser
     */
    List<EventInstructorEntity> findAll();

    /**
     * Gets a instructor
     *
     * @param id Long
     * @return EventUser
     */
    EventInstructorEntity findById(long id);

    /**
     * Finds all EventUser by eventId
     *
     * @param eventId event ID
     * @return list of EventUser
     */
    List<EventInstructorEntity> findByEventId(long eventId);

    /**
     * Finds all EventUser by userId
     *
     * @param userId user ID
     * @return list of EventUser
     */
    List<EventInstructorEntity> findByUserId(long userId);

    /**
     * Finds all EventUser by eventId and userId
     *
     * @param eventId Event ID
     * @param userId user ID
     * @return list of EventUser
     */
    @Query(value = "SELECT * FROM event_instructor WHERE event_id = ?1 AND user_id = ?2 LIMIT 1", nativeQuery = true)
    EventInstructorEntity findByEventAndUserId(long eventId, long userId);

    /**
     * Saves a eventUser
     *
     * @param eventUser EventUser
     * @return EventUser
     */
    EventInstructorEntity save(EventInstructorEntity eventUser);
}
