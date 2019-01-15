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
import org.springframework.util.CollectionUtils;

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
        final Question question = mapper.map(findQuestionById(id), Question.class);
        if (question != null) {
            for (Answer answer : question.getAnswers()) {
                answerService.delete(answer.getId());
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
            Question question = mapper.map(questionEntity, Question.class);
            question.setAnswers(answerService.findByQuestionId(question.getId()));
            final List<QuestionReferenceMaterialEntity> questionReferenceMaterialEntities =
                    questionReferenceMaterialRepository.findAll();
            for (QuestionReferenceMaterialEntity questionReferenceMaterial : questionReferenceMaterialEntities) {
                if (question.getId() == questionReferenceMaterial.getQuestionId()) {
                    question.addReferenceMaterial(
                            referenceMaterialService.findReferenceMaterialById(
                                    questionReferenceMaterial.getReferenceMaterialId()));
                }
            }
            questions.add(question);
        }
        return questions;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Question findQuestionById(long id) {
        Question response = mapper.map(questionRepository.findById(id), Question.class);
        response.setAnswers(answerService.findByQuestionId(id));
        List<ReferenceMaterial> referenceMaterials = new ArrayList<>();
        for (QuestionReferenceMaterialEntity questionReferenceMaterial : questionReferenceMaterialRepository
                .findByQuestionId(id)) {
            referenceMaterials.add(
                    referenceMaterialService.findReferenceMaterialById(
                            questionReferenceMaterial.getReferenceMaterialId()));
        }
        response.setReferenceMaterials(referenceMaterials);
        return response;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean answer(long questionId, long userId, String selection) {
        final Instant start = Instant.now();
        boolean answeredCorrectly = false;
        final Question question = findQuestionById(questionId);
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
            final Event event = eventService.findById(eventId);
            final LessonPlan lessonPlan = lessonPlanService.findById(event.getLessonPlanId());
            for (final Long id : lessonPlan.getQuizIds()) {
                final Quiz quiz = quizService.findById(id);
                if (quiz.isStarted() && !quiz.isCompleted()) {
                    quizId = quiz.getId();
                }
            }
        }
        final Statistic statistic = new Statistic(
                StatisticType.QUESTION_ANSWERED,
                String.format(
                        "Duration [%s]; Question ID [%s]; Event ID [%s]; Quiz ID [%s]; Answer Given [%s]; Answered Correctly [%s]",
                        Duration.between(start, Instant.now()),
                        questionId,
                        eventId,
                        quizId,
                        selection,
                        answeredCorrectly));
        statistic.setUserId(userId);
        statisticService.store(statistic);

        return answeredCorrectly;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public boolean assignReferenceMaterial(long questionId, long referenceMaterialId) {
        boolean success = false;
        Question question = findQuestionById(questionId);
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
