/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.model.sql.QuizEntity;
import com.starfireaviation.groundschool.repository.QuizRepository;
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
     * QuizRepository
     */
    @Autowired
    private QuizRepository quizRepository;

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
    public Quiz store(Quiz quiz) {
        if (quiz == null) {
            return quiz;
        }
        return mapper.map(quizRepository.save(mapper.map(quiz, QuizEntity.class)), Quiz.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz delete(long id) {
        Quiz quiz = mapper.map(findById(id), Quiz.class);
        if (quiz != null) {
            quizRepository.delete(mapper.map(quiz, QuizEntity.class));
        }
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Quiz> findAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        List<QuizEntity> quizEntities = quizRepository.findAll();
        for (QuizEntity quizEntity : quizEntities) {
            quizzes.add(mapper.map(quizEntity, Quiz.class));
        }
        return quizzes;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz findById(long id) {
        return mapper.map(quizRepository.findById(id), Quiz.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz start(long quizId) {
        Quiz quiz = findById(quizId);
        if (quiz != null && !quiz.isStarted()) {
            quiz.setStarted(true);
            quiz.setStartTime(LocalDateTime.now());
            quiz = store(quiz);
        }
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz startQuestion(long quizId) {
        Quiz quiz = findById(quizId);
        if (quiz != null && quiz.isStarted() && !quiz.isCompleted() && quiz.getCurrentQuestion() == null) {
            Long nextQuestionId = determineNextQuestion(quiz);
            if (nextQuestionId != null) {
                quiz.setCurrentQuestion(nextQuestionId);
                quiz.setCurrentQuestionStartTime(LocalDateTime.now());
                quiz = store(quiz);
            } else {
                complete(quizId);
            }
        }
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Quiz complete(long quizId) {
        Quiz quiz = findById(quizId);
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
    public Quiz completeQuestion(long quizId) {
        Quiz quiz = findById(quizId);
        if (quiz != null && quiz.isStarted()) {
            Long questionId = quiz.getCurrentQuestion();
            String completedQuestions = quiz.getCompletedQuestions();
            if (completedQuestions == null) {
                completedQuestions = questionId.toString();
            } else {
                completedQuestions = completedQuestions + "," + questionId.toString();
            }
            quiz.setCompletedQuestions(completedQuestions);
            quiz.setCurrentQuestion(null);
            quiz.setStartTime(null);
            quiz = store(quiz);
            if (determineNextQuestion(quiz) == null) {
                complete(quizId);
            }
        }
        return quiz;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Question getCurrentQuestion(long quizId) {
        Question question = null;
        Quiz quiz = findById(quizId);
        if (quiz != null && !quiz.isCompleted()) {
            Long currentQuestionId = quiz.getCurrentQuestion();
            for (Question q : quiz.getQuestions()) {
                if (currentQuestionId.equals(q.getId())) {
                    question = q;
                }
            }
        }
        return question;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public LocalDateTime getCurrentQuestionStart(long quizId) {
        LocalDateTime start = null;
        Quiz quiz = findById(quizId);
        if (quiz != null && !quiz.isCompleted()) {
            start = quiz.getCurrentQuestionStartTime();
        }
        return start;
    }

    /**
     * Determines the next question in a quiz
     *
     * @param quiz Quiz
     * @return question ID
     */
    private static Long determineNextQuestion(Quiz quiz) {
        Long questionId = null;
        String completedQuestions = quiz.getCompletedQuestions();
        if (completedQuestions == null || "".equals(completedQuestions)) {
            questionId = quiz.getQuestions().get(0).getId();
            return questionId;
        }
        String[] completedQuestionsArray = quiz.getCompletedQuestions().split(",");
        Long lastQuestionId = Long.valueOf(completedQuestionsArray[completedQuestionsArray.length - 1]);
        boolean useNext = false;
        for (Question question : quiz.getQuestions()) {
            if (useNext) {
                questionId = question.getId();
                break;
            }
            if (lastQuestionId == question.getId()) {
                useNext = true;
            }
        }
        return questionId;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Quiz> findQuizzesByLessonPlanId(Long lessonPlanId) {
        List<QuizEntity> quizEntities = quizRepository.findByLessonPlanId(lessonPlanId);
        List<Quiz> quizzes = new ArrayList<>();
        for (QuizEntity quizEntity : quizEntities) {
            quizzes.add(mapper.map(quizEntity, Quiz.class));
        }
        return quizzes;
    }

}
