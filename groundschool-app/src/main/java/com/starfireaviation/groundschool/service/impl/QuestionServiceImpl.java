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
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hazelcast.core.HazelcastInstance;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Answer;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.sql.QuestionEntity;
import com.starfireaviation.groundschool.model.sql.QuestionReferenceMaterialEntity;
import com.starfireaviation.groundschool.repository.QuestionReferenceMaterialRepository;
import com.starfireaviation.groundschool.repository.QuestionRepository;
import com.starfireaviation.groundschool.service.AnswerService;
import com.starfireaviation.groundschool.service.EventService;
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
     * HazelcastInstance
     */
    @Autowired
    private HazelcastInstance hazelcastInstance;

    /**
     * AddressCache
     */
    private Map<Long, Question> questionCache;

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
     * @param hazelcastInstance HazelcastInstance
     */
    public QuestionServiceImpl(
            QuestionRepository questionRepository,
            MapperFacade mapperFacade,
            HazelcastInstance hazelcastInstance) {
        this.questionRepository = questionRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Question store(Question question) throws ResourceNotFoundException {
        if (question == null) {
            return question;
        }
        if (question.getId() != null) {
            initCache();
            questionCache.remove(question.getId());
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
    public Question delete(long id) throws ResourceNotFoundException {
        final Question question = mapper.map(get(id), Question.class);
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
            initCache();
            questionCache.remove(id);
            questionRepository.delete(mapper.map(question, QuestionEntity.class));
        }
        return question;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Question> getAll() throws ResourceNotFoundException {
        final List<Question> questions = new ArrayList<>();
        final List<QuestionEntity> questionEntities = questionRepository.findAll();
        for (QuestionEntity questionEntity : questionEntities) {
            questions.add(get(questionEntity.getId()));
        }
        return questions;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Question get(final long id) throws ResourceNotFoundException {
        initCache();
        if (questionCache.containsKey(id)) {
            return questionCache.get(id);
        }
        Question response = mapper.map(questionRepository.findById(id), Question.class);
        response.setAnswers(answerService.findByQuestionId(id));
        response.setReferenceMaterials(referenceMaterialService.findByQuestionId(id));
        questionCache.put(id, response);
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
        final Question question = get(questionId);
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
        final Long eventId = eventService.getCurrentEvent();
        final Long quizId = quizService.getCurrentQuiz();
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
    public boolean assignReferenceMaterial(long questionId, long referenceMaterialId) throws ResourceNotFoundException {
        boolean success = false;
        Question question = get(questionId);
        ReferenceMaterial referenceMaterial = referenceMaterialService.get(referenceMaterialId);
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

    /**
     * Initializes Hazelcast cache
     */
    private void initCache() {
        if (questionCache == null) {
            //hazelcastInstance = Hazelcast.newHazelcastInstance(new Config());
            questionCache = hazelcastInstance.getMap("questions");
        }
    }

}
