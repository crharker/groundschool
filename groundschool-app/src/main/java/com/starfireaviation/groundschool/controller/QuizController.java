/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.controller;

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

import com.starfireaviation.groundschool.exception.AccessDeniedException;
import com.starfireaviation.groundschool.exception.InvalidPayloadException;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.service.QuizService;
import com.starfireaviation.groundschool.validation.QuizValidator;

import java.security.Principal;
import java.util.List;

/**
 * QuizController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping({
        "/quizzes"
})
public class QuizController {

    /**
     * QuizService
     */
    @Autowired
    private QuizService quizService;

    /**
     * QuizValidator
     */
    @Autowired
    private QuizValidator quizValidator;

    /**
     * Initializes an instance of <code>QuizController</code> with the default data.
     */
    public QuizController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>QuizController</code> with the default data.
     *
     * @param quizService QuizService
     */
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    /**
     * Creates a quiz
     *
     * @param quiz Quiz
     * @param principal Principal
     * @return Quiz
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     * @throws ResourceNotFoundException when no quiz is found
     * @throws InvalidPayloadException when invalid data is provided
     */
    @PostMapping
    public Quiz post(@RequestBody Quiz quiz, Principal principal) throws InvalidPayloadException,
            ResourceNotFoundException, AccessDeniedException {
        quizValidator.validate(quiz);
        quizValidator.access(principal);
        return quizService.store(quiz);
    }

    /**
     * Gets a quiz
     *
     * @param quizId Long
     * @param principal Principal
     * @return Quiz
     * @throws ResourceNotFoundException when quiz is not found
     */
    @GetMapping(path = {
            "/{quizId}"
    })
    public Quiz get(@PathVariable("quizId") long quizId, Principal principal) throws ResourceNotFoundException {
        return quizService.get(quizId);
    }

    /**
     * Updates a quiz
     *
     * @param quiz Quiz
     * @param principal Principal
     * @return Quiz
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     * @throws ResourceNotFoundException when no quiz is found
     * @throws InvalidPayloadException when invalid data is provided
     */
    @PutMapping
    public Quiz put(@RequestBody Quiz quiz, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException, InvalidPayloadException {
        quizValidator.validate(quiz);
        quizValidator.access(principal);
        return quizService.store(quiz);
    }

    /**
     * Deletes a quiz
     *
     * @param quizId Long
     * @param principal Principal
     * @return Quiz
     * @throws ResourceNotFoundException when quiz is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @DeleteMapping(path = {
            "/{quizId}"
    })
    public Quiz delete(@PathVariable("quizId") long quizId, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException {
        quizValidator.access(principal);
        return quizService.delete(quizId);
    }

    /**
     * Get all quizzes
     *
     * @param principal Principal
     * @return list of Quiz
     * @throws ResourceNotFoundException when quiz is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @GetMapping
    public List<Quiz> list(Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        quizValidator.access(principal);
        return quizService.getAll();
    }

    /**
     * Starts a quiz
     *
     * @param quizId Long
     * @param principal Principal
     * @return started quiz
     * @throws ResourceNotFoundException when things go wrong
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @PostMapping(path = {
            "/{quizId}/start"
    })
    public Quiz start(@PathVariable("quizId") long quizId, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException {
        quizValidator.access(principal);
        return quizService.start(quizId);
    }

    /**
     * Complete's a quiz
     *
     * @param quizId Long
     * @param principal Principal
     * @return completed quiz
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     * @throws ResourceNotFoundException when no quiz is found
     */
    @PostMapping(path = {
            "/{quizId}/complete"
    })
    public Quiz complete(@PathVariable("quizId") long quizId, Principal principal) throws ResourceNotFoundException,
            AccessDeniedException {
        quizValidator.access(principal);
        return quizService.complete(quizId);
    }

    /**
     * Adds a question to a quiz
     *
     * @param quizId Long
     * @param questionId Long
     * @param principal Principal
     * @return completed quiz
     * @throws ResourceNotFoundException when quiz or question is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @PostMapping(path = {
            "/{quizId}/add/{questionId}"
    })
    public Quiz addQuestion(
            @PathVariable("quizId") long quizId,
            @PathVariable("questionId") long questionId,
            Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        quizValidator.access(principal);
        return quizService.addQuestion(quizId, questionId);
    }

    /**
     * Removes a question from a quiz
     *
     * @param quizId Long
     * @param questionId Long
     * @param principal Principal
     * @return completed quiz
     * @throws ResourceNotFoundException when quiz or question is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @PostMapping(path = {
            "/{quizId}/remove/{questionId}"
    })
    public Quiz removeQuestion(
            @PathVariable("quizId") long quizId,
            @PathVariable("questionId") long questionId,
            Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        quizValidator.access(principal);
        return quizService.removeQuestion(quizId, questionId);
    }

}
