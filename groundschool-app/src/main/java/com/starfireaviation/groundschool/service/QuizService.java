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
     * @param partial only store base quiz data
     * @return Quiz
     */
    public Quiz store(final Quiz quiz, final boolean partial);

    /**
     * Deletes a quiz
     *
     * @param id Long
     * @return Quiz
     */
    public Quiz delete(final long id);

    /**
     * Gets all quizzes
     *
     * @return list of Quiz
     */
    public List<Quiz> findAllQuizzes();

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
     * @param partial only load base quiz data
     * @return Quiz
     */
    public Quiz findById(final long id, final boolean partial);

    /**
     * Starts a quiz
     *
     * @param quizId Long
     * @return started quiz
     * @throws ResourceNotFoundException when things go wrong
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
     */
    public Quiz addQuestion(final long quizId, final long questionId);

    /**
     * Removes a question from a quiz
     *
     * @param quizId Long
     * @param questionId Long
     * @return Quiz with question added
     */
    public Quiz removeQuestion(final long quizId, final long questionId);

    /**
     * Determines the next question for a quiz.
     *
     * Note: returns null if no "next" question is available
     *
     * @param quizId Quiz ID
     * @param userId User ID
     * @return next question ID
     */
    public Long getNextQuestion(final Long quizId, final Long userId);
}
