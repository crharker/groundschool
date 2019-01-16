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

import com.starfireaviation.groundschool.model.Answer;
import com.starfireaviation.groundschool.model.sql.AnswerEntity;
import com.starfireaviation.groundschool.repository.AnswerRepository;
import com.starfireaviation.groundschool.repository.QuestionRepository;
import com.starfireaviation.groundschool.service.AnswerService;
import ma.glasnost.orika.MapperFacade;

/**
 * AnswerServiceImpl
 *
 * @author brianmichael
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    /**
     * AnswerRepository
     */
    @Autowired
    private AnswerRepository answerRepository;

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
     * Initializes an instance of <code>AnswerServiceImpl</code> with the default data.
     */
    public AnswerServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>AnswerServiceImpl</code> with the default data.
     *
     * @param answerRepository AnswerRepository
     * @param mapperFacade MapperFacade
     */
    public AnswerServiceImpl(AnswerRepository answerRepository, MapperFacade mapperFacade) {
        this.answerRepository = answerRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Answer store(Long questionId, Answer answer) {
        if (questionId == null || answer == null) {
            return answer;
        }
        AnswerEntity answerEntity = mapper.map(answer, AnswerEntity.class);
        answerEntity.setQuestion(questionRepository.findById(questionId));
        return mapper.map(answerRepository.save(answerEntity), Answer.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Answer delete(long id) {
        Answer answer = mapper.map(findAnswerById(id), Answer.class);
        if (answer != null) {
            answerRepository.delete(mapper.map(answer, AnswerEntity.class));
        }
        return answer;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Answer> findAllAnswers() {
        List<Answer> answers = new ArrayList<>();
        List<AnswerEntity> answerEntities = answerRepository.findAll();
        for (AnswerEntity answerEntity : answerEntities) {
            answers.add(mapper.map(answerEntity, Answer.class));
        }
        return answers;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Answer> findByQuestionId(Long questionId) {
        List<Answer> answers = new ArrayList<>();
        List<AnswerEntity> answerEntities = answerRepository.findAllAnswerByQuestionId(questionId);
        for (AnswerEntity answerEntity : answerEntities) {
            answers.add(mapper.map(answerEntity, Answer.class));
        }
        return answers;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Answer findAnswerById(long id) {
        return mapper.map(answerRepository.findById(id), Answer.class);
    }

}
