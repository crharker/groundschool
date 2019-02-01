/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.QuizQuestionEntity;

/**
 * QuizQuestionRepository
 *
 * @author brianmichael
 */
public interface QuizQuestionRepository extends Repository<QuizQuestionEntity, Long> {

    /**
     * Deletes a QuizQuestionEntity
     *
     * @param quizQuestionEntity QuizQuestionEntity
     */
    void delete(QuizQuestionEntity quizQuestionEntity);

    /**
     * Gets all QuizQuestionEntity
     *
     * @return list of QuizQuestionEntity
     */
    List<QuizQuestionEntity> findAll();

    /**
     * Gets a QuizQuestionEntity
     *
     * @param id Long
     * @return QuizQuestionEntity
     */
    QuizQuestionEntity findById(long id);

    /**
     * Gets all QuizQuestionEntity for a given Question ID
     *
     * @param id Long
     * @return QuizQuestionEntity
     */
    List<QuizQuestionEntity> findByQuestionId(long id);

    /**
     * Gets all QuizQuestionEntity for a given Quiz ID
     *
     * @param id Long
     * @return QuizQuestionEntity
     */
    List<QuizQuestionEntity> findByQuizId(long id);

    /**
     * Finds the QuizQuestionEntity by questionId and quizId
     *
     * @param questionId Question ID
     * @param quizId Quiz ID
     * @return list of EventUser
     */
    @Query(value = "SELECT * FROM quiz_question WHERE question_id = ?1 AND quiz_id = ?2 LIMIT 1",
            nativeQuery = true)
    QuizQuestionEntity findByQuizAndQuestionId(long quizId, long questionId);

    /**
     * Saves a QuestionReferenceMaterialEntity
     *
     * @param quizQuestionEntity QuestionReferenceMaterialEntity
     * @return QuestionReferenceMaterialEntity
     */
    QuizQuestionEntity save(QuizQuestionEntity quizQuestionEntity);
}
