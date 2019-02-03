/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.starfireaviation.groundschool.model.QuizType;

/**
 * QuizEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "QUIZ")
public class QuizEntity extends BaseEntity {

    /**
     * Title
     */
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    /**
     * Quiz started?
     */
    @Column(name = "started", nullable = false)
    private boolean started;

    /**
     * LocalDateTime - startTime
     */
    @Column(name = "start_time", nullable = true)
    private LocalDateTime startTime;

    /**
     * Quiz completed?
     */
    @Column(name = "completed", nullable = false)
    private boolean completed;

    /**
     * LocalDateTime - completedTime
     */
    @Column(name = "completed_time", nullable = true)
    private LocalDateTime completedTime;

    /**
     * Lesson Plan ID
     */
    @Column(name = "lesson_plan_id", nullable = false)
    private Long lessonPlanId;

    /**
     * Quiz Type (Exam, Informal, Formal, etc)
     */
    @Column(name = "type", length = 100)
    @Enumerated(EnumType.STRING)
    private QuizType quizType;

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

    /**
     * Retrieves the value for {@link #quizType}.
     *
     * @return the current value
     */
    public QuizType getQuizType() {
        return quizType;
    }

    /**
     * Provides a value for {@link #quizType}.
     *
     * @param quizType the new value to set
     */
    public void setQuizType(QuizType quizType) {
        this.quizType = quizType;
    }

}
