/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.util.List;

/**
 * LessonPlan
 *
 * @author brianmichael
 */
public class LessonPlan extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Title
     */
    private String title;

    /**
     * Summary
     */
    private String summary;

    /**
     * Objective
     */
    private String objective;

    /**
     * Content
     */
    private String content;

    /**
     * Schedule
     */
    private String schedule;

    /**
     * Equipment
     */
    private String equipment;

    /**
     * Instructor's Actions
     */
    private String instructorActions;

    /**
     * Student's Actions
     */
    private String studentActions;

    /**
     * Completion Standards
     */
    private String completionStandards;

    /**
     * Activities
     */
    private List<Activity> activities;

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
     * Retrieves the value for {@link #summary}.
     *
     * @return the current value
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Provides a value for {@link #summary}.
     *
     * @param summary the new value to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Retrieves the value for {@link #objective}.
     *
     * @return the current value
     */
    public String getObjective() {
        return objective;
    }

    /**
     * Provides a value for {@link #objective}.
     *
     * @param objective the new value to set
     */
    public void setObjective(String objective) {
        this.objective = objective;
    }

    /**
     * Retrieves the value for {@link #content}.
     *
     * @return the current value
     */
    public String getContent() {
        return content;
    }

    /**
     * Provides a value for {@link #content}.
     *
     * @param content the new value to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retrieves the value for {@link #schedule}.
     *
     * @return the current value
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * Provides a value for {@link #schedule}.
     *
     * @param schedule the new value to set
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * Retrieves the value for {@link #equipment}.
     *
     * @return the current value
     */
    public String getEquipment() {
        return equipment;
    }

    /**
     * Provides a value for {@link #equipment}.
     *
     * @param equipment the new value to set
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    /**
     * Retrieves the value for {@link #instructorActions}.
     *
     * @return the current value
     */
    public String getInstructorActions() {
        return instructorActions;
    }

    /**
     * Provides a value for {@link #instructorActions}.
     *
     * @param instructorActions the new value to set
     */
    public void setInstructorActions(String instructorActions) {
        this.instructorActions = instructorActions;
    }

    /**
     * Retrieves the value for {@link #studentActions}.
     *
     * @return the current value
     */
    public String getStudentActions() {
        return studentActions;
    }

    /**
     * Provides a value for {@link #studentActions}.
     *
     * @param studentActions the new value to set
     */
    public void setStudentActions(String studentActions) {
        this.studentActions = studentActions;
    }

    /**
     * Retrieves the value for {@link #completionStandards}.
     *
     * @return the current value
     */
    public String getCompletionStandards() {
        return completionStandards;
    }

    /**
     * Provides a value for {@link #completionStandards}.
     *
     * @param completionStandards the new value to set
     */
    public void setCompletionStandards(String completionStandards) {
        this.completionStandards = completionStandards;
    }

    /**
     * Retrieves the value for {@link #activities}.
     *
     * @return the current value
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * Provides a value for {@link #activities}.
     *
     * @param activities the new value to set
     */
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

}
