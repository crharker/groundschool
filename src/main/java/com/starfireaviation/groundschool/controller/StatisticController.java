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

import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.service.StatisticService;

import java.security.Principal;
import java.util.List;

/**
 * StatisticController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
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
     */
    @GetMapping(path = {
            "/{statisticId}"
    })
    public Statistic get(@PathVariable("statisticId") long statisticId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return statisticService.findById(statisticId);
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
     */
    @DeleteMapping(path = {
            "/{statisticId}"
    })
    public Statistic delete(@PathVariable("statisticId") long statisticId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return statisticService.delete(statisticId);
    }

    /**
     * Get all statistics
     *
     * @param principal Principal
     * @return list of Statistic
     */
    @GetMapping
    public List<Statistic> list(Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return statisticService.findAllStatistics();
    }
}
