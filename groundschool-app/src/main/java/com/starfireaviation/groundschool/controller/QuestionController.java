/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Question;
import com.starfireaviation.groundschool.service.QuestionService;
import java.security.Principal;
import java.util.List;

/**
 * QuestionController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping({
        "/questions"
})
public class QuestionController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

    /**
     * QuestionService
     */
    @Autowired
    private QuestionService questionService;

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
     * @param principal Principal
     * @return Question
     * @throws ResourceNotFoundException when question is not found
     */
    @PostMapping
    public Question post(@RequestBody Question question, Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (question == null) {
            return question;
        }
        return questionService.store(question);
    }

    /**
     * Gets a question
     *
     * @param questionId Long
     * @param principal Principal
     * @return Question
     * @throws ResourceNotFoundException when question is not found
     */
    @GetMapping(path = {
            "/{questionId}"
    })
    public Question get(@PathVariable("questionId") long questionId, Principal principal)
            throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return questionService.get(questionId);
    }

    /**
     * Updates a question
     *
     * @param question Question
     * @param principal Principal
     * @return Question
     * @throws ResourceNotFoundException when question is not found
     */
    @PutMapping
    public Question put(@RequestBody Question question, Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (question == null) {
            return question;
        }
        return questionService.store(question);
    }

    /**
     * Deletes a question
     *
     * @param questionId Long
     * @param principal Principal
     * @return Question
     * @throws ResourceNotFoundException when question is not found
     */
    @DeleteMapping(path = {
            "/{questionId}"
    })
    public Question delete(@PathVariable("questionId") long questionId, Principal principal)
            throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return questionService.delete(questionId);
    }

    /**
     * Get all questions
     *
     * @param principal Principal
     * @return list of Question
     * @throws ResourceNotFoundException when question is not found
     */
    @GetMapping
    public List<Question> list(Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return questionService.getAll();
    }

    /**
     * Answers a question for a user
     *
     * @param questionId Long
     * @param userId Long
     * @param selection String
     * @param principal Principal
     * @return answered correctly?
     * @throws ResourceNotFoundException when user or question is not found
     */
    @PostMapping(path = {
            "/{questionId}/answer/{userId}/{selection}"
    })
    public boolean answer(
            @PathVariable("questionId") long questionId,
            @PathVariable("userId") long userId,
            @PathVariable("selection") String selection,
            Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return questionService.answer(questionId, userId, selection, null);
    }

    /**
     * Assigns reference material to a question
     *
     * @param questionId Event ID
     * @param referenceMaterialId LessonPlan ID
     * @param principal Principal
     * @throws ResourceNotFoundException when question is not found
     */
    @PostMapping(path = {
            "/{questionId}/assign/referencematerial/{referenceMaterialId}"
    })
    public void assignReferenceMaterial(
            @PathVariable("questionId") long questionId,
            @PathVariable("referenceMaterialId") long referenceMaterialId,
            Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        questionService.assignReferenceMaterial(questionId, referenceMaterialId);
    }

    /**
     * Unassigns reference material from a question
     *
     * @param questionId Event ID
     * @param referenceMaterialId LessonPlan ID
     * @param principal Principal
     * @throws ResourceNotFoundException when question is not found
     */
    @PostMapping(path = {
            "/{questionId}/unassign/referencematerial/{referenceMaterialId}"
    })
    public void unassignReferenceMaterial(
            @PathVariable("questionId") long questionId,
            @PathVariable("referenceMaterialId") long referenceMaterialId,
            Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        questionService.unassignReferenceMaterial(questionId, referenceMaterialId);
    }

}
