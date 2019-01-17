/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

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
     */
    public LessonPlan delete(long id);

    /**
     * Gets all lessonPlan
     *
     * @return list of LessonPlan
     */
    public List<LessonPlan> findAllLessonPlans();

    /**
     * Gets a lessonPlan
     *
     * @param id Long
     * @return LessonPlan
     */
    public LessonPlan findById(long id);

}
