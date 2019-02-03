/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * LessonPlanEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "LESSON_PLAN")
public class LessonPlanEntity extends BaseEntity {

    /**
     * Title
     */
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    /**
     * Summary
     */
    @Column(name = "summary", nullable = false, length = 2000)
    private String summary;

    /**
     * Objective
     */
    @Column(name = "objective", length = 2000)
    private String objective;

    /**
     * Content
     */
    @Column(name = "content", length = 2000)
    private String content;

    /**
     * Schedule
     */
    @Column(name = "schedule", length = 2000)
    private String schedule;

    /**
     * Equipment
     */
    @Column(name = "equipment", length = 2000)
    private String equipment;

    /**
     * Instructor's Actions
     */
    @Column(name = "instructor_actions", length = 2000)
    private String instructorActions;

    /**
     * Student's Actions
     */
    @Column(name = "student_actions", length = 2000)
    private String studentActions;

    /**
     * Completion Standards
     */
    @Column(name = "completion_standards", length = 2000)
    private String completionStandards;

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

}
