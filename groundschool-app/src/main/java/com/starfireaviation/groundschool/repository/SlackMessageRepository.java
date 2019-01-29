/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.SlackMessageEntity;

/**
 * SlackMessageRepository
 *
 * @author brianmichael
 */
public interface SlackMessageRepository extends Repository<SlackMessageEntity, Long> {

    /**
     * Deletes a SlackMessageEntity
     *
     * @param slackMessageEntity SlackMessageEntity
     */
    void delete(SlackMessageEntity slackMessageEntity);

    /**
     * Gets all SlackMessageEntities
     *
     * @return list of SlackMessageEntity
     */
    List<SlackMessageEntity> findAll();

    /**
     * Gets a SlackMessageEntity
     *
     * @param id Long
     * @return SlackMessageEntity
     */
    SlackMessageEntity findById(long id);

    /**
     * Finds SlackMessageEntities by TO number
     *
     * @param to number
     * @return list of SlackMessageEntity
     */
    List<SlackMessageEntity> findByTo(String to);

    /**
     * Finds SlackMessageEntities by user ID
     *
     * @param userId user ID
     * @return list of SlackMessageEntity
     */
    List<SlackMessageEntity> findByUserId(Long userId);

    /**
     * Saves a SlackMessageEntity
     *
     * @param slackMessageEntity SlackMessageEntity
     * @return SlackMessageEntity
     */
    SlackMessageEntity save(SlackMessageEntity slackMessageEntity);
}
