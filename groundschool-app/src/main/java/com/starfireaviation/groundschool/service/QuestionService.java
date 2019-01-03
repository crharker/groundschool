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

}
