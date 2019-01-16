/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.time.LocalDateTime;

/**
 * User
 *
 * @author brianmichael
 */
public class Event extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Title
     */
    private String title;

    /**
     * Event started?
     */
    private boolean started = false;

    /**
     * LocalDateTime - startTime
     */
    private LocalDateTime startTime;

    /**
     * Event completed?
     */
    private boolean completed = false;

    /**
     * LocalDateTime - completedTime
     */
    private LocalDateTime completedTime;

    /**
     * Google calendar URL
     */
    private String calendarUrl;

    /**
     * Checkin code
     */
    private String checkinCode;

    /**
     * Address
     */
    private Address address;

    /**
     * LessonPlan ID
     */
    private Long lessonPlanId;

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
     * Retrieves the value for {@link #address}.
     *
     * @return the current value
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Provides a value for {@link #address}.
     *
     * @param address the new value to set
     */
    public void setAddress(Address address) {
        this.address = address;
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
    public void setLessonPlan(Long lessonPlanId) {
        this.lessonPlanId = lessonPlanId;
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

}
