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
     * Current question
     */
    @Column(name = "current_question", nullable = true)
    private Long currentQuestion;

    /**
     * LocalDateTime - currentQuestionStartTime
     */
    @Column(name = "current_question_start_time")
    private LocalDateTime currentQuestionStartTime;

    /**
     * Completed questions
     *
     * comma separated list of question IDs
     */
    @Column(name = "completed_questions", nullable = true, length = 2000)
    private String completedQuestions;

    /**
     * Lesson Plan ID
     */
    @Column(name = "lesson_plan_id", nullable = false)
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
     * Retrieves the value for {@link #currentQuestion}.
     *
     * @return the current value
     */
    public Long getCurrentQuestion() {
        return currentQuestion;
    }

    /**
     * Provides a value for {@link #currentQuestion}.
     *
     * @param currentQuestion the new value to set
     */
    public void setCurrentQuestion(Long currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    /**
     * Retrieves the value for {@link #currentQuestionStartTime}.
     *
     * @return the current value
     */
    public LocalDateTime getCurrentQuestionStartTime() {
        return currentQuestionStartTime;
    }

    /**
     * Provides a value for {@link #currentQuestionStartTime}.
     *
     * @param currentQuestionStartTime the new value to set
     */
    public void setCurrentQuestionStartTime(LocalDateTime currentQuestionStartTime) {
        this.currentQuestionStartTime = currentQuestionStartTime;
    }

    /**
     * Retrieves the value for {@link #completedQuestions}.
     *
     * @return the current value
     */
    public String getCompletedQuestions() {
        return completedQuestions;
    }

    /**
     * Provides a value for {@link #completedQuestions}.
     *
     * @param completedQuestions the new value to set
     */
    public void setCompletedQuestions(String completedQuestions) {
        this.completedQuestions = completedQuestions;
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
