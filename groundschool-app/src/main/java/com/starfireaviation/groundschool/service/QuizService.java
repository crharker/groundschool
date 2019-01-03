/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.model.Quiz;

/**
 * QuizService
 *
 * @author brianmichael
 */
public interface QuizService {

    /**
     * Creates a quiz
     *
     * @param quiz Quiz
     * @return Quiz
     */
    public Quiz store(Quiz quiz);

    /**
     * Deletes a quiz
     *
     * @param id Long
     * @return Quiz
     */
    public Quiz delete(long id);

    /**
     * Gets all quizzes
     *
     * @return list of Quiz
     */
    public List<Quiz> findAllQuizzes();

    /**
     * Gets a quiz
     *
     * @param id Long
     * @return Quiz
     */
    public Quiz findById(long id);

}
