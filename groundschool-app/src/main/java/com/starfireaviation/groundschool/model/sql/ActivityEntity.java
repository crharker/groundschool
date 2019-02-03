/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starfireaviation.groundschool.model.ActivityType;

/**
 * ActivityEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "ACTIVITY")
public class ActivityEntity extends BaseEntity {

    /**
     * Title
     */
    @Column(name = "title", length = 100)
    private String title;

    /**
     * Duration (in seconds)
     */
    @Column(name = "duration")
    private long duration;

    /**
     * Event
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lesson_plan_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LessonPlanEntity lessonPlan;

    /**
     * Activity Type (Quiz, Lecture, etc)
     */
    @Column(name = "type", length = 100)
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    /**
     * Reference ID (I.E. quiz ID)
     */
    @Column(name = "reference_id")
    private Long referenceId;

    /**
     * Retrieves the value for {@link #title}.
     *
     * @return the current value
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides a value for {@link #title}.
     *
     * @param title the new value to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the value for {@link #duration}.
     *
     * @return the current value
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Provides a value for {@link #duration}.
     *
     * @param duration the new value to set
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * Retrieves the value for {@link #lessonPlan}.
     *
     * @return the current value
     */
    public LessonPlanEntity getLessonPlan() {
        return lessonPlan;
    }

    /**
     * Provides a value for {@link #lessonPlan}.
     *
     * @param lessonPlan the new value to set
     */
    public void setLessonPlan(LessonPlanEntity lessonPlan) {
        this.lessonPlan = lessonPlan;
    }

    /**
     * Retrieves the value for {@link #activityType}.
     *
     * @return the current value
     */
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * Provides a value for {@link #activityType}.
     *
     * @param activityType the new value to set
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    /**
     * Retrieves the value for {@link #referenceId}.
     *
     * @return the current value
     */
    public Long getReferenceId() {
        return referenceId;
    }

    /**
     * Provides a value for {@link #referenceId}.
     *
     * @param referenceId the new value to set
     */
    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

}
