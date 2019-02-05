/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EventEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "EVENT")
public class EventEntity extends BaseEntity {

    /**
     * Title
     */
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    /**
     * LessonPlan ID
     */
    @Column(name = "lesson_plan_id", nullable = false)
    private Long lessonPlanId;

    /**
     * Event started?
     */
    @Column(name = "started", nullable = false)
    private boolean started;

    /**
     * LocalDateTime - startTime
     */
    @Column(name = "start_time")
    private LocalDateTime startTime;

    /**
     * Event completed?
     */
    @Column(name = "completed", nullable = false)
    private boolean completed;

    /**
     * LocalDateTime - completedTime
     */
    @Column(name = "completed_time")
    private LocalDateTime completedTime;

    /**
     * Google calendar URL
     */
    @Column(name = "calendar_url", nullable = true, length = 255)
    private String calendarUrl;

    /**
     * Checkin code
     */
    @Column(name = "checkin_code", nullable = true, length = 4)
    private String checkinCode;

    /**
     * Checkin code required
     */
    @Column(name = "checkin_code_required", nullable = false)
    private boolean checkinCodeRequired;

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
     * Retrieves the value for {@link #startTime}.
     *
     * @return the current value
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Provides a value for {@link #startTime}.
     *
     * @param startTime the new value to set
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the value for {@link #calendarUrl}.
     *
     * @return the current value
     */
    public String getCalendarUrl() {
        return calendarUrl;
    }

    /**
     * Provides a value for {@link #calendarUrl}.
     *
     * @param calendarUrl the new value to set
     */
    public void setCalendarUrl(String calendarUrl) {
        this.calendarUrl = calendarUrl;
    }

    /**
     * Retrieves the value for {@link #checkinCode}.
     *
     * @return the current value
     */
    public String getCheckinCode() {
        return checkinCode;
    }

    /**
     * Provides a value for {@link #checkinCode}.
     *
     * @param checkinCode the new value to set
     */
    public void setCheckinCode(String checkinCode) {
        this.checkinCode = checkinCode;
    }

    /**
     * Retrieves the value for {@link #checkinCodeRequired}.
     *
     * @return the current value
     */
    public boolean isCheckinCodeRequired() {
        return checkinCodeRequired;
    }

    /**
     * Provides a value for {@link #checkinCodeRequired}.
     *
     * @param checkinCodeRequired the new value to set
     */
    public void setCheckinCodeRequired(boolean checkinCodeRequired) {
        this.checkinCodeRequired = checkinCodeRequired;
    }

    /**
     * Retrieves the value for {@link #started}.
     *
     * @return the current value
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Provides a value for {@link #started}.
     *
     * @param started the new value to set
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * Retrieves the value for {@link #completed}.
     *
     * @return the current value
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Provides a value for {@link #completed}.
     *
     * @param completed the new value to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Retrieves the value for {@link #completedTime}.
     *
     * @return the current value
     */
    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    /**
     * Provides a value for {@link #completedTime}.
     *
     * @param completedTime the new value to set
     */
    public void setCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
    }

    /**
     * Retrieves the value for {@link #lessonPlanId}.
     *
     * @return the current value
     */
    public Long getLessonPlanId() {
        return lessonPlanId;
    }

    /**
     * Provides a value for {@link #lessonPlanId}.
     *
     * @param lessonPlanId the new value to set
     */
    public void setLessonPlanId(Long lessonPlanId) {
        this.lessonPlanId = lessonPlanId;
    }

}
