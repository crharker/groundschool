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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starfireaviation.groundschool.service.ReportService;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * ReportController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({
        "/reports"
})
public class ReportController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    /**
     * ReportService
     */
    @Autowired
    private ReportService reportService;

    /**
     * Initializes an instance of <code>ReportController</code> with the default data.
     */
    public ReportController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>ReportController</code> with the default data.
     *
     * @param reportService ReportService
     */
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Gets all of a user's answers
     *
     * @param userId Long
     * @param principal Principal
     * @return List<Map<String, String>>
     */
    @GetMapping(path = {
            "/answers/user/{userId}"
    })
    public List<Map<String, String>> get(@PathVariable("userId") long userId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return reportService.getAllAnswersForUser(userId);
    }

}
