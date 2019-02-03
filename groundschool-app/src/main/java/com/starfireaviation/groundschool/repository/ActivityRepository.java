/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.ActivityEntity;

/**
 * ActivityRepository
 *
 * @author brianmichael
 */
public interface ActivityRepository extends Repository<ActivityEntity, Long> {

    /**
     * Deletes an activity
     *
     * @param activity Activity
     */
    void delete(ActivityEntity activity);

    /**
     * Gets all activities
     *
     * @return list of Activity
     */
    List<ActivityEntity> findAll();

    /**
     * Gets all addresses for an event
     *
     * @param lessonPlanId LessonPlan ID
     * @return list of Activity
     */
    List<ActivityEntity> findActivityByLessonPlanId(Long lessonPlanId);

    /**
     * Gets an activity
     *
     * @param id Long
     * @return Activity
     */
    ActivityEntity findById(long id);

    /**
     * Saves a activity
     *
     * @param activity Activity
     * @return Activity
     */
    ActivityEntity save(ActivityEntity activity);
}
