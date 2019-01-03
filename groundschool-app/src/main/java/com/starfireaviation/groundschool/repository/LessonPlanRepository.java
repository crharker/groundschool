/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.LessonPlanEntity;

/**
 * LessonPlanRepository
 *
 * @author brianmichael
 */
public interface LessonPlanRepository extends Repository<LessonPlanEntity, Long> {

    /**
     * Deletes a lessonPlan
     *
     * @param lessonPlan LessonPlanEntity
     */
    void delete(LessonPlanEntity lessonPlan);

    /**
     * Gets all lessonPlan
     *
     * @return list of LessonPlanEntity
     */
    List<LessonPlanEntity> findAll();

    /**
     * Gets a lessonPlan
     *
     * @param id Long
     * @return LessonPlanEntity
     */
    LessonPlanEntity findById(long id);

    /**
     * Saves a lessonPlan
     *
     * @param lessonPlan LessonPlanEntity
     * @return LessonPlanEntity
     */
    LessonPlanEntity save(LessonPlanEntity lessonPlan);
}
