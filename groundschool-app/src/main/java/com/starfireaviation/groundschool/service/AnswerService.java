/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.model.Answer;

/**
 * AnswerService
 *
 * @author brianmichael
 */
public interface AnswerService {

    /**
     * Creates a answer
     *
     * @param questionId Long
     * @param answer Answer
     * @return Answer
     */
    public Answer store(Long questionId, Answer answer);

    /**
     * Deletes a answer
     *
     * @param id Long
     * @return Answer
     */
    public Answer delete(long id);

    /**
     * Gets all answers
     *
     * @return list of Answer
     */
    public List<Answer> findAllAnswers();

    /**
     * Gets all answers for a question
     *
     * @param questionId Long
     * @return list of Answer
     */
    public List<Answer> findByQuestionId(Long questionId);

    /**
     * Gets a answer
     *
     * @param id Long
     * @return Answer
     */
    public Answer findAnswerById(long id);

}
