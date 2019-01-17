/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.sql.LessonPlanEntity;
import com.starfireaviation.groundschool.repository.LessonPlanRepository;
import com.starfireaviation.groundschool.service.LessonPlanService;
import com.starfireaviation.groundschool.service.QuizService;

import ma.glasnost.orika.MapperFacade;

/**
 * LessonPlanServiceImpl
 *
 * @author brianmichael
 */
@Service
public class LessonPlanServiceImpl implements LessonPlanService {

    /**
     * LessonPlanRepository
     */
    @Autowired
    private LessonPlanRepository lessonPlanRepository;

    /**
     * QuizService
     */
    @Autowired
    private QuizService quizService;

    /**
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * Initializes an instance of <code>LessonPlanServiceImpl</code> with the default data.
     */
    public LessonPlanServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>LessonPlanServiceImpl</code> with the default data.
     *
     * @param lessonPlanRepository LessonPlanRepository
     * @param mapperFacade MapperFacade
     */
    public LessonPlanServiceImpl(LessonPlanRepository lessonPlanRepository, MapperFacade mapperFacade) {
        this.lessonPlanRepository = lessonPlanRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public LessonPlan store(LessonPlan lessonPlan) {
        if (lessonPlan == null) {
            return lessonPlan;
        }
        return mapper.map(lessonPlanRepository.save(mapper.map(lessonPlan, LessonPlanEntity.class)), LessonPlan.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public LessonPlan delete(long id) {
        LessonPlan lessonPlan = mapper.map(findById(id), LessonPlan.class);
        if (lessonPlan != null) {
            lessonPlanRepository.delete(mapper.map(lessonPlan, LessonPlanEntity.class));
        }
        return lessonPlan;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<LessonPlan> findAllLessonPlans() {
        List<LessonPlan> lessonPlans = new ArrayList<>();
        List<LessonPlanEntity> lessonPlanEntities = lessonPlanRepository.findAll();
        for (LessonPlanEntity lessonPlanEntity : lessonPlanEntities) {
            lessonPlans.add(mapper.map(lessonPlanEntity, LessonPlan.class));
        }
        return lessonPlans;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public LessonPlan findById(long id) {
        final LessonPlan lessonPlan = mapper.map(lessonPlanRepository.findById(id), LessonPlan.class);
        final List<Quiz> quizzes = quizService.findQuizzesByLessonPlanId(id);
        List<Long> quizIds = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            quizIds.add(quiz.getId());
        }
        lessonPlan.setQuizIds(quizIds);
        return lessonPlan;
    }

}
