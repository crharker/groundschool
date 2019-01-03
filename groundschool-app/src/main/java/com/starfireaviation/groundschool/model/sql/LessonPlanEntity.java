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
 * UserEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "LESSON_PLAN")
public class LessonPlanEntity extends BaseEntity {

    /**
     * Title
     */
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    /**
     * Attention
     */
    @Column(name = "attention", length = 2000)
    private String attention;

    /**
     * AttentionTime (in minutes)
     */
    @Column(name = "attention_time", nullable = true)
    private Integer attentionTime;

    /**
     * Motivation
     */
    @Column(name = "motivation", length = 2000)
    private String motivation;

    /**
     * MotivationTime (in minutes)
     */
    @Column(name = "motivation_time", nullable = true)
    private Integer motivationTime;

    /**
     * Overview
     */
    @Column(name = "overview", length = 2000)
    private String overview;

    /**
     * OverviewTime (in minutes)
     */
    @Column(name = "overview_time", nullable = true)
    private Integer overviewTime;

    /**
     * Explanation Demonstration
     */
    @Column(name = "explanation_demonstration", length = 2000)
    private String explanationDemonstration;

    /**
     * ExplanationDemonstrationTime (in minutes)
     */
    @Column(name = "explanation_demonstration_time", nullable = true)
    private Integer explanationDemonstrationTime;

    /**
     * Performance Supervision
     */
    @Column(name = "performance_supervision", length = 2000)
    private String performanceSupervision;

    /**
     * PerformanceSupervisionTime (in minutes)
     */
    @Column(name = "performance_supervision_time", nullable = true)
    private Integer performanceSupervisionTime;

    /**
     * Evaluation
     */
    @Column(name = "evaluation", length = 2000)
    private String evaluation;

    /**
     * EvaluationTime (in minutes)
     */
    @Column(name = "evaluation_time", nullable = true)
    private Integer evaluationTime;

    /**
     * Summary
     */
    @Column(name = "summary", length = 2000)
    private String summary;

    /**
     * SummaryTime (in minutes)
     */
    @Column(name = "summary_time", nullable = true)
    private Integer summaryTime;

    /**
     * Remotivation
     */
    @Column(name = "remotivation", length = 2000)
    private String remotivation;

    /**
     * RemotivationTime (in minutes)
     */
    @Column(name = "remotivation_time", nullable = true)
    private Integer remotivationTime;

    /**
     * Closure
     */
    @Column(name = "closure", length = 2000)
    private String closure;

    /**
     * ClosureTime (in minutes)
     */
    @Column(name = "closure_time", nullable = true)
    private Integer closureTime;

    /**
     * Retrieves the value for {@link #title}.
     *
     * @return the current value
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides a value for {@link #title}.
     *
     * @param title the new value to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the value for {@link #attention}.
     *
     * @return the current value
     */
    public String getAttention() {
        return attention;
    }

    /**
     * Provides a value for {@link #attention}.
     *
     * @param attention the new value to set
     */
    public void setAttention(String attention) {
        this.attention = attention;
    }

    /**
     * Retrieves the value for {@link #attentionTime}.
     *
     * @return the current value
     */
    public Integer getAttentionTime() {
        return attentionTime;
    }

    /**
     * Provides a value for {@link #attentionTime}.
     *
     * @param attentionTime the new value to set
     */
    public void setAttentionTime(Integer attentionTime) {
        this.attentionTime = attentionTime;
    }

    /**
     * Retrieves the value for {@link #motivation}.
     *
     * @return the current value
     */
    public String getMotivation() {
        return motivation;
    }

    /**
     * Provides a value for {@link #motivation}.
     *
     * @param motivation the new value to set
     */
    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    /**
     * Retrieves the value for {@link #motivationTime}.
     *
     * @return the current value
     */
    public Integer getMotivationTime() {
        return motivationTime;
    }

    /**
     * Provides a value for {@link #motivationTime}.
     *
     * @param motivationTime the new value to set
     */
    public void setMotivationTime(Integer motivationTime) {
        this.motivationTime = motivationTime;
    }

    /**
     * Retrieves the value for {@link #overview}.
     *
     * @return the current value
     */
    public String getOverview() {
        return overview;
    }

    /**
     * Provides a value for {@link #overview}.
     *
     * @param overview the new value to set
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * Retrieves the value for {@link #overviewTime}.
     *
     * @return the current value
     */
    public Integer getOverviewTime() {
        return overviewTime;
    }

    /**
     * Provides a value for {@link #overviewTime}.
     *
     * @param overviewTime the new value to set
     */
    public void setOverviewTime(Integer overviewTime) {
        this.overviewTime = overviewTime;
    }

    /**
     * Retrieves the value for {@link #explanationDemonstration}.
     *
     * @return the current value
     */
    public String getExplanationDemonstration() {
        return explanationDemonstration;
    }

    /**
     * Provides a value for {@link #explanationDemonstration}.
     *
     * @param explanationDemonstration the new value to set
     */
    public void setExplanationDemonstration(String explanationDemonstration) {
        this.explanationDemonstration = explanationDemonstration;
    }

    /**
     * Retrieves the value for {@link #explanationDemonstrationTime}.
     *
     * @return the current value
     */
    public Integer getExplanationDemonstrationTime() {
        return explanationDemonstrationTime;
    }

    /**
     * Provides a value for {@link #explanationDemonstrationTime}.
     *
     * @param explanationDemonstrationTime the new value to set
     */
    public void setExplanationDemonstrationTime(Integer explanationDemonstrationTime) {
        this.explanationDemonstrationTime = explanationDemonstrationTime;
    }

    /**
     * Retrieves the value for {@link #performanceSupervision}.
     *
     * @return the current value
     */
    public String getPerformanceSupervision() {
        return performanceSupervision;
    }

    /**
     * Provides a value for {@link #performanceSupervision}.
     *
     * @param performanceSupervision the new value to set
     */
    public void setPerformanceSupervision(String performanceSupervision) {
        this.performanceSupervision = performanceSupervision;
    }

    /**
     * Retrieves the value for {@link #performanceSupervisionTime}.
     *
     * @return the current value
     */
    public Integer getPerformanceSupervisionTime() {
        return performanceSupervisionTime;
    }

    /**
     * Provides a value for {@link #performanceSupervisionTime}.
     *
     * @param performanceSupervisionTime the new value to set
     */
    public void setPerformanceSupervisionTime(Integer performanceSupervisionTime) {
        this.performanceSupervisionTime = performanceSupervisionTime;
    }

    /**
     * Retrieves the value for {@link #evaluation}.
     *
     * @return the current value
     */
    public String getEvaluation() {
        return evaluation;
    }

    /**
     * Provides a value for {@link #evaluation}.
     *
     * @param evaluation the new value to set
     */
    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    /**
     * Retrieves the value for {@link #evaluationTime}.
     *
     * @return the current value
     */
    public Integer getEvaluationTime() {
        return evaluationTime;
    }

    /**
     * Provides a value for {@link #evaluationTime}.
     *
     * @param evaluationTime the new value to set
     */
    public void setEvaluationTime(Integer evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    /**
     * Retrieves the value for {@link #summary}.
     *
     * @return the current value
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Provides a value for {@link #summary}.
     *
     * @param summary the new value to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Retrieves the value for {@link #summaryTime}.
     *
     * @return the current value
     */
    public Integer getSummaryTime() {
        return summaryTime;
    }

    /**
     * Provides a value for {@link #summaryTime}.
     *
     * @param summaryTime the new value to set
     */
    public void setSummaryTime(Integer summaryTime) {
        this.summaryTime = summaryTime;
    }

    /**
     * Retrieves the value for {@link #remotivation}.
     *
     * @return the current value
     */
    public String getRemotivation() {
        return remotivation;
    }

    /**
     * Provides a value for {@link #remotivation}.
     *
     * @param remotivation the new value to set
     */
    public void setRemotivation(String remotivation) {
        this.remotivation = remotivation;
    }

    /**
     * Retrieves the value for {@link #remotivationTime}.
     *
     * @return the current value
     */
    public Integer getRemotivationTime() {
        return remotivationTime;
    }

    /**
     * Provides a value for {@link #remotivationTime}.
     *
     * @param remotivationTime the new value to set
     */
    public void setRemotivationTime(Integer remotivationTime) {
        this.remotivationTime = remotivationTime;
    }

    /**
     * Retrieves the value for {@link #closure}.
     *
     * @return the current value
     */
    public String getClosure() {
        return closure;
    }

    /**
     * Provides a value for {@link #closure}.
     *
     * @param closure the new value to set
     */
    public void setClosure(String closure) {
        this.closure = closure;
    }

    /**
     * Retrieves the value for {@link #closureTime}.
     *
     * @return the current value
     */
    public Integer getClosureTime() {
        return closureTime;
    }

    /**
     * Provides a value for {@link #closureTime}.
     *
     * @param closureTime the new value to set
     */
    public void setClosureTime(Integer closureTime) {
        this.closureTime = closureTime;
    }

}
