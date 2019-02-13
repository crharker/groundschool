/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import org.jfree.chart.JFreeChart;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;

/**
 * ReportService
 *
 * @author brianmichael
 */
public interface ReportService {

    /**
     * Generates a quiz completion pie chart
     *
     * @param quizId Quiz ID
     * @return JFreeChart
     * @throws ResourceNotFoundException when quiz is not found
     */
    JFreeChart getQuizCompletionChart(Long quizId) throws ResourceNotFoundException;

    /**
     * Generates a quiz results chart
     *
     * @param quizId Quiz ID
     * @return JFreeChart
     * @throws ResourceNotFoundException when quiz is not found
     */
    JFreeChart getQuizResultsChart(Long quizId) throws ResourceNotFoundException;
}
