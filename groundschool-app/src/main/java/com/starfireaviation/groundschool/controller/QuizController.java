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

import com.starfireaviation.groundschool.model.Quiz;
import com.starfireaviation.groundschool.service.QuizService;

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
     * @return Quiz
     */
    @PostMapping
    public Quiz post(@RequestBody Quiz quiz) {
        if (quiz == null) {
            return quiz;
        }
        return quizService.store(quiz);
    }

    /**
     * Gets a quiz
     *
     * @param id Long
     * @return Quiz
     */
    @GetMapping(path = {
            "/{id}"
    })
    public Quiz get(@PathVariable("id") long id) {
        return quizService.findById(id);
    }

    /**
     * Updates a quiz
     *
     * @param quiz Quiz
     * @return Quiz
     */
    @PutMapping
    public Quiz put(@RequestBody Quiz quiz) {
        if (quiz == null) {
            return quiz;
        }
        return quizService.store(quiz);
    }

    /**
     * Deletes a quiz
     *
     * @param id Long
     * @return Quiz
     */
    @DeleteMapping(path = {
            "/{id}"
    })
    public Quiz delete(@PathVariable("id") long id) {
        return quizService.delete(id);
    }

    /**
     * Get all quizzes
     *
     * @return list of Quiz
     */
    @GetMapping
    public List<Quiz> list() {
        return quizService.findAllQuizzes();
    }
}
