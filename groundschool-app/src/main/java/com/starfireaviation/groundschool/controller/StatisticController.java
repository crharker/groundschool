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

import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.service.StatisticService;

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
     * @return Statistic
     */
    @PostMapping
    public Statistic post(@RequestBody Statistic statistic) {
        if (statistic == null) {
            return statistic;
        }
        return statisticService.store(statistic);
    }

    /**
     * Gets a statistic
     *
     * @param id Long
     * @return Statistic
     */
    @GetMapping(path = {
            "/{id}"
    })
    public Statistic get(@PathVariable("id") long id) {
        return statisticService.findById(id);
    }

    /**
     * Updates a statistic
     *
     * @param statistic Statistic
     * @return Statistic
     */
    @PutMapping
    public Statistic put(@RequestBody Statistic statistic) {
        if (statistic == null) {
            return statistic;
        }
        return statisticService.store(statistic);
    }

    /**
     * Deletes a statistic
     *
     * @param id Long
     * @return Statistic
     */
    @DeleteMapping(path = {
            "/{id}"
    })
    public Statistic delete(@PathVariable("id") long id) {
        return statisticService.delete(id);
    }

    /**
     * Get all statistics
     *
     * @return list of Statistic
     */
    @GetMapping
    public List<Statistic> list() {
        return statisticService.findAllStatistics();
    }
}
