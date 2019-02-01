/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.NotificationEventType;
import com.starfireaviation.groundschool.model.NotificationType;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.sql.QuizEntity;
import com.starfireaviation.groundschool.model.sql.QuizQuestionEntity;
import com.starfireaviation.groundschool.repository.QuizQuestionRepository;
import com.starfireaviation.groundschool.repository.QuizRepository;
import com.starfireaviation.groundschool.service.EventService;
import com.starfireaviation.groundschool.service.NotificationService;
import com.starfireaviation.groundschool.service.QuestionService;
import com.starfireaviation.groundschool.service.QuizService;
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
     */
    public QuizServiceImpl(QuizRepository quizRepository, MapperFacade mapperFacade) {
        this.quizRepository = quizRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz store(final Quiz quiz, final boolean partial) {
        if (quiz == null) {
            return quiz;
        }
        if (quiz.getId() != null && !partial) {
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
        final QuizEntity quizEntity = quizRepository.save(mapper.map(quiz, QuizEntity.class));
        return findById(quizEntity.getId(), false);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz delete(final long id) {
        final Quiz quiz = mapper.map(findById(id, true), Quiz.class);
        if (quiz != null) {
            quizRepository.delete(mapper.map(quiz, QuizEntity.class));
            // TODO delete quiz/questions
        }
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Quiz> findAllQuizzes() {
        final List<Quiz> quizzes = new ArrayList<>();
        final List<QuizEntity> quizEntities = quizRepository.findAll();
        for (QuizEntity quizEntity : quizEntities) {
            final Quiz quiz = mapper.map(quizEntity, Quiz.class);
            quizzes.add(quiz);
        }
        return quizzes;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz findById(final long id, final boolean partial) {
        final Quiz quiz = mapper.map(quizRepository.findById(id), Quiz.class);
        if (!partial) {
            addQuizQuestions(quiz);
        }
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz start(final long quizId) {
        Quiz quiz = findById(quizId, true);
        if (quiz != null && !quiz.isStarted()) {
            quiz.setStarted(true);
            quiz.setStartTime(LocalDateTime.now());
            quiz.setCompleted(false);
            quiz.setCompletedTime(null);
            quiz.setCurrentQuestion(determineFirstQuestion(findById(quizId, false)));
            quiz.setCurrentQuestionStartTime(LocalDateTime.now());
            quiz = store(quiz, true);
            final Long eventId = eventService.getCurrentEvent();
            final List<Long> eventUsers = eventService.getAllEventCheckedInUsers(eventId);
            for (Long userId : eventUsers) {
                try {
                    notificationService.send(
                            userId,
                            eventId,
                            quiz.getCurrentQuestion(),
                            NotificationType.ALL,
                            NotificationEventType.QUESTION_ASKED);
                } catch (ResourceNotFoundException e) {
                    LOGGER.warn(String.format("Exception %s", e.getMessage()));
                }
            }
        }
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz complete(final long quizId) {
        Quiz quiz = findById(quizId, true);
        if (quiz != null && quiz.isStarted()) {
            quiz.setCompleted(true);
            quiz.setCompletedTime(LocalDateTime.now());
            quiz = store(quiz, true);
        }
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz getCurrentQuiz() {
        Quiz current = null;
        List<Quiz> quizzes = findAllQuizzes();
        for (Quiz quiz : quizzes) {
            if (quiz.isStarted() && !quiz.isCompleted()) {
                current = quiz;
                break;
            }
        }
        return current;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Long getCurrentQuestion(final long quizId) {
        Long currentQuestionId = null;
        final Quiz quiz = findById(quizId, true);
        if (quiz != null && !quiz.isCompleted()) {
            currentQuestionId = quiz.getCurrentQuestion();
        }
        return currentQuestionId;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public LocalDateTime getCurrentQuestionStart(final long quizId) {
        LocalDateTime start = null;
        final Quiz quiz = findById(quizId, true);
        if (quiz != null && !quiz.isCompleted()) {
            start = quiz.getCurrentQuestionStartTime();
        }
        return start;
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
    public Quiz addQuestion(final long quizId, final long questionId) {
        LOGGER.info(String.format("Adding question ID [%s] to quiz ID [%s]", questionId, quizId));
        final Quiz quiz = findById(quizId, false);
        final Question question = questionService.findById(questionId, true);
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
        return store(quiz, false);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz removeQuestion(final long quizId, final long questionId) {
        LOGGER.info(String.format("Removing question ID [%s] from quiz ID [%s]", questionId, quizId));
        final Quiz quiz = findById(quizId, false);
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
        return store(quiz, false);
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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Long getNextQuestion(Long quizId, Long previousQuestionId) {
        Long questionId = null;
        if (quizId == null) {
            return questionId;
        }
        final List<QuizQuestionEntity> quizQuestions = quizQuestionRepository.findByQuizId(quizId);
        if (quizQuestions != null && quizQuestions.size() > 0 && previousQuestionId != null) {
            int index = 0;
            int count = 0;
            for (QuizQuestionEntity question : quizQuestions) {
                if (question.getQuestionId() == previousQuestionId) {
                    index = count + 1;
                    break;
                }
                count++;
            }
            if (index < quizQuestions.size()) {
                questionId = quizQuestions.get(index).getQuestionId();
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
            for (final QuizQuestionEntity quizQuestionEntity : quizQuestions) {
                questions.add(questionService.findById(quizQuestionEntity.getQuestionId(), true));
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

}
