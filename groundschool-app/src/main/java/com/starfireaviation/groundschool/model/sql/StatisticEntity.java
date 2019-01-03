/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.starfireaviation.groundschool.model.StatisticType;

/**
 * StatisticEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "STATISTICS")
public class StatisticEntity extends BaseEntity {

    /**
     * User ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * Statistic Type (Question answerd, quiz started, etc)
     */
    @Column(name = "type", length = 100)
    @Enumerated(EnumType.STRING)
    private StatisticType statisticType;

    /**
     * Date
     */
    @Column(name = "entry_date")
    private Date date;

    /**
     * Description
     */
    @Column(name = "description", nullable = true, length = 255)
    private String description;

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
