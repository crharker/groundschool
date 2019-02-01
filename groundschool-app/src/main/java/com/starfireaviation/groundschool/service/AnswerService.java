/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
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
    public Answer store(final Long questionId, final Answer answer);

    /**
     * Deletes a answer
     *
     * @param id Long
     * @return Answer
     * @throws ResourceNotFoundException when no answer is found
     */
    public Answer delete(final long id) throws ResourceNotFoundException;

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
    public List<Answer> findByQuestionId(final Long questionId);

    /**
     * Gets a answer
     *
     * @param id Long
     * @return Answer
     * @throws ResourceNotFoundException when no answer is found
     */
    public Answer findAnswerById(final long id) throws ResourceNotFoundException;

}
