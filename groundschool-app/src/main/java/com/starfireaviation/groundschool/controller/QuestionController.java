/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starfireaviation.groundschool.model.Answer;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.service.AnswerService;
import com.starfireaviation.groundschool.service.QuestionService;
import java.util.List;

/**
 * QuestionController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({
        "/questions"
})
public class QuestionController {

    /**
     * QuestionService
     */
    @Autowired
    private QuestionService questionService;

    /**
     * AnswerService
     */
    @Autowired
    private AnswerService answerService;

    /**
     * Initializes an instance of <code>QuestionController</code> with the default data.
     */
    public QuestionController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>QuestionController</code> with the default data.
     *
     * @param questionService QuestionService
     */
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * Creates a question
     *
     * @param question Question
     * @return Question
     */
    @PostMapping
    public Question post(@RequestBody Question question) {
        if (question == null) {
            return question;
        }
        Question response = questionService.store(question);
        List<Answer> answers = question.getAnswers();
        if (!CollectionUtils.isEmpty(answers)) {
            for (Answer answer : answers) {
                response.addAnswer(answerService.store(response.getId(), answer));
            }
        }
        return response;
    }

    /**
     * Gets a question
     *
     * @param id Long
     * @return Question
     */
    @GetMapping(path = {
            "/{id}"
    })
    public Question get(@PathVariable("id") long id) {
        Question question = questionService.findQuestionById(id);
        question.setAnswers(answerService.findByQuestionId(question.getId()));
        return question;
    }

    /**
     * Updates a question
     *
     * @param question Question
     * @return Question
     */
    @PutMapping
    public Question put(@RequestBody Question question) {
        if (question == null) {
            return question;
        }
        Question response = questionService.store(question);
        List<Answer> answers = question.getAnswers();
        if (!CollectionUtils.isEmpty(answers)) {
            for (Answer answer : answers) {
                response.addAnswer(answerService.store(response.getId(), answer));
            }
        }
        return response;
    }

    /**
     * Deletes a question
     *
     * @param id Long
     * @return Question
     */
    @DeleteMapping(path = {
            "/{id}"
    })
    public Question delete(@PathVariable("id") long id) {
        return questionService.delete(id);
    }

    /**
     * Get all questions
     *
     * @return list of Question
     */
    @GetMapping
    public List<Question> list() {
        List<Question> questions = questionService.findAllQuestions();
        for (Question question : questions) {
            question.setAnswers(answerService.findByQuestionId(question.getId()));
        }
        return questions;
    }
}
