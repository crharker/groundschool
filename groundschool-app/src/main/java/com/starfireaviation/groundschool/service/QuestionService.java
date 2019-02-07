/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.Date;
import java.util.List;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
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
     * @throws ResourceNotFoundException when reference material is not found
     */
    public Question store(Question question) throws ResourceNotFoundException;

    /**
     * Deletes a question
     *
     * @param id Long
     * @return Question
     * @throws ResourceNotFoundException when question is not found
     */
    public Question delete(long id) throws ResourceNotFoundException;

    /**
     * Gets all questions
     *
     * @return list of Question
     * @throws ResourceNotFoundException when question is not found
     */
    public List<Question> getAll() throws ResourceNotFoundException;

    /**
     * Gets a question
     *
     * @param id Long
     * @return Question
     * @throws ResourceNotFoundException when question is not found
     */
    public Question get(final long id) throws ResourceNotFoundException;

    /**
     * Answers a question for a user
     *
     * @param questionId Long
     * @param userId Long
     * @param selection Long
     * @param startTime when question was asked
     * @return answered correctly?
     * @throws ResourceNotFoundException when question or user is not found
     */
    public boolean answer(long questionId, long userId, String selection, Date startTime)
            throws ResourceNotFoundException;

    /**
     * Assigns reference material to a question
     *
     * @param questionId Long
     * @param referenceMaterialId Long
     * @return assignment successful
     * @throws ResourceNotFoundException when question is not found
     */
    public boolean assignReferenceMaterial(long questionId, long referenceMaterialId) throws ResourceNotFoundException;

    /**
     * Unassigns reference material from a question
     *
     * @param questionId Long
     * @param referenceMaterialId Long
     * @return unassignment successful
     * @throws ResourceNotFoundException when question is not found
     */
    public boolean unassignReferenceMaterial(long questionId, long referenceMaterialId)
            throws ResourceNotFoundException;
}
