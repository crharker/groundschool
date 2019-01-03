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
 * QuestionEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "QUESTION")
public class QuestionEntity extends BaseEntity {

    /**
     * Text
     */
    @Column(name = "text", length = 2000)
    private String text;

    /**
     * Unit
     */
    @Column(name = "unit", length = 100)
    private String unit;

    /**
     * SubUnit
     */
    @Column(name = "sub_unit", length = 100)
    private String subUnit;

    /**
     * Discussion
     */
    @Column(name = "discussion", length = 2000)
    private String discussion;

    /**
     * LearningStatementCode
     */
    @Column(name = "learning_statement_code", length = 10)
    private String learningStatementCode;

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
     * Retrieves the value for {@link #unit}.
     *
     * @return the current value
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Provides a value for {@link #unit}.
     *
     * @param unit the new value to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Retrieves the value for {@link #subUnit}.
     *
     * @return the current value
     */
    public String getSubUnit() {
        return subUnit;
    }

    /**
     * Provides a value for {@link #subUnit}.
     *
     * @param subUnit the new value to set
     */
    public void setSubUnit(String subUnit) {
        this.subUnit = subUnit;
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
     * Retrieves the value for {@link #learningStatementCode}.
     *
     * @return the current value
     */
    public String getLearningStatementCode() {
        return learningStatementCode;
    }

    /**
     * Provides a value for {@link #learningStatementCode}.
     *
     * @param learningStatementCode the new value to set
     */
    public void setLearningStatementCode(String learningStatementCode) {
        this.learningStatementCode = learningStatementCode;
    }

}
