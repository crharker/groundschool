/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.AnswerEntity;

/**
 * AnswerRepository
 *
 * @author brianmichael
 */
public interface AnswerRepository extends Repository<AnswerEntity, Long> {

    /**
     * Deletes a answer
     *
     * @param answer Answer
     */
    void delete(AnswerEntity answer);

    /**
     * Gets all answers
     *
     * @return list of Answer
     */
    List<AnswerEntity> findAll();

    /**
     * Gets all answers for a question
     *
     * @param questionId question ID
     * @return list of Answer
     */
    List<AnswerEntity> findAllAnswerByQuestionId(Long questionId);

    /**
     * Gets a answer
     *
     * @param id Long
     * @return Answer
     */
    AnswerEntity findById(long id);

    /**
     * Saves a answer
     *
     * @param answer Answer
     * @return Answer
     */
    AnswerEntity save(AnswerEntity answer);
}
