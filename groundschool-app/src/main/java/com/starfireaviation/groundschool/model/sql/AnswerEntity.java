/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * UserEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "ANSWER")
public class AnswerEntity extends BaseEntity {

    /**
     * Text
     */
    @Column(name = "text", length = 2000)
    private String text;

    /**
     * Correct
     */
    @Column(name = "correct", nullable = false)
    private boolean correct;

    /**
     * Discussion
     */
    @Column(name = "discussion", length = 2000)
    private String discussion;

    /**
     * Question
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private QuestionEntity question;

    /**
     * Retrieves the value for {@link #text}.
     *
     * @return the current value
     */
    public String getText() {
        return text;
    }

    /**
     * Provides a value for {@link #text}.
     *
     * @param text the new value to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Retrieves the value for {@link #correct}.
     *
     * @return the current value
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * Provides a value for {@link #correct}.
     *
     * @param correct the new value to set
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    /**
     * Retrieves the value for {@link #discussion}.
     *
     * @return the current value
     */
    public String getDiscussion() {
        return discussion;
    }

    /**
     * Provides a value for {@link #discussion}.
     *
     * @param discussion the new value to set
     */
    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    /**
     * Retrieves the value for {@link #question}.
     *
     * @return the current value
     */
    public QuestionEntity getQuestion() {
        return question;
    }

    /**
     * Provides a value for {@link #question}.
     *
     * @param question the new value to set
     */
    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

}
