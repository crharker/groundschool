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
     * @param statisticType StatisticType
     * @param description description of statistic
     */
    public Statistic(StatisticType statisticType, String description) {
        // TODO this.userId = ContextHolder.getLoggedInUserId();
        this.statisticType = statisticType;
        this.description = description;
    }

    /**
     * Initializes an instance of <code>Statistic</code> with the default data.
     *
     * @param userId for the user
     * @param statisticType StatisticType
     * @param description description of statistic
     */
    public Statistic(Long userId, StatisticType statisticType, String description) {
        this.userId = userId;
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

}
