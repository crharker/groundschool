/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.starfireaviation.groundschool.Constants;
import com.starfireaviation.groundschool.service.QuizService;

/**
 * CompleteQuestionJob
 *
 * @author brianmichael
 */
@Component
public class CompleteQuestionJob extends QuartzJobBean {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CompleteQuestionJob.class);

    /**
     * QuizService
     */
    @Autowired
    private QuizService quizService;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        final Long quizId = Long.valueOf(jobExecutionContext.getJobDetail().getJobDataMap().getString(Constants.ID));
        LOGGER.info(String.format("Completing question for quiz [%s]", quizId));
        quizService.completeQuestion(quizId);
        LOGGER.info(String.format("Starting next question for quiz [%s]", quizId));
        quizService.startQuestion(quizId, true);
    }

}
