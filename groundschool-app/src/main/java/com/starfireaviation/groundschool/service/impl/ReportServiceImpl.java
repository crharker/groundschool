/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.service.QuizService;
import com.starfireaviation.groundschool.service.ReportService;
import com.starfireaviation.groundschool.service.StatisticService;

/**
 * ReportServiceImpl
 *
 * @author brianmichael
 */
@Service
public class ReportServiceImpl implements ReportService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);

    /**
     * QUESTION_ANSWERED_DESC_PATTERN
     */
    private static final Pattern QUESTION_ANSWERED_DESC_PATTERN = Pattern.compile(
            "Duration [(.+)]; Answer Given [(.+)]; Answered Correctly [(.+)]");

    /**
     * QuizService
     */
    @Autowired
    private QuizService quizService;

    /**
     * StatisticService
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * NOT_ANSWERED
     */
    private static final String NOT_ANSWERED = "Not Answered";

    /**
     * ANSWERED_CORRECTLY
     */
    private static final String ANSWERED_CORRECTLY = "Answered Correctly";

    /**
     * ANSWERED_INCORRECTLY
     */
    private static final String ANSWERED_INCORRECTLY = "Answered Incorrectly";

    /**
     * QUESTION_NUM
     */
    private static final String QUESTION_NUM = "Question #";

    /**
     * GREEN
     */
    private static final Color GREEN = new Color(0, 255, 0);

    /**
     * BLUE
     */
    private static final Color BLUE = new Color(0, 0, 255);

    /**
     * RED
     */
    private static final Color RED = new Color(255, 0, 0);

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public JFreeChart getQuizCompletionChart(Long quizId) throws ResourceNotFoundException {
        final Quiz quiz = quizService.get(quizId);
        final List<Question> questions = getQuizQuestions(quiz);
        final List<Long> quizParticipants = new ArrayList<>();
        int answeredCorrectly = 0;
        int answeredIncorrectly = 0;
        final List<Statistic> statistics = statisticService.findByQuizId(quizId, StatisticType.QUESTION_ANSWERED);
        for (final Statistic statistic : statistics) {
            boolean correct = false;
            final Matcher matcher = QUESTION_ANSWERED_DESC_PATTERN.matcher(statistic.getDescription());
            if (matcher.find()) {
                LOGGER.info("getQuizCompletionChart() matching on " + matcher.group(3));
                correct = Boolean.valueOf(matcher.group(3));
            }
            if (correct) {
                answeredCorrectly += 1;
            } else {
                answeredIncorrectly += 1;
            }
            if (!quizParticipants.contains(statistic.getUserId())) {
                quizParticipants.add(statistic.getUserId());
            }
        }
        final int notAnswered = (questions.size() * quizParticipants.size())
                - (answeredCorrectly + answeredIncorrectly);
        final DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue(NOT_ANSWERED, notAnswered);
        dataset.setValue(ANSWERED_CORRECTLY, answeredCorrectly);
        dataset.setValue(ANSWERED_INCORRECTLY, answeredIncorrectly);
        final JFreeChart chart = ChartFactory.createPieChart(
                String.format("Quiz \"%s\" Completion", quiz.getTitle()),
                dataset);
        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint(NOT_ANSWERED, BLUE);
        plot.setSectionPaint(ANSWERED_CORRECTLY, GREEN);
        plot.setSectionPaint(ANSWERED_INCORRECTLY, RED);
        return chart;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public JFreeChart getQuizResultsChart(Long quizId) throws ResourceNotFoundException {
        final Quiz quiz = quizService.get(quizId);
        final List<Question> questions = getQuizQuestions(quiz);
        final Map<Long, Integer> questionIdMap = buildQuestionIdMap(questions);
        final int questionData[][] = new int[questions.size()][3];
        final List<Long> quizParticipants = new ArrayList<>();
        final List<Statistic> statistics = statisticService.findByQuizId(quizId, StatisticType.QUESTION_ANSWERED);
        for (Statistic statistic : statistics) {
            boolean correct = false;
            final Matcher matcher = QUESTION_ANSWERED_DESC_PATTERN.matcher(statistic.getDescription());
            if (matcher.find()) {
                LOGGER.info("getQuizCompletionChart() matching on " + matcher.group(3));
                correct = Boolean.valueOf(matcher.group(3));
            }
            final int questionIndex = questionIdMap.get(statistic.getQuestionId());
            if (correct) {
                questionData[questionIndex][1] += 1;
            } else {
                questionData[questionIndex][2] += 1;
            }
            if (!quizParticipants.contains(statistic.getUserId())) {
                quizParticipants.add(statistic.getUserId());
            }
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < questionData.length; i++) {
            final int answered = questionData[i][1] + questionData[i][2];
            if (answered < quizParticipants.size()) {
                questionData[i][0] = quizParticipants.size() - answered;
            } else {
                questionData[i][0] = 0;
            }
            dataset.addValue(questionData[i][0], NOT_ANSWERED, QUESTION_NUM + (i + 1));
            dataset.addValue(questionData[i][1], ANSWERED_CORRECTLY, QUESTION_NUM + (i + 1));
            dataset.addValue(questionData[i][2], ANSWERED_INCORRECTLY, QUESTION_NUM + (i + 1));
        }
        final JFreeChart chart = ChartFactory.createBarChart(
                String.format("Quiz \"%s\" Results", quiz.getTitle()),
                "Question",
                "# of Participants Answering",
                dataset);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, BLUE);
        renderer.setSeriesPaint(1, GREEN);
        renderer.setSeriesPaint(2, RED);
        CategoryAxis domainAxis = new CategoryAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        plot.setDomainAxis(domainAxis);
        return chart;
    }

    /**
     * Verifies input data as well as retrieves quiz question list
     *
     * @param quiz Quiz
     * @return list of questions
     * @throws ResourceNotFoundException when quiz or quiz questions are not found
     */
    private static List<Question> getQuizQuestions(final Quiz quiz) throws ResourceNotFoundException {
        if (quiz == null) {
            throw new ResourceNotFoundException("Quiz not found");
        }
        final List<Question> questions = quiz.getQuestions();
        if (questions == null) {
            throw new ResourceNotFoundException("Quiz questions not found");
        }
        return questions;
    }

    /**
     * Builds a question id map
     *
     * @param questions list of questions
     * @return Map
     */
    private static Map<Long, Integer> buildQuestionIdMap(final List<Question> questions) {
        final Map<Long, Integer> questionIdMap = new HashMap<>();
        int count = 0;
        for (Question question : questions) {
            questionIdMap.put(question.getId(), count++);
        }
        return questionIdMap;
    }

}
