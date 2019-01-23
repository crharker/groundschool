/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.steps;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.starfireaviation.groundschool.controller.EventController;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.User;

/**
 * BaseSteps
 *
 * @author brianmichael
 */
public class BaseSteps {

    /**
     * EventController
     */
    @Autowired
    protected EventController eventController;

    /**
     * ID
     */
    protected Long id;

    /**
     * Exception
     */
    protected Exception exception;

    /**
     * Event
     */
    protected Event event;

    /**
     * Events
     */
    protected List<Event> events;

    /**
     * LessonPlan
     */
    protected LessonPlan lessonPlan;

    /**
     * LessonPlans
     */
    protected List<LessonPlan> lessonPlans;

    /**
     * Question
     */
    protected Question question;

    /**
     * Questions
     */
    protected List<Question> questions;

    /**
     * Quiz
     */
    protected Quiz quiz;

    /**
     * Quizzes
     */
    protected List<Quiz> quizzes;

    /**
     * ReferenceMaterial
     */
    protected ReferenceMaterial referenceMaterial;

    /**
     * ReferenceMaterials
     */
    protected List<ReferenceMaterial> referenceMaterials;

    /**
     * Statistic
     */
    protected Statistic statistic;

    /**
     * Statistics
     */
    protected List<Statistic> statistics;

    /**
     * User
     */
    protected User user;

    /**
     * Users
     */
    protected List<User> users;

}
