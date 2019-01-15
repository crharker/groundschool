/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.EventParticipantEntity;

/**
 * EventUserRepository
 *
 * @author brianmichael
 */
public interface EventParticipantRepository extends Repository<EventParticipantEntity, Long> {

    /**
     * Deletes a event user
     *
     * @param eventUser EventUser
     */
    void delete(EventParticipantEntity eventUser);

    /**
     * Gets all eventUsers
     *
     * @return list of EventUser
     */
    List<EventParticipantEntity> findAll();

    /**
     * Gets a eventUser
     *
     * @param id Long
     * @return EventUser
     */
    EventParticipantEntity findById(long id);

    /**
     * Finds all EventUser by eventId
     *
     * @param eventId event ID
     * @return list of EventUser
     */
    List<EventParticipantEntity> findByEventId(long eventId);

    /**
     * Finds all EventUser by userId
     *
     * @param userId user ID
     * @return list of EventUser
     */
    List<EventParticipantEntity> findByUserId(long userId);

    /**
     * Finds all EventUser by eventId and userId
     *
     * @param eventId Event ID
     * @param userId user ID
     * @return list of EventUser
     */
    @Query(value = "SELECT * FROM event_participant WHERE event_id = ?1 AND user_id = ?2 LIMIT 1", nativeQuery = true)
    EventParticipantEntity findByEventAndUserId(long eventId, long userId);

    /**
     * Saves a eventUser
     *
     * @param eventUser EventUser
     * @return EventUser
     */
    EventParticipantEntity save(EventParticipantEntity eventUser);
}
