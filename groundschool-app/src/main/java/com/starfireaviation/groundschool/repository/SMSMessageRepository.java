/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.SMSMessageEntity;

/**
 * SMSMessageRepository
 *
 * @author brianmichael
 */
public interface SMSMessageRepository extends Repository<SMSMessageEntity, Long> {

    /**
     * Deletes a SMSMessageEntity
     *
     * @param smsMessageEntity SMSMessageEntity
     */
    void delete(SMSMessageEntity smsMessageEntity);

    /**
     * Gets all SMSMessageEntities
     *
     * @return list of SMSMessageEntity
     */
    List<SMSMessageEntity> findAll();

    /**
     * Gets a SMSMessageEntity
     *
     * @param id Long
     * @return SMSMessageEntity
     */
    SMSMessageEntity findById(long id);

    /**
     * Finds SMSMessageEntities by TO number
     *
     * @param to number
     * @return list of SMSMessageEntity
     */
    List<SMSMessageEntity> findByTo(String to);

    /**
     * Finds SMSMessageEntities by user ID
     *
     * @param userId user ID
     * @return list of SMSMessageEntity
     */
    List<SMSMessageEntity> findByUserId(Long userId);

    /**
     * Saves a SMSMessageEntity
     *
     * @param smsMessageEntity SMSMessageEntity
     * @return SMSMessageEntity
     */
    SMSMessageEntity save(SMSMessageEntity smsMessageEntity);
}
