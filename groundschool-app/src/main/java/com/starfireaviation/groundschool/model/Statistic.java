/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.util.Date;

/**
 * Statistic
 *
 * @author brianmichael
 */
public class Statistic extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * User ID
     */
    private Long userId;

    /**
     * Event ID
     */
    private Long eventId;

    /**
     * Question ID
     */
    private Long questionId;

    /**
     * Quiz ID
     */
    private Long quizId;

    /**
     * Statistic Type (Question answerd, quiz started, etc)
     */
    private StatisticType statisticType;

    /**
     * Date
     */
    private Date date = new Date();

    /**
     * Description
     */
    private String description;

    /**
     * Initializes an instance of <code>Statistic</code> with the default data.
     *
     * @param userId User ID
     * @param eventId Event ID
     * @param quizId Quiz ID
     * @param questionId Question ID
     * @param statisticType StatisticType
     * @param description description of statistic
     */
    public Statistic(
            Long userId,
            Long eventId,
            Long quizId,
            Long questionId,
            StatisticType statisticType,
            String description) {
        this.userId = userId;
        this.eventId = eventId;
        this.quizId = quizId;
        this.questionId = questionId;
        this.statisticType = statisticType;
        this.description = description;
    }

    /**
     * Initializes an instance of <code>Statistic</code> with the default data.
     */
    public Statistic() {
        // Default constructor
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
     * Retrieves the value for {@link #statisticType}.
     *
     * @return the current value
     */
    public StatisticType getStatisticType() {
        return statisticType;
    }

    /**
     * Provides a value for {@link #statisticType}.
     *
     * @param statisticType the new value to set
     */
    public void setStatisticType(StatisticType statisticType) {
        this.statisticType = statisticType;
    }

    /**
     * Retrieves the value for {@link #date}.
     *
     * @return the current value
     */
    public Date getDate() {
        return date;
    }

    /**
     * Provides a value for {@link #date}.
     *
     * @param date the new value to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Retrieves the value for {@link #description}.
     *
     * @return the current value
     */
    public String getDescription() {
        return description;
    }

    /**
     * Provides a value for {@link #description}.
     *
     * @param description the new value to set
     */
    public void setDescription(String description) {
        this.description = description;
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

}
