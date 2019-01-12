/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.Answer;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.sql.QuestionEntity;
import com.starfireaviation.groundschool.repository.QuestionRepository;
import com.starfireaviation.groundschool.service.AnswerService;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.LessonPlanService;
import com.starfireaviation.groundschool.service.QuestionService;
import com.starfireaviation.groundschool.service.QuizService;
import com.starfireaviation.groundschool.service.StatisticService;

import ma.glasnost.orika.MapperFacade;

/**
 * QuestionServiceImpl
 *
 * @author brianmichael
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    /**
     * QuestionRepository
     */
    @Autowired
    private QuestionRepository questionRepository;

    /**
     * AnswerService
     */
    @Autowired
    private AnswerService answerService;

    /**
     * EventService
     */
    @Autowired
    private EventService eventService;

    /**
     * LessonPlanService
     */
    @Autowired
    private LessonPlanService lessonPlanService;

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
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * Initializes an instance of <code>QuestionServiceImpl</code> with the default data.
     */
    public QuestionServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>QuestionServiceImpl</code> with the default data.
     *
     * @param questionRepository QuestionRepository
     * @param mapperFacade MapperFacade
     */
    public QuestionServiceImpl(QuestionRepository questionRepository, MapperFacade mapperFacade) {
        this.questionRepository = questionRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Question store(Question user) {
        if (user == null) {
            return user;
        }
        return mapper.map(questionRepository.save(mapper.map(user, QuestionEntity.class)), Question.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Question delete(long id) {
        Question question = mapper.map(findQuestionById(id), Question.class);
        if (question != null) {
            questionRepository.delete(mapper.map(question, QuestionEntity.class));
        }
        return question;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Question> findAllQuestions() {
        List<Question> questions = new ArrayList<>();
        List<QuestionEntity> questionEntities = questionRepository.findAll();
        for (QuestionEntity questionEntity : questionEntities) {
            questions.add(mapper.map(questionEntity, Question.class));
        }
        return questions;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Question findQuestionById(long id) {
        return mapper.map(questionRepository.findById(id), Question.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Question answer(long questionId, long userId, String selection) {
        Instant start = Instant.now();
        final Question question = findQuestionById(questionId);
        if (question == null) {
            return null;
        }
        final List<Answer> answers = answerService.findByQuestionId(questionId);
        question.setAnswers(answers);
        boolean answeredCorrectly = false;
        for (Answer answer : answers) {
            if (answer.getChoice().equalsIgnoreCase(selection)) {
                answeredCorrectly = answer.isCorrect();
            }
        }
        final Long eventId = eventService.isCheckedIn(userId);
        Long quizId = null;
        if (eventId != null) {
            final Event event = eventService.findById(eventId);
            final LessonPlan lessonPlan = lessonPlanService.findById(event.getLessonPlanId());
            for (Long id : lessonPlan.getQuizIds()) {
                Quiz quiz = quizService.findById(id);
                if (quiz.isStarted() && !quiz.isCompleted()) {
                    quizId = quiz.getId();
                }
            }
        }
        statisticService.store(
                new Statistic(
                        StatisticType.QUESTION_ANSWERED,
                        String.format(
                                "Duration [%s]; Question ID [%s]; Event ID [%s]; Quiz ID [%s]; Answer Given [%s]; Answered Correctly [%s]",
                                Duration.between(start, Instant.now()),
                                questionId,
                                eventId,
                                quizId,
                                selection,
                                answeredCorrectly)));
        return question;
    }

}
