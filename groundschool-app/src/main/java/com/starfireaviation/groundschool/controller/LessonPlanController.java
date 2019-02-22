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
import com.starfireaviation.groundschool.model.LessonPlan;
import com.starfireaviation.groundschool.service.LessonPlanService;
import com.starfireaviation.groundschool.validation.LessonPlanValidator;

import java.security.Principal;
import java.util.List;

/**
 * LessonPlanController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
     * LessonPlanValidator
     */
    @Autowired
    private LessonPlanValidator lessonPlanValidator;

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
     * @throws ResourceNotFoundException when user is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     * @throws InvalidPayloadException when invalid data is provided
     */
    @PostMapping
    public LessonPlan post(@RequestBody LessonPlan lessonPlan, Principal principal) throws InvalidPayloadException,
            ResourceNotFoundException, AccessDeniedException {
        lessonPlanValidator.validate(lessonPlan);
        lessonPlanValidator.access(principal);
        return lessonPlanService.store(lessonPlan);
    }

    /**
     * Gets a lessonPlan
     *
     * @param lessonPlanId Long
     * @param principal Principal
     * @return LessonPlan
     * @throws ResourceNotFoundException when lesson plan is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @GetMapping(path = {
            "/{lessonPlanId}"
    })
    public LessonPlan get(@PathVariable("lessonPlanId") long lessonPlanId, Principal principal)
            throws ResourceNotFoundException, AccessDeniedException {
        lessonPlanValidator.access(principal);
        return lessonPlanService.get(lessonPlanId);
    }

    /**
     * Updates a lessonPlan
     *
     * @param lessonPlan LessonPlan
     * @param principal Principal
     * @return LessonPlan
     * @throws ResourceNotFoundException when user is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     * @throws InvalidPayloadException when invalid data is provided
     */
    @PutMapping
    public LessonPlan put(@RequestBody LessonPlan lessonPlan, Principal principal) throws InvalidPayloadException,
            ResourceNotFoundException, AccessDeniedException {
        lessonPlanValidator.validate(lessonPlan);
        lessonPlanValidator.access(principal);
        return lessonPlanService.store(lessonPlan);
    }

    /**
     * Deletes a lessonPlan
     *
     * @param lessonPlanId Long
     * @param principal Principal
     * @return LessonPlan
     * @throws ResourceNotFoundException when lesson plan is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @DeleteMapping(path = {
            "/{lessonPlanId}"
    })
    public LessonPlan delete(@PathVariable("lessonPlanId") long lessonPlanId, Principal principal)
            throws ResourceNotFoundException, AccessDeniedException {
        lessonPlanValidator.access(principal);
        return lessonPlanService.delete(lessonPlanId);
    }

    /**
     * Get all lessonPlans
     *
     * @param principal Principal
     * @return list of LessonPlans
     * @throws ResourceNotFoundException when lesson plan is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @GetMapping
    public List<LessonPlan> list(Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        lessonPlanValidator.access(principal);
        return lessonPlanService.getAll();
    }
}
