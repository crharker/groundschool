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

import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.service.LessonPlanService;

import java.security.Principal;
import java.util.List;

/**
 * LessonPlanController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({
        "/lessonplans"
})
public class LessonPlanController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonPlanController.class);

    /**
     * LessonPlanService
     */
    @Autowired
    private LessonPlanService lessonPlanService;

    /**
     * Initializes an instance of <code>UserController</code> with the default data.
     */
    public LessonPlanController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>UserController</code> with the default data.
     *
     * @param lessonPlanService LessonPlanService
     */
    public LessonPlanController(LessonPlanService lessonPlanService) {
        this.lessonPlanService = lessonPlanService;
    }

    /**
     * Creates a lessonPlan
     *
     * @param lessonPlan LessonPlan
     * @param principal Principal
     * @return LessonPlan
     */
    @PostMapping
    public LessonPlan post(@RequestBody LessonPlan lessonPlan, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (lessonPlan == null) {
            return lessonPlan;
        }
        return lessonPlanService.store(lessonPlan);
    }

    /**
     * Gets a lessonPlan
     *
     * @param lessonPlanId Long
     * @param principal Principal
     * @return LessonPlan
     */
    @GetMapping(path = {
            "/{lessonPlanId}"
    })
    public LessonPlan get(@PathVariable("lessonPlanId") long lessonPlanId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return lessonPlanService.findById(lessonPlanId);
    }

    /**
     * Updates a lessonPlan
     *
     * @param lessonPlan LessonPlan
     * @param principal Principal
     * @return LessonPlan
     */
    @PutMapping
    public LessonPlan put(@RequestBody LessonPlan lessonPlan, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (lessonPlan == null) {
            return lessonPlan;
        }
        return lessonPlanService.store(lessonPlan);
    }

    /**
     * Deletes a lessonPlan
     *
     * @param lessonPlanId Long
     * @param principal Principal
     * @return LessonPlan
     */
    @DeleteMapping(path = {
            "/{lessonPlanId}"
    })
    public LessonPlan delete(@PathVariable("lessonPlanId") long lessonPlanId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return lessonPlanService.delete(lessonPlanId);
    }

    /**
     * Get all lessonPlans
     *
     * @param principal Principal
     * @return list of LessonPlans
     */
    @GetMapping
    public List<LessonPlan> list(Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return lessonPlanService.findAllLessonPlans();
    }
}
