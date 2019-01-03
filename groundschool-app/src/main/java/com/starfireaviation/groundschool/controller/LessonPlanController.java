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

import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.service.LessonPlanService;
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
     * @return LessonPlan
     */
    @PostMapping
    public LessonPlan post(@RequestBody LessonPlan lessonPlan) {
        if (lessonPlan == null) {
            return lessonPlan;
        }
        return lessonPlanService.store(lessonPlan);
    }

    /**
     * Gets a lessonPlan
     *
     * @param id Long
     * @return LessonPlan
     */
    @GetMapping(path = {
            "/{id}"
    })
    public LessonPlan get(@PathVariable("id") long id) {
        return lessonPlanService.findById(id);
    }

    /**
     * Updates a lessonPlan
     *
     * @param lessonPlan LessonPlan
     * @return LessonPlan
     */
    @PutMapping
    public LessonPlan put(@RequestBody LessonPlan lessonPlan) {
        if (lessonPlan == null) {
            return lessonPlan;
        }
        return lessonPlanService.store(lessonPlan);
    }

    /**
     * Deletes a lessonPlan
     *
     * @param id Long
     * @return LessonPlan
     */
    @DeleteMapping(path = {
            "/{id}"
    })
    public LessonPlan delete(@PathVariable("id") long id) {
        return lessonPlanService.delete(id);
    }

    /**
     * Get all lessonPlans
     *
     * @return list of LessonPlans
     */
    @GetMapping
    public List<LessonPlan> list() {
        return lessonPlanService.findAllLessonPlans();
    }
}
