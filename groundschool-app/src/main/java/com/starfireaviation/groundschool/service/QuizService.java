/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
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
    public Quiz store(final Quiz quiz);

    /**
     * Deletes a quiz
     *
     * @param id Long
     * @return Quiz
     * @throws ResourceNotFoundException when quiz is not found
     */
    public Quiz delete(final long id) throws ResourceNotFoundException;

    /**
     * Gets all quizzes
     *
     * @return list of Quiz
     * @throws ResourceNotFoundException when quiz is not found
     */
    public List<Quiz> getAll() throws ResourceNotFoundException;

    /**
     * Gets all quizzes for a given lesson plan
     *
     * @param lessonPlanId Long
     * @return list of Quiz
     */
    public List<Quiz> findQuizzesByLessonPlanId(final Long lessonPlanId);

    /**
     * Gets a quiz
     *
     * @param id Long
     * @return Quiz
     * @throws ResourceNotFoundException when quiz is not found
     */
    public Quiz get(final long id) throws ResourceNotFoundException;

    /**
     * Starts a quiz
     *
     * @param quizId Long
     * @return started quiz
     * @throws ResourceNotFoundException when quiz is not found
     */
    public Quiz start(final long quizId) throws ResourceNotFoundException;

    /**
     * Complete's a quiz
     *
     * @param quizId Long
     * @return completed quiz
     */
    public Quiz complete(final long quizId);

    /**
     * Gets the current quiz
     *
     * The response is null if there is no active quiz
     *
     * @return Quiz ID
     */
    public Long getCurrentQuiz();

    /**
     * Adds a question to a quiz
     *
     * @param quizId Long
     * @param questionId Long
     * @return Quiz with question added
     * @throws ResourceNotFoundException when quiz or question is not found
     */
    public Quiz addQuestion(final long quizId, final long questionId) throws ResourceNotFoundException;

    /**
     * Removes a question from a quiz
     *
     * @param quizId Long
     * @param questionId Long
     * @return Quiz with question added
     * @throws ResourceNotFoundException when quiz or question is not found
     */
    public Quiz removeQuestion(final long quizId, final long questionId) throws ResourceNotFoundException;

    /**
     * Determines the next question for a quiz.
     *
     * Note: returns null if no "next" question is available
     *
     * @param quizId Quiz ID
     * @param userId User ID
     * @return next question ID
     * @throws ResourceNotFoundException when statistic is not found
     */
    public Long getNextQuestion(final Long quizId, final Long userId) throws ResourceNotFoundException;
}
