/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
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
     * HazelcastInstance
     */
    @Autowired
    private HazelcastInstance hazelcastInstance;

    /**
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * AnswerCache
     */
    private Map<Long, Answer> answerCache;

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
     * @param hazelcastInstance HazelcastInstance
     */
    public AnswerServiceImpl(
            AnswerRepository answerRepository,
            MapperFacade mapperFacade,
            HazelcastInstance hazelcastInstance) {
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
        initCache();
        answerCache.remove(questionId);
        return mapper.map(answerRepository.save(answerEntity), Answer.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Answer delete(long id) throws ResourceNotFoundException {
        Answer answer = mapper.map(get(id), Answer.class);
        if (answer != null) {
            answerRepository.delete(mapper.map(answer, AnswerEntity.class));
            initCache();
            answerCache.remove(id);
        }
        return answer;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Answer> getAll() throws ResourceNotFoundException {
        List<Answer> answers = new ArrayList<>();
        List<AnswerEntity> answerEntities = answerRepository.findAll();
        for (AnswerEntity answerEntity : answerEntities) {
            answers.add(get(answerEntity.getId()));
        }
        return answers;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Answer> findByQuestionId(Long questionId) throws ResourceNotFoundException {
        List<Answer> answers = new ArrayList<>();
        List<AnswerEntity> answerEntities = answerRepository.findAllAnswerByQuestionId(questionId);
        for (AnswerEntity answerEntity : answerEntities) {
            answers.add(get(answerEntity.getId()));
        }
        return answers;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Answer get(long id) throws ResourceNotFoundException {
        initCache();
        if (answerCache.containsKey(id)) {
            return answerCache.get(id);
        }
        AnswerEntity answerEntity = answerRepository.findById(id);
        if (answerEntity == null) {
            throw new ResourceNotFoundException();
        }
        final Answer answer = mapper.map(answerEntity, Answer.class);
        answerCache.put(id, answer);
        return answer;
    }

    /**
     * Initializes Hazelcast cache
     */
    private void initCache() {
        if (answerCache == null) {
            //hazelcastInstance = Hazelcast.newHazelcastInstance(new Config());
            answerCache = hazelcastInstance.getMap("answers");
        }
    }

}
