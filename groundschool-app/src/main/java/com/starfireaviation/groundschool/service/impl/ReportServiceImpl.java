/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.sql.UserQuestionEntity;
import com.starfireaviation.groundschool.repository.UserQuestionRepository;
import com.starfireaviation.groundschool.service.ReportService;

/**
 * ReportServiceImpl
 *
 * @author brianmichael
 */
@Service
public class ReportServiceImpl implements ReportService {

    /**
     * UserQuestionRepository
     */
    @Autowired
    private UserQuestionRepository userQuestionRepository;

    /**
     * Initializes an instance of <code>ReportServiceImpl</code> with the default data.
     */
    public ReportServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>ReportServiceImpl</code> with the default data.
     *
     * @param userQuestionRepository UserQuestionRepository
     */
    public ReportServiceImpl(UserQuestionRepository userQuestionRepository) {
        this.userQuestionRepository = userQuestionRepository;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Map<String, String>> getAllAnswersForUser(Long userId) {
        final List<Map<String, String>> response = new ArrayList<>();
        final List<UserQuestionEntity> userQuestionEntities = userQuestionRepository.findByUserId(userId);
        buildResponse(response, userQuestionEntities);
        return response;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Map<String, String>> getAllAnswersForEvent(Long eventId) {
        final List<Map<String, String>> response = new ArrayList<>();
        final List<UserQuestionEntity> userQuestionEntities = userQuestionRepository.findByEventId(eventId);
        buildResponse(response, userQuestionEntities);
        return response;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Map<String, String>> getAllAnswersForQuiz(Long quizId) {
        final List<Map<String, String>> response = new ArrayList<>();
        final List<UserQuestionEntity> userQuestionEntities = userQuestionRepository.findByQuizId(quizId);
        buildResponse(response, userQuestionEntities);
        return response;
    }

    /**
     * Builds response list
     *
     * @param response list of map data
     * @param userQuestionEntities database data
     */
    private static void buildResponse(
            final List<Map<String, String>> response,
            final List<UserQuestionEntity> userQuestionEntities) {
        if (!CollectionUtils.isEmpty(userQuestionEntities)) {
            for (final UserQuestionEntity userQuestionEntity : userQuestionEntities) {
                Map<String, String> map = new HashMap<>();
                map.put(UserQuestionEntity.ANSWER_GIVEN, userQuestionEntity.getAnswerGiven());
                map.put(
                        UserQuestionEntity.ANSWERED_CORRECTLY,
                        Boolean.toString(userQuestionEntity.isAnsweredCorrectly()));
                map.put(UserQuestionEntity.EVENT_ID, Long.toString(userQuestionEntity.getEventId()));
                map.put(UserQuestionEntity.QUESTION_ID, Long.toString(userQuestionEntity.getQuestionId()));
                map.put(UserQuestionEntity.QUIZ_ID, Long.toString(userQuestionEntity.getQuizId()));
                //map.put(UserQuestionEntity.TIME, userQuestionEntity.getTime());
                map.put(UserQuestionEntity.USER_ID, Long.toString(userQuestionEntity.getUserId()));
                response.add(map);
            }
        }
    }

}
