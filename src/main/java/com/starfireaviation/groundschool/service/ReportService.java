/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;
import java.util.Map;

/**
 * ReportService
 *
 * @author brianmichael
 */
public interface ReportService {

    /**
     * Gets all answered questions by a user
     *
     * @param userId User ID
     * @return list of all answered question details
     */
    List<Map<String, String>> getAllAnswersForUser(Long userId);

    /**
     * Gets all answered questions for an event
     *
     * @param eventId Event ID
     * @return list of all answered question details
     */
    List<Map<String, String>> getAllAnswersForEvent(Long eventId);

    /**
     * Gets all answered questions for a quiz
     *
     * @param quizId Quiz ID
     * @return list of all answered question details
     */
    List<Map<String, String>> getAllAnswersForQuiz(Long quizId);
}
