/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.QuestionEntity;

/**
 * QuestionRepository
 *
 * @author brianmichael
 */
public interface QuestionRepository extends Repository<QuestionEntity, Long> {

    /**
     * Deletes a question
     *
     * @param question Question
     */
    void delete(QuestionEntity question);

    /**
     * Gets all questions
     *
     * @return list of Question
     */
    List<QuestionEntity> findAll();

    /**
     * Gets a question
     *
     * @param id Long
     * @return Question
     */
    QuestionEntity findById(long id);

    /**
     * Saves a question
     *
     * @param question Question
     * @return Question
     */
    QuestionEntity save(QuestionEntity question);
}
