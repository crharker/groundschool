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
 * UserQuestionEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "USER_QUESTION")
public class UserQuestionEntity extends BaseEntity {

    /**
     * QUESTION_ID
     */
    public static final String QUESTION_ID = "QUESTION_ID";

    /**
     * QUIZ_ID
     */
    public static final String QUIZ_ID = "QUIZ_ID";

    /**
     * EVENT_ID
     */
    public static final String EVENT_ID = "EVENT_ID";

    /**
     * USER_ID
     */
    public static final String USER_ID = "USER_ID";

    /**
     * TIME
     */
    public static final String TIME = "TIME";

    /**
     * ANSWER_GIVEN
     */
    public static final String ANSWER_GIVEN = "ANSWER_GIVEN";

    /**
     * ANSWERED_CORRECTLY
     */
    public static final String ANSWERED_CORRECTLY = "ANSWERED_CORRECTLY";

    /**
     * Question ID
     */
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    /**
     * Quiz ID
     */
    @Column(name = "quiz_id", nullable = true)
    private Long quizId;

    /**
     * Event ID
     */
    @Column(name = "event_id", nullable = true)
    private Long eventId;

    /**
     * User ID
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * LocalDateTime - time
     */
    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    /**
     * Answer given by user
     */
    @Column(name = "answer_given", nullable = false)
    private String answerGiven;

    /**
     * Answer given was correct
     */
    @Column(name = "answered_correctly", nullable = false)
    private boolean answeredCorrectly;

    /**
     * Retrieves the value for {@link #questionId}.
     *
     * @return the current value
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * Provides a value for {@link #questionId}.
     *
     * @param questionId the new value to set
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * Retrieves the value for {@link #quizId}.
     *
     * @return the current value
     */
    public Long getQuizId() {
        return quizId;
    }

    /**
     * Provides a value for {@link #quizId}.
     *
     * @param quizId the new value to set
     */
    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    /**
     * Retrieves the value for {@link #eventId}.
     *
     * @return the current value
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * Provides a value for {@link #eventId}.
     *
     * @param eventId the new value to set
     */
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    /**
     * Retrieves the value for {@link #userId}.
     *
     * @return the current value
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Provides a value for {@link #userId}.
     *
     * @param userId the new value to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the value for {@link #time}.
     *
     * @return the current value
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Provides a value for {@link #time}.
     *
     * @param time the new value to set
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Retrieves the value for {@link #answerGiven}.
     *
     * @return the current value
     */
    public String getAnswerGiven() {
        return answerGiven;
    }

    /**
     * Provides a value for {@link #answerGiven}.
     *
     * @param answerGiven the new value to set
     */
    public void setAnswerGiven(String answerGiven) {
        this.answerGiven = answerGiven;
    }

    /**
     * Retrieves the value for {@link #answeredCorrectly}.
     *
     * @return the current value
     */
    public boolean isAnsweredCorrectly() {
        return answeredCorrectly;
    }

    /**
     * Provides a value for {@link #answeredCorrectly}.
     *
     * @param answeredCorrectly the new value to set
     */
    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }
}
