/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Lesson;

/**
 * LessonService
 *
 * @author brianmichael
 */
public interface LessonService {

    /**
     * Creates a lesson
     *
     * @param lesson Lesson
     * @return Lesson
     */
    public Lesson store(Lesson lesson);

    /**
     * Deletes a lesson
     *
     * @param id Long
     * @return Lesson
     * @throws ResourceNotFoundException when lesson is not found
     */
    public Lesson delete(long id) throws ResourceNotFoundException;

    /**
     * Gets all lesson
     *
     * @return list of Lesson
     * @throws ResourceNotFoundException when lesson is not found
     */
    public List<Lesson> getAll() throws ResourceNotFoundException;

    /**
     * Gets all lessons attended by a participant
     *
     * @param userId User ID
     * @return list of Lesson
     * @throws ResourceNotFoundException when statistic is not found
     */
    public List<Lesson> getAttendedLessons(Long userId) throws ResourceNotFoundException;

    /**
     * Gets a lesson
     *
     * @param id Long
     * @return Lesson
     * @throws ResourceNotFoundException when lesson is not found
     */
    public Lesson get(long id) throws ResourceNotFoundException;

}
