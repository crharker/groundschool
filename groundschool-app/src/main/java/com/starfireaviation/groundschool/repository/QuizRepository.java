/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.QuizEntity;

/**
 * QuizRepository
 *
 * @author brianmichael
 */
public interface QuizRepository extends Repository<QuizEntity, Long> {

    /**
     * Deletes a quiz
     *
     * @param quiz Quiz
     */
    void delete(QuizEntity quiz);

    /**
     * Gets all quizzes
     *
     * @return list of Quiz
     */
    List<QuizEntity> findAll();

    /**
     * Gets a quiz
     *
     * @param id Long
     * @return Quiz
     */
    QuizEntity findById(long id);

    /**
     * Saves a quiz
     *
     * @param quiz Quiz
     * @return Quiz
     */
    QuizEntity save(QuizEntity quiz);
}
