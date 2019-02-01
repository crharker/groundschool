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
import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.service.QuizService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * QuizController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({
        "/quizzes"
})
public class QuizController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizController.class);

    /**
     * UserService
     */
    @Autowired
    private QuizService quizService;

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
     */
    @PostMapping
    public Quiz post(@RequestBody Quiz quiz, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (quiz == null) {
            return quiz;
        }
        return quizService.store(quiz);
    }

    /**
     * Gets a quiz
     *
     * @param quizId Long
     * @param principal Principal
     * @return Quiz
     */
    @GetMapping(path = {
            "/{quizId}"
    })
    public Quiz get(@PathVariable("quizId") long quizId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return quizService.findById(quizId);
    }

    /**
     * Updates a quiz
     *
     * @param quiz Quiz
     * @param principal Principal
     * @return Quiz
     */
    @PutMapping
    public Quiz put(@RequestBody Quiz quiz, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (quiz == null) {
            return quiz;
        }
        return quizService.store(quiz);
    }

    /**
     * Deletes a quiz
     *
     * @param quizId Long
     * @param principal Principal
     * @return Quiz
     */
    @DeleteMapping(path = {
            "/{quizId}"
    })
    public Quiz delete(@PathVariable("quizId") long quizId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return quizService.delete(quizId);
    }

    /**
     * Get all quizzes
     *
     * @param principal Principal
     * @return list of Quiz
     */
    @GetMapping
    public List<Quiz> list(Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return quizService.findAllQuizzes();
    }

    /**
     * Starts a quiz
     *
     * @param quizId Long
     * @param principal Principal
     * @return started quiz
     * @throws ResourceNotFoundException when things go wrong
     */
    @PostMapping(path = {
            "/{quizId}/start"
    })
    public Quiz start(@PathVariable("quizId") long quizId, Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return quizService.start(quizId);
    }

    /**
     * Complete's a quiz
     *
     * @param quizId Long
     * @param principal Principal
     * @return completed quiz
     */
    @PostMapping(path = {
            "/{quizId}/complete"
    })
    public Quiz complete(@PathVariable("quizId") long quizId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return quizService.complete(quizId);
    }

    /**
     * Gets the current question for a quiz
     *
     * @param quizId Long
     * @param principal Principal
     * @return Question
     */
    @GetMapping(path = {
            "/{quizId}/current"
    })
    public Question getCurrentQuestion(@PathVariable("quizId") long quizId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return quizService.getCurrentQuestion(quizId);
    }

    /**
     * Gets the current question start time for a quiz
     *
     * @param quizId Long
     * @param principal Principal
     * @return Question start time
     */
    @GetMapping(path = {
            "/{quizId}/current/start"
    })
    public LocalDateTime getCurrentQuestionStart(@PathVariable("quizId") long quizId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return quizService.getCurrentQuestionStart(quizId);
    }

    /**
     * Adds a question to a quiz
     *
     * @param quizId Long
     * @param questionId Long
     * @param principal Principal
     * @return completed quiz
     */
    @PostMapping(path = {
            "/{quizId}/add/{questionId}"
    })
    public Quiz addQuestion(
            @PathVariable("quizId") long quizId,
            @PathVariable("questionId") long questionId,
            Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return quizService.addQuestion(quizId, questionId);
    }

    /**
     * Removes a question from a quiz
     *
     * @param quizId Long
     * @param questionId Long
     * @param principal Principal
     * @return completed quiz
     */
    @PostMapping(path = {
            "/{quizId}/remove/{questionId}"
    })
    public Quiz removeQuestion(
            @PathVariable("quizId") long quizId,
            @PathVariable("questionId") long questionId,
            Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return quizService.removeQuestion(quizId, questionId);
    }

}
