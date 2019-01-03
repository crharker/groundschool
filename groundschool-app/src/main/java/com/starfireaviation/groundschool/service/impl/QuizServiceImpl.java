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

}
