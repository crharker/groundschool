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
 * EventParticipantEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "EVENT_PARTICIPANT")
public class EventParticipantEntity extends BaseEntity {

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
     * LocalDateTime - confirmation time
     */
    @Column(name = "confirmation_time", nullable = true)
    private LocalDateTime confirmationTime;

    /**
     * Checked in
     */
    @Column(name = "checked_in", nullable = true)
    private Boolean checkedIn;

    /**
     * LocalDateTime - checkin time
     */
    @Column(name = "checkin_time", nullable = true)
    private LocalDateTime checkinTime;

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
     * Retrieves the value for {@link #confirmed}.
     *
     * @return the current value
     */
    public Boolean getConfirmed() {
        return confirmed;
    }

    /**
     * Provides a value for {@link #confirmed}.
     *
     * @param confirmed the new value to set
     */
    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * Retrieves the value for {@link #declined}.
     *
     * @return the current value
     */
    public Boolean getDeclined() {
        return declined;
    }

    /**
     * Provides a value for {@link #declined}.
     *
     * @param declined the new value to set
     */
    public void setDeclined(Boolean declined) {
        this.declined = declined;
    }

    /**
     * Retrieves the value for {@link #confirmationTime}.
     *
     * @return the current value
     */
    public LocalDateTime getConfirmationTime() {
        return confirmationTime;
    }

    /**
     * Provides a value for {@link #confirmationTime}.
     *
     * @param confirmationTime the new value to set
     */
    public void setConfirmationTime(LocalDateTime confirmationTime) {
        this.confirmationTime = confirmationTime;
    }

    /**
     * Retrieves the value for {@link #checkedIn}.
     *
     * @return the current value
     */
    public Boolean getCheckedIn() {
        return checkedIn;
    }

    /**
     * Provides a value for {@link #checkedIn}.
     *
     * @param checkedIn the new value to set
     */
    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    /**
     * Retrieves the value for {@link #checkinTime}.
     *
     * @return the current value
     */
    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    /**
     * Provides a value for {@link #checkinTime}.
     *
     * @param checkinTime the new value to set
     */
    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }

}
