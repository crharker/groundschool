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
import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.StatisticType;
import com.starfireaviation.groundschool.service.StatisticService;

import java.security.Principal;
import java.util.List;

/**
 * StatisticController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping({
        "/statistics"
})
public class StatisticController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticController.class);

    /**
     * UserService
     */
    @Autowired
    private StatisticService statisticService;

    /**
     * Initializes an instance of <code>StatisticController</code> with the default data.
     */
    public StatisticController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>StatisticController</code> with the default data.
     *
     * @param statisticService StatisticService
     */
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    /**
     * Creates a statistic
     *
     * @param statistic Statistic
     * @param principal Principal
     * @return Statistic
     */
    @PostMapping
    public Statistic post(@RequestBody Statistic statistic, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (statistic == null) {
            return statistic;
        }
        return statisticService.store(statistic);
    }

    /**
     * Gets a statistic
     *
     * @param statisticId Long
     * @param principal Principal
     * @return Statistic
     * @throws ResourceNotFoundException when statistic is not found
     */
    @GetMapping(path = {
            "/{statisticId}"
    })
    public Statistic get(@PathVariable("statisticId") long statisticId, Principal principal)
            throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return statisticService.get(statisticId);
    }

    /**
     * Updates a statistic
     *
     * @param statistic Statistic
     * @param principal Principal
     * @return Statistic
     */
    @PutMapping
    public Statistic put(@RequestBody Statistic statistic, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (statistic == null) {
            return statistic;
        }
        return statisticService.store(statistic);
    }

    /**
     * Deletes a statistic
     *
     * @param statisticId Long
     * @param principal Principal
     * @return Statistic
     * @throws ResourceNotFoundException when statistic is not found
     */
    @DeleteMapping(path = {
            "/{statisticId}"
    })
    public Statistic delete(@PathVariable("statisticId") final long statisticId, Principal principal)
            throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return statisticService.delete(statisticId);
    }

    /**
     * Get all statistics
     *
     * @param principal Principal
     * @return list of Statistic
     * @throws ResourceNotFoundException when statistic is not found
     */
    @GetMapping
    public List<Statistic> list(Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return statisticService.getAll();
    }

    /**
     * Get all statistics for a user
     *
     * @param userId User ID
     * @param statisticType StatisticType
     * @param principal Principal
     * @return list of Statistic
     * @throws ResourceNotFoundException when user or statistic is not found
     */
    @GetMapping(path = {
            "/user/{userId}/{statisticType}"
    })
    public List<Statistic> listByUser(
            @PathVariable("userId") final Long userId,
            @PathVariable("statisticType") final StatisticType statisticType,
            Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return statisticService.findByUserId(userId, statisticType);
    }

    /**
     * Get all statistics for an event
     *
     * @param eventId User ID
     * @param statisticType StatisticType
     * @param principal Principal
     * @return list of Statistic
     * @throws ResourceNotFoundException when event or statistic is not found
     */
    @GetMapping(path = {
            "/event/{eventId}/{statisticType}"
    })
    public List<Statistic> listByEvent(
            @PathVariable("eventId") final Long eventId,
            @PathVariable("statisticType") final StatisticType statisticType,
            Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return statisticService.findByEventId(eventId, statisticType);
    }

    /**
     * Get all statistics for a question
     *
     * @param questionId User ID
     * @param statisticType StatisticType
     * @param principal Principal
     * @return list of Statistic
     * @throws ResourceNotFoundException when question of statistic is not found
     */
    @GetMapping(path = {
            "/question/{questionId}/{statisticType}"
    })
    public List<Statistic> listByQuestion(
            @PathVariable("questionId") final Long questionId,
            @PathVariable("statisticType") final StatisticType statisticType,
            Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return statisticService.findByQuestionId(questionId, statisticType);
    }

    /**
     * Get all statistics for a quiz
     *
     * @param quizId User ID
     * @param statisticType StatisticType
     * @param principal Principal
     * @return list of Statistic
     * @throws ResourceNotFoundException when quiz or statistic is not found
     */
    @GetMapping(path = {
            "/quiz/{quizId}/{statisticType}"
    })
    public List<Statistic> listByQuiz(
            @PathVariable("quizId") final Long quizId,
            @PathVariable("statisticType") final StatisticType statisticType,
            Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return statisticService.findByQuizId(quizId, statisticType);
    }
}
