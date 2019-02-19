/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.service.ReportService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * ReportController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping({
        "/reports"
})
public class ReportController {

    /**
     * ReportService
     */
    @Autowired
    private ReportService reportService;

    /**
     * Initializes an instance of <code>ReportController</code> with the default data.
     */
    public ReportController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>ReportController</code> with the default data.
     *
     * @param reportService ReportService
     */
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Generates a pie chart image of a quiz's completion
     *
     * @param quizId Quiz ID
     * @return chart image
     * @throws IOException when chart image is not producible
     * @throws ResourceNotFoundException when quiz is not found
     */
    @GetMapping(
            path = "/quizzes/{quizId}/completion",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getQuizCompletionChart(
            @PathVariable("quizId") long quizId) throws IOException, ResourceNotFoundException {
        final JFreeChart chart = reportService.getQuizCompletionChart(quizId);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(out, chart, 640, 480);
        return out.toByteArray();
    }

    /**
     * Generates a pie chart image of a quiz user's results
     *
     * @param quizId Quiz ID
     * @param userId User ID
     * @return chart image
     * @throws IOException when chart image is not producible
     * @throws ResourceNotFoundException when quiz or user is not found
     */
    @GetMapping(
            path = "/quizzes/{quizId}/user/{userId}/results",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getQuizUserResultsChart(
            @PathVariable("quizId") long quizId,
            @PathVariable("userId") long userId) throws IOException, ResourceNotFoundException {
        final JFreeChart chart = reportService.getQuizUserResultsChart(quizId, userId);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(out, chart, 640, 480);
        return out.toByteArray();
    }

    /**
     * Generates a chart image of a quiz's results
     *
     * @param quizId Quiz ID
     * @return chart image
     * @throws IOException when chart image is not producible
     * @throws ResourceNotFoundException when quiz is not found
     */
    @GetMapping(
            path = "/quizzes/{quizId}/results",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getQuizResultsChart(
            @PathVariable("quizId") long quizId) throws IOException, ResourceNotFoundException {
        final JFreeChart chart = reportService.getQuizResultsChart(quizId);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(out, chart, 640, 480);
        return out.toByteArray();
    }
}
