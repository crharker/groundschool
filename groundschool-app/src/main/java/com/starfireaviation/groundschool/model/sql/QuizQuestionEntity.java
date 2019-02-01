/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * QuizQuestionEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "QUIZ_QUESTION")
public class QuizQuestionEntity extends BaseEntity {

    /**
     * Question ID
     */
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    /**
     * Quiz ID
     */
    @Column(name = "quiz_id", nullable = false)
    private Long quizId;

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
