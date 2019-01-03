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

import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.model.sql.QuestionEntity;
import com.starfireaviation.groundschool.repository.QuestionRepository;
import com.starfireaviation.groundschool.service.QuestionService;
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

}
