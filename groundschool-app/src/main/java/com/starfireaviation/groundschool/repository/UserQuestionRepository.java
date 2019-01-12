/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.UserQuestionEntity;

/**
 * UserQuestionRepository
 *
 * @author brianmichael
 */
public interface UserQuestionRepository extends Repository<UserQuestionEntity, Long> {

    /**
     * Deletes a UserQuestion
     *
     * @param userQuestion UserQuestion
     */
    void delete(UserQuestionEntity userQuestion);

    /**
     * Gets all UserQuestion
     *
     * @return list of UserQuestion
     */
    List<UserQuestionEntity> findAll();

    /**
     * Gets a UserQuestion
     *
     * @param id Long
     * @return UserQuestion
     */
    UserQuestionEntity findById(long id);

    /**
     * Saves a UserQuestion
     *
     * @param userQuestion UserQuestion
     * @return Question
     */
    UserQuestionEntity save(UserQuestionEntity userQuestion);
}
