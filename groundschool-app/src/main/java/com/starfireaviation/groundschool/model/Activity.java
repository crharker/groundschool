/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

/**
 * Activity
 *
 * @author brianmichael
 */
public class Activity extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Title
     */
    private String title;

    /**
     * Duration (in seconds)
     */
    private long duration;

    /**
     * ActivityType
     */
    private ActivityType activityType;

    /**
     * Reference ID (I.E. quiz ID)
     */
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
