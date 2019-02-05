/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.MessageEntity;

/**
 * MessageRepository
 *
 * @author brianmichael
 */
public interface MessageRepository extends Repository<MessageEntity, Long> {

    /**
     * Deletes a MessageEntity
     *
     * @param messageEntity MessageEntity
     */
    void delete(MessageEntity messageEntity);

    /**
     * Gets all MessageEntities
     *
     * @return list of MessageEntity
     */
    List<MessageEntity> findAll();

    /**
     * Gets a MessageEntity
     *
     * @param id Long
     * @return MessageEntity
     */
    MessageEntity findById(long id);

    /**
     * Finds MessageEntities by to
     *
     * @param to number
     * @return list of MessageEntity
     */
    List<MessageEntity> findByTo(String to);

    /**
     * Finds MessageEntities by user ID
     *
     * @param userId user ID
     * @return list of MessageEntity
     */
    List<MessageEntity> findByUserId(Long userId);

    /**
     * Saves a MessageEntity
     *
     * @param messageEntity MessageEntity
     * @return MessageEntity
     */
    MessageEntity save(MessageEntity messageEntity);
}
