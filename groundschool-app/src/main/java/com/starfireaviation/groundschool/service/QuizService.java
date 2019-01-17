/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.time.LocalDateTime;
import java.util.List;

import com.starfireaviation.groundschool.model.Question;
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
     * Gets all quizzes for a given lesson plan
     *
     * @param lessonPlanId Long
     * @return list of Quiz
     */
    public List<Quiz> findQuizzesByLessonPlanId(Long lessonPlanId);

    /**
     * Gets a quiz
     *
     * @param id Long
     * @return Quiz
     */
    public Quiz findById(long id);

    /**
     * Starts a quiz
     *
     * @param quizId Long
     * @return started quiz
     */
    public Quiz start(long quizId);

    /**
     * Starts the next quiz question
     *
     * @param quizId Long
     * @return started quiz
     */
    public Quiz startQuestion(long quizId);

    /**
     * Complete's a quiz
     *
     * @param quizId Long
     * @return completed quiz
     */
    public Quiz complete(long quizId);

    /**
     * Complete's a quiz question
     *
     * @param quizId Long
     * @return completed quiz
     */
    public Quiz completeQuestion(long quizId);

    /**
     * Gets the current question for a quiz
     *
     * @param quizId Long
     * @return Question
     */
    public Question getCurrentQuestion(long quizId);

    /**
     * Gets the current question start time for a quiz
     *
     * @param quizId Long
     * @return Question start time
     */
    public LocalDateTime getCurrentQuestionStart(long quizId);
}
