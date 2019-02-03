/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Activity;
import com.starfireaviation.groundschool.model.ActivityType;
import com.starfireaviation.groundschool.model.Answer;
import com.starfireaviation.groundschool.model.Event;
import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.sql.QuestionEntity;
import com.starfireaviation.groundschool.model.sql.QuestionReferenceMaterialEntity;
import com.starfireaviation.groundschool.repository.QuestionReferenceMaterialRepository;
import com.starfireaviation.groundschool.repository.QuestionRepository;
import com.starfireaviation.groundschool.service.AnswerService;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.LessonPlanService;
import com.starfireaviation.groundschool.service.QuestionService;
import com.starfireaviation.groundschool.service.QuizService;
import com.starfireaviation.groundschool.service.ReferenceMaterialService;
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
     * QuestionReferenceMaterialRepository
     */
    @Autowired
    private QuestionReferenceMaterialRepository questionReferenceMaterialRepository;

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
     * ReferenceMaterialService
     */
    @Autowired
    private ReferenceMaterialService referenceMaterialService;

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
    public Question store(Question question) {
        if (question == null) {
            return question;
        }
        Question response = mapper.map(
                questionRepository.save(mapper.map(question, QuestionEntity.class)),
                Question.class);
        final List<Answer> answers = question.getAnswers();
        final List<Answer> responseAnswers = new ArrayList<>();
        if (!CollectionUtils.isEmpty(answers)) {
            for (final Answer answer : answers) {
                responseAnswers.add(answerService.store(response.getId(), answer));
            }
        }
        response.setAnswers(responseAnswers);
        final List<ReferenceMaterial> referenceMaterials = question.getReferenceMaterials();
        final List<ReferenceMaterial> responseReferenceMaterials = new ArrayList<>();
        if (!CollectionUtils.isEmpty(referenceMaterials)) {
            for (final ReferenceMaterial referenceMaterial : referenceMaterials) {
                ReferenceMaterial responseReferenceMaterial = referenceMaterialService.store(referenceMaterial);
                assignReferenceMaterial(response.getId(), responseReferenceMaterial.getId());
                responseReferenceMaterials.add(responseReferenceMaterial);
            }
            // TODO unassign reference material not found in list
        }
        response.setReferenceMaterials(responseReferenceMaterials);
        return response;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Question delete(long id) {
        final Question question = mapper.map(findById(id, true), Question.class);
        if (question != null) {
            for (Answer answer : question.getAnswers()) {
                try {
                    answerService.delete(answer.getId());
                } catch (ResourceNotFoundException e) {
                    // Do nothing
                }
            }
            for (QuestionReferenceMaterialEntity questionReferenceMaterial : questionReferenceMaterialRepository
                    .findByQuestionId(id)) {
                questionReferenceMaterialRepository.delete(questionReferenceMaterial);
            }
            questionRepository.delete(mapper.map(question, QuestionEntity.class));
        }
        return question;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Question> findAllQuestions() {
        final List<Question> questions = new ArrayList<>();
        final List<QuestionEntity> questionEntities = questionRepository.findAll();
        for (QuestionEntity questionEntity : questionEntities) {
            questions.add(mapper.map(questionEntity, Question.class));
        }
        return questions;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Question findById(final long id, final boolean partial) {
        Question response = mapper.map(questionRepository.findById(id), Question.class);
        if (!partial) {
            response.setAnswers(answerService.findByQuestionId(id));
            response.setReferenceMaterials(referenceMaterialService.findByQuestionId(id));
        }
        return response;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean answer(long questionId, long userId, String selection, Date startTime)
            throws ResourceNotFoundException {
        final Instant start = startTime == null ? Instant.now() : startTime.toInstant();
        boolean answeredCorrectly = false;
        final Question question = findById(questionId, false);
        if (question == null) {
            return answeredCorrectly;
        }
        final List<Answer> answers = answerService.findByQuestionId(questionId);
        question.setAnswers(answers);
        for (Answer answer : answers) {
            if (answer.getChoice().equalsIgnoreCase(selection)) {
                answeredCorrectly = answer.isCorrect();
            }
        }
        final Long eventId = eventService.isCheckedIn(userId);
        Long quizId = null;
        if (eventId != null) {
            final Event event = eventService.findById(eventId, true);
            final LessonPlan lessonPlan = lessonPlanService.findById(event.getLessonPlanId(), false);
            final List<Activity> activities = lessonPlan.getActivities();
            if (activities != null) {
                for (final Activity activity : activities) {
                    if (activity.getActivityType() == ActivityType.QUIZ) {
                        final Quiz quiz = quizService.findById(activity.getReferenceId(), true);
                        if (quiz.isStarted() && !quiz.isCompleted()) {
                            quizId = quiz.getId();
                            break;
                        }
                    }
                }
            }
        }
        statisticService.store(
                new Statistic(
                        userId,
                        eventId,
                        quizId,
                        questionId,
                        StatisticType.QUESTION_ANSWERED,
                        String.format(
                                "Duration [%s]; Answer Given [%s]; Answered Correctly [%s]",
                                Duration.between(start, Instant.now()),
                                selection,
                                answeredCorrectly)));

        return answeredCorrectly;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean assignReferenceMaterial(long questionId, long referenceMaterialId) {
        boolean success = false;
        Question question = findById(questionId, false);
        ReferenceMaterial referenceMaterial = referenceMaterialService.findReferenceMaterialById(referenceMaterialId);
        if (question != null
                && referenceMaterial != null
                && questionReferenceMaterialRepository.findByQuestionAndReferenceMaterialId(
                        questionId,
                        referenceMaterialId) == null) {
            QuestionReferenceMaterialEntity questionReferenceMaterialEntity = new QuestionReferenceMaterialEntity();
            questionReferenceMaterialEntity.setQuestionId(questionId);
            questionReferenceMaterialEntity.setReferenceMaterialId(referenceMaterialId);
            questionReferenceMaterialRepository.save(questionReferenceMaterialEntity);
            success = true;
        }
        return success;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean unassignReferenceMaterial(long questionId, long referenceMaterialId) {
        boolean success = false;
        QuestionReferenceMaterialEntity questionReferenceMaterialEntity = questionReferenceMaterialRepository
                .findByQuestionAndReferenceMaterialId(
                        questionId,
                        referenceMaterialId);
        if (questionReferenceMaterialEntity != null) {
            questionReferenceMaterialRepository.delete(questionReferenceMaterialEntity);
            success = true;
        }
        return success;
    }

}
