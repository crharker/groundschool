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
import com.starfireaviation.groundschool.model.Lesson;
import com.starfireaviation.groundschool.service.LessonService;

import java.security.Principal;
import java.util.List;

/**
 * LessonController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({
        "/lessons"
})
public class LessonController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LessonController.class);

    /**
     * LessonService
     */
    @Autowired
    private LessonService lessonService;

    /**
     * Initializes an instance of <code>LessonController</code> with the default data.
     */
    public LessonController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>LessonController</code> with the default data.
     *
     * @param lessonService LessonService
     */
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    /**
     * Creates a lesson
     *
     * @param lesson Lesson
     * @param principal Principal
     * @return Lesson
     */
    @PostMapping
    public Lesson post(@RequestBody Lesson lesson, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (lesson == null) {
            return lesson;
        }
        return lessonService.store(lesson);
    }

    /**
     * Gets a lesson
     *
     * @param lessonId Long
     * @param principal Principal
     * @return Lesson
     * @throws ResourceNotFoundException when lesson is not found
     */
    @GetMapping(path = {
            "/{lessonId}"
    })
    public Lesson get(@PathVariable("lessonId") long lessonId, Principal principal)
            throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return lessonService.get(lessonId);
    }

    /**
     * Updates a lesson
     *
     * @param lesson Lesson
     * @param principal Principal
     * @return Lesson
     */
    @PutMapping
    public Lesson put(@RequestBody Lesson lesson, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (lesson == null) {
            return lesson;
        }
        return lessonService.store(lesson);
    }

    /**
     * Deletes a lesson
     *
     * @param lessonId Long
     * @param principal Principal
     * @return Lesson
     * @throws ResourceNotFoundException when lesson is not found
     */
    @DeleteMapping(path = {
            "/{lessonId}"
    })
    public Lesson delete(@PathVariable("lessonId") long lessonId, Principal principal)
            throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return lessonService.delete(lessonId);
    }

    /**
     * Get all lessons
     *
     * @param principal Principal
     * @return list of Lessons
     * @throws ResourceNotFoundException when lesson is not found
     */
    @GetMapping
    public List<Lesson> list(Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return lessonService.getAll();
    }
}
