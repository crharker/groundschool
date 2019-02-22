/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.model.sql.QuizEntity;
import com.starfireaviation.groundschool.model.sql.QuizQuestionEntity;
import com.starfireaviation.groundschool.repository.QuizQuestionRepository;
import com.starfireaviation.groundschool.repository.QuizRepository;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.QuestionService;
import com.starfireaviation.groundschool.service.QuizService;
import com.starfireaviation.groundschool.service.StatisticService;

import ma.glasnost.orika.MapperFacade;

/**
 * UserServiceImpl
 *
 * @author brianmichael
 */
@Service
public class QuizServiceImpl implements QuizService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizServiceImpl.class);

    /**
     * QuizRepository
     */
    @Autowired
    private QuizRepository quizRepository;

    /**
     * QuizQuestionRepository
     */
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    /**
     * NotificationService
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * QuestionService
     */
    @Autowired
    private QuestionService questionService;

    /**
     * StatisticService
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * EventService
     */
    @Autowired
    private EventService eventService;

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
     * QuizCache
     */
    private Map<Long, Quiz> quizCache;

    /**
     * Initializes an instance of <code>QuizServiceImpl</code> with the default data.
     */
    public QuizServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>QuizServiceImpl</code> with the default data.
     *
     * @param quizRepository QuizRepository
     * @param mapperFacade MapperFacade
     * @param hazelcastInstance HazelcastInstance
     */
    public QuizServiceImpl(
            QuizRepository quizRepository,
            MapperFacade mapperFacade,
            HazelcastInstance hazelcastInstance) {
        this.quizRepository = quizRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz store(final Quiz quiz) {
        if (quiz == null) {
            return quiz;
        }
        if (quiz.getId() != null) {
            final List<QuizQuestionEntity> quizQuestions = quizQuestionRepository.findByQuizId(quiz.getId());
            final List<QuizQuestionEntity> keepList = new ArrayList<>();
            if (quiz.getQuestions() != null && quiz.getQuestions().size() > 0) {
                for (final Question question : quiz.getQuestions()) {
                    QuizQuestionEntity quizQuestionEntity = getQuizQuestionEntity(
                            quiz.getId(),
                            question.getId(),
                            quizQuestions);
                    if (quizQuestionEntity == null) {
                        quizQuestionEntity = new QuizQuestionEntity();
                        quizQuestionEntity.setQuestionId(question.getId());
                        quizQuestionEntity.setQuizId(quiz.getId());
                    }
                    keepList.add(quizQuestionRepository.save(quizQuestionEntity));
                }
            }
            // Remove entries that are not still in the list
            for (final QuizQuestionEntity qqe : quizQuestions) {
                boolean keep = false;
                for (final QuizQuestionEntity qqe2 : keepList) {
                    if (qqe.getId() == qqe2.getId()) {
                        keep = true;
                        break;
                    }
                }
                if (!keep) {
                    quizQuestionRepository.delete(qqe);
                }
            }
        }
        if (quiz.getId() != null) {
            initCache();
            quizCache.remove(quiz.getId());
        }
        final QuizEntity quizEntity = quizRepository.save(mapper.map(quiz, QuizEntity.class));
        return get(quizEntity.getId());
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz delete(final long id) {
        final Quiz quiz = mapper.map(get(id), Quiz.class);
        if (quiz != null) {
            quizRepository.delete(mapper.map(quiz, QuizEntity.class));
            initCache();
            quizCache.remove(id);
            // TODO delete quiz/questions
        }
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Quiz> getAll() {
        final List<Quiz> quizzes = new ArrayList<>();
        final List<QuizEntity> quizEntities = quizRepository.findAll();
        for (QuizEntity quizEntity : quizEntities) {
            quizzes.add(get(quizEntity.getId()));
        }
        return quizzes;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz get(final long id) {
        initCache();
        if (quizCache.containsKey(id)) {
            return quizCache.get(id);
        }
        final Quiz quiz = mapper.map(quizRepository.findById(id), Quiz.class);
        addQuizQuestions(quiz);
        quizCache.put(id, quiz);
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz start(final long quizId) throws ResourceNotFoundException {
        Quiz quiz = get(quizId);
        if (quiz != null && !quiz.isStarted()) {
            quiz.setStarted(true);
            quiz.setStartTime(LocalDateTime.now());
            quiz.setCompleted(false);
            quiz.setCompletedTime(null);
            quiz = store(quiz);
            final Long eventId = eventService.getCurrentEvent();
            final Long firstQuestionId = determineFirstQuestion(get(quizId));
            final List<Long> eventUsers = eventService.getAllEventCheckedInUsers(eventId);
            for (Long userId : eventUsers) {
                sendQuestionAskedNotification(firstQuestionId, eventId, userId);
            }
        }
        return quiz;
    }

    /**
     * Sends QuestionAsked Notifications
     *
     * @param questionId Question ID
     * @param eventId Event ID
     * @param userId User ID
     */
    private void sendQuestionAskedNotification(final Long questionId, final Long eventId, final Long userId) {
        try {
            notificationService.send(
                    userId,
                    eventId,
                    questionId,
                    NotificationType.ALL,
                    NotificationEventType.QUESTION_ASKED);
        } catch (ResourceNotFoundException e) {
            LOGGER.warn(String.format("Exception %s", e.getMessage()));
        }
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz complete(final long quizId) {
        Quiz quiz = get(quizId);
        if (quiz != null && quiz.isStarted()) {
            quiz.setCompleted(true);
            quiz.setCompletedTime(LocalDateTime.now());
            quiz = store(quiz);
        }
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Long getCurrentQuiz() {
        Long current = null;
        List<Quiz> quizzes = getAll();
        for (Quiz quiz : quizzes) {
            if (quiz.isStarted() && !quiz.isCompleted()) {
                current = quiz.getId();
                break;
            }
        }
        return current;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Quiz> findQuizzesByLessonPlanId(final Long lessonPlanId) {
        final List<QuizEntity> quizEntities = quizRepository.findByLessonPlanId(lessonPlanId);
        final List<Quiz> quizzes = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntities) {
            quizzes.add(mapper.map(quizEntity, Quiz.class));
        }
        return quizzes;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz addQuestion(final long quizId, final long questionId) throws ResourceNotFoundException {
        LOGGER.info(String.format("Adding question ID [%s] to quiz ID [%s]", questionId, quizId));
        final Quiz quiz = get(quizId);
        final Question question = questionService.get(questionId);
        List<Question> questions = quiz.getQuestions();
        // Initialize list if null
        if (questions == null) {
            questions = new ArrayList<>();
        }
        // Make sure question is not already in the list
        boolean alreadyPresent = false;
        for (final Question q : questions) {
            if (q.getId() == questionId) {
                alreadyPresent = true;
            }
        }
        // Add question if not already present
        if (!alreadyPresent) {
            questions.add(question);
        }
        quiz.setQuestions(questions);
        return store(quiz);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz removeQuestion(final long quizId, final long questionId) {
        LOGGER.info(String.format("Removing question ID [%s] from quiz ID [%s]", questionId, quizId));
        final Quiz quiz = get(quizId);
        List<Question> questions = quiz.getQuestions();
        // Initialize list if null
        if (questions == null) {
            questions = new ArrayList<>();
        }
        // Only keep questions that don't match the question ID provided
        final List<Question> list = new ArrayList<>();
        for (final Question q : questions) {
            if (q.getId() != questionId) {
                list.add(q);
            }
        }
        quiz.setQuestions(list);
        return store(quiz);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Long getNextQuestion(final Long quizId, final Long userId) throws ResourceNotFoundException {
        Long questionId = null;
        if (quizId == null) {
            return questionId;
        }
        final List<QuizQuestionEntity> quizQuestions = quizQuestionRepository.findByQuizId(quizId);
        if (quizQuestions != null && quizQuestions.size() > 0) {
            List<Long> quizQuestionIds = quizQuestions
                    .stream()
                    .map(quizQuestionEntity -> quizQuestionEntity.getQuestionId())
                    .collect(Collectors.toList());
            final List<Statistic> statistics = statisticService.findByQuizId(quizId, StatisticType.QUESTION_ANSWERED);
            final List<Long> userAnsweredQuestions = statistics
                    .stream()
                    .filter(statistic -> statistic.getUserId() == userId)
                    .map(statistic -> statistic.getQuestionId())
                    .collect(Collectors.toList());
            LOGGER.info(
                    String.format(
                            "getNextQuestion() Filtering questions [%s] from quiz questions list [%s]",
                            userAnsweredQuestions,
                            quizQuestionIds));
            quizQuestionIds.removeAll(userAnsweredQuestions);
            if (quizQuestionIds.size() > 0) {
                questionId = quizQuestionIds.get(0);
            }
        }
        LOGGER.info(String.format("getNextQuestion() returning questionId [%s] for quizId [%s]", questionId, quizId));
        return questionId;
    }

    /**
     * Adds quiz questions
     *
     * @param quiz Quiz
     */
    private void addQuizQuestions(final Quiz quiz) {
        if (quiz == null) {
            return;
        }
        final List<QuizQuestionEntity> quizQuestions = quizQuestionRepository.findByQuizId(quiz.getId());
        final List<Question> questions = new ArrayList<>();
        if (quizQuestions != null && quizQuestions.size() > 0) {
            ForkJoinPool forkJoinPool = new ForkJoinPool(4);
            try {
                questions.addAll(
                        forkJoinPool
                                .submit(
                                        () -> quizQuestions
                                                .parallelStream()
                                                .map(
                                                        quizQuestionEntity -> {
                                                            try {
                                                                return questionService.get(
                                                                        quizQuestionEntity.getQuestionId());
                                                            } catch (ResourceNotFoundException e) {
                                                                // TODO do something?
                                                                return null;
                                                            }
                                                        })
                                                .collect(
                                                        Collectors.toList()))
                                .get());
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.info("Unable to process lesson plan activities");
            }
        }
        quiz.setQuestions(questions);
    }

    /**
     * Retrieves the QuizQuestionEntity specified for a list of QuizQuestionEntity
     *
     * @param quizId Long
     * @param questionId Long
     * @param quizQuestions list of QuizQuestionEntity
     * @return QuizQuestionEntity
     */
    private static QuizQuestionEntity getQuizQuestionEntity(
            final long quizId,
            final long questionId,
            final List<QuizQuestionEntity> quizQuestions) {
        QuizQuestionEntity quizQuestionEntity = null;
        if (quizQuestions == null) {
            return quizQuestionEntity;
        }
        for (final QuizQuestionEntity qqe : quizQuestions) {
            if (qqe.getQuestionId() == questionId && qqe.getQuizId() == quizId) {
                quizQuestionEntity = qqe;
                break;
            }
        }
        return quizQuestionEntity;
    }

    /**
     * Determines the 1st question for a quiz
     *
     * @param quiz Quiz
     * @return first question ID
     */
    private static Long determineFirstQuestion(Quiz quiz) {
        Long questionId = null;
        if (quiz.getQuestions() != null && quiz.getQuestions().size() > 0) {
            questionId = quiz.getQuestions().get(0).getId();
        }
        return questionId;
    }

    /**
     * Initializes Hazelcast cache
     */
    private void initCache() {
        if (quizCache == null) {
            //hazelcastInstance = Hazelcast.newHazelcastInstance(new Config());
            quizCache = hazelcastInstance.getMap("quizzes");
        }
    }

}
