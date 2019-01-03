/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Question
 *
 * @author brianmichael
 */
public class Question extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Text
     */
    private String text;

    /**
     * Unit
     */
    private String unit;

    /**
     * SubUnit
     */
    private String subUnit;

    /**
     * Discussion
     */
    private String discussion;

    /**
     * LearningStatementCode
     */
    private String learningStatementCode;

    /**
     * Answers
     */
    private List<Answer> answers;

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

    /**
     * Retrieves the value for {@link #answers}.
     *
     * @return the current value
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Provides a value for {@link #answers}.
     *
     * @param answers the new value to set
     */
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    /**
     * Adds an answer
     *
     * @param answer Answer
     */
    public void addAnswer(Answer answer) {
        if (answers == null) {
            answers = new ArrayList<>();
        }
        answers.add(answer);
    }
}
