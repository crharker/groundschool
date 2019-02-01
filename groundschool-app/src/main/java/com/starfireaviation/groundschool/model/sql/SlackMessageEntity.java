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

import com.starfireaviation.groundschool.model.NotificationEventType;

/**
 * SlackMessageEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "SLACK_MESSAGE")
public class SlackMessageEntity extends BaseEntity {

    /**
     * Message
     */
    @Column(name = "message", length = 4000)
    private String message;

    /**
     * Time message was sent
     */
    @Column(name = "time")
    private Date time;

    /**
     * Response received?
     */
    @Column(name = "response_received")
    private boolean responseReceived;

    /**
     * Message open?
     */
    @Column(name = "open")
    private boolean open;

    /**
     * To
     */
    @Column(name = "destination")
    private String to;

    /**
     * UserID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * EventID
     */
    @Column(name = "event_id")
    private Long eventId;

    /**
     * QuizID
     */
    @Column(name = "quiz_id")
    private Long quizId;

    /**
     * QuestionID
     */
    @Column(name = "question_id")
    private Long questionId;

    /**
     * NotificationEventType
     */
    @Column(name = "notification_event_type")
    @Enumerated(EnumType.STRING)
    private NotificationEventType notificationEventType;

    /**
     * Initializes an instance of <code>SlackMessageEntity</code> with the default data.
     */
    public SlackMessageEntity() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>SlackMessageEntity</code> with the default data.
     *
     * @param userId user ID
     * @param eventId event ID
     * @param quizId event ID
     * @param questionId question ID
     * @param to phone number
     * @param time message was sent
     * @param message text
     * @param type NotificationEventType
     */
    public SlackMessageEntity(
            Long userId,
            Long eventId,
            Long quizId,
            Long questionId,
            String to,
            Date time,
            String message,
            NotificationEventType type) {
        this.userId = userId;
        this.eventId = eventId;
        this.quizId = quizId;
        this.questionId = questionId;
        this.to = to;
        this.time = time;
        this.message = message;
        notificationEventType = type;
        open = true;
    }

    /**
     * Retrieves the value for {@link #message}.
     *
     * @return the current value
     */
    public String getMessage() {
        return message;
    }

    /**
     * Provides a value for {@link #message}.
     *
     * @param message the new value to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the value for {@link #time}.
     *
     * @return the current value
     */
    public Date getTime() {
        return time;
    }

    /**
     * Provides a value for {@link #time}.
     *
     * @param time the new value to set
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Retrieves the value for {@link #responseReceived}.
     *
     * @return the current value
     */
    public boolean isResponseReceived() {
        return responseReceived;
    }

    /**
     * Provides a value for {@link #responseReceived}.
     *
     * @param responseReceived the new value to set
     */
    public void setResponseReceived(boolean responseReceived) {
        this.responseReceived = responseReceived;
    }

    /**
     * Retrieves the value for {@link #to}.
     *
     * @return the current value
     */
    public String getTo() {
        return to;
    }

    /**
     * Provides a value for {@link #to}.
     *
     * @param to the new value to set
     */
    public void setTo(String to) {
        this.to = to;
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
     * Retrieves the value for {@link #open}.
     *
     * @return the current value
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Provides a value for {@link #open}.
     *
     * @param open the new value to set
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Retrieves the value for {@link #notificationEventType}.
     *
     * @return the current value
     */
    public NotificationEventType getNotificationEventType() {
        return notificationEventType;
    }

    /**
     * Provides a value for {@link #notificationEventType}.
     *
     * @param notificationEventType the new value to set
     */
    public void setNotificationEventType(NotificationEventType notificationEventType) {
        this.notificationEventType = notificationEventType;
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
}
