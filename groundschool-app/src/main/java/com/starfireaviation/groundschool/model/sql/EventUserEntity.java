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
 * EventUserEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "EVENT_USER")
public class EventUserEntity extends BaseEntity {

    /**
     * Event ID
     */
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    /**
     * User ID
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * Confirmed
     */
    @Column(name = "confirmed", nullable = true)
    private Boolean confirmed;

    /**
     * Declined
     */
    @Column(name = "declined", nullable = true)
    private Boolean declined;

    /**
     * LocalDateTime - time
     */
    @Column(name = "time")
    private LocalDateTime time;

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
     * Retrieves the value for {@link #confirmed}.
     *
     * @return the current value
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * Provides a value for {@link #confirmed}.
     *
     * @param confirmed the new value to set
     */
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * Retrieves the value for {@link #declined}.
     *
     * @return the current value
     */
    public boolean isDeclined() {
        return declined;
    }

    /**
     * Provides a value for {@link #declined}.
     *
     * @param declined the new value to set
     */
    public void setDeclined(boolean declined) {
        this.declined = declined;
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

}
