/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.LessonPlan;

/**
 * LessonPlanService
 *
 * @author brianmichael
 */
public interface LessonPlanService {

    /**
     * Creates a lessonPlan
     *
     * @param lessonPlan LessonPlan
     * @return LessonPlan
     */
    public LessonPlan store(LessonPlan lessonPlan);

    /**
     * Deletes a lessonPlan
     *
     * @param id Long
     * @return LessonPlan
     * @throws ResourceNotFoundException when lesson plan is not found
     */
    public LessonPlan delete(long id) throws ResourceNotFoundException;

    /**
     * Gets all lessonPlan
     *
     * @return list of LessonPlan
     * @throws ResourceNotFoundException when lesson plan is not found
     */
    public List<LessonPlan> getAll() throws ResourceNotFoundException;

    /**
     * Gets a lessonPlan
     *
     * @param id Long
     * @return LessonPlan
     * @throws ResourceNotFoundException when lesson plan is not found
     */
    public LessonPlan get(long id) throws ResourceNotFoundException;

}
