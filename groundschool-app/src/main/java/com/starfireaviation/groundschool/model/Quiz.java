/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Quiz
 *
 * @author brianmichael
 */
public class Quiz extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Title
     */
    private String title;

    /**
     * Quiz started?
     */
    private boolean started = false;

    /**
     * LocalDateTime - startTime
     */
    private LocalDateTime startTime;

    /**
     * Quiz completed?
     */
    private boolean completed = false;

    /**
     * LocalDateTime - completedTime
     */
    private LocalDateTime completedTime;

    /**
     * Questions
     */
    private List<Question> questions;

    /**
     * LessonPlan ID
     */
    private Long lessonPlanId;

    /**
     * QuizType
     */
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
     * Retrieves the value for {@link #questions}.
     *
     * @return the current value
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Provides a value for {@link #questions}.
     *
     * @param questions the new value to set
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
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
     * Computes duration of quiz summing question durations
     *
     * @return duration in seconds
     */
    public Integer getDuration() {
        Integer duration = 0;
        if (questions != null) {
            for (Question question : questions) {
                duration += question.getDuration();
            }
        }
        return duration;
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
