/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.model.Question;

/**
 * QuestionService
 *
 * @author brianmichael
 */
public interface QuestionService {

    /**
     * Creates a question
     *
     * @param question Question
     * @return Question
     */
    public Question store(Question question);

    /**
     * Deletes a question
     *
     * @param id Long
     * @return Question
     */
    public Question delete(long id);

    /**
     * Gets all questions
     *
     * @return list of Question
     */
    public List<Question> findAllQuestions();

    /**
     * Gets a question
     *
     * @param id Long
     * @return Question
     */
    public Question findQuestionById(long id);

    /**
     * Answers a question for a user
     *
     * @param questionId Long
     * @param userId Long
     * @param selection Long
     * @return answered correctly?
     */
    public boolean answer(long questionId, long userId, String selection);

    /**
     * Assigns reference material to a question
     *
     * @param questionId Long
     * @param referenceMaterialId Long
     * @return assignment successful
     */
    public boolean assignReferenceMaterial(long questionId, long referenceMaterialId);

    /**
     * Unassigns reference material from a question
     *
     * @param questionId Long
     * @param referenceMaterialId Long
     * @return unassignment successful
     */
    public boolean unassignReferenceMaterial(long questionId, long referenceMaterialId);
}
