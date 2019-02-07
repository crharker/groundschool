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
import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.service.ReferenceMaterialService;

import java.security.Principal;
import java.util.List;

/**
 * ReferenceMaterialController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({
        "/referencematerials"
})
public class ReferenceMaterialController {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceMaterialController.class);

    /**
     * ReferenceMaterialService
     */
    @Autowired
    private ReferenceMaterialService referenceMaterialService;

    /**
     * Initializes an instance of <code>ReferenceMaterialController</code> with the default data.
     */
    public ReferenceMaterialController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>ReferenceMaterialController</code> with the default data.
     *
     * @param referenceMaterialService ReferenceMaterialService
     */
    public ReferenceMaterialController(ReferenceMaterialService referenceMaterialService) {
        this.referenceMaterialService = referenceMaterialService;
    }

    /**
     * Creates a reference material
     *
     * @param referenceMaterial ReferenceMaterial
     * @param principal Principal
     * @return ReferenceMaaterial
     */
    @PostMapping
    public ReferenceMaterial post(@RequestBody ReferenceMaterial referenceMaterial, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (referenceMaterial == null) {
            return referenceMaterial;
        }
        return referenceMaterialService.store(referenceMaterial);
    }

    /**
     * Gets a referenceMaterial
     *
     * @param referenceMaterialId Long
     * @param principal Principal
     * @return ReferenceMaterial
     * @throws ResourceNotFoundException when reference material is not found
     */
    @GetMapping(path = {
            "/{referenceMaterialId}"
    })
    public ReferenceMaterial get(@PathVariable("referenceMaterialId") long referenceMaterialId, Principal principal)
            throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return referenceMaterialService.get(referenceMaterialId);
    }

    /**
     * Updates a referenceMaterial
     *
     * @param referenceMaterial ReferenceMaterial
     * @param principal Principal
     * @return ReferenceMaterial
     */
    @PutMapping
    public ReferenceMaterial put(@RequestBody ReferenceMaterial referenceMaterial, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (referenceMaterial == null) {
            return referenceMaterial;
        }
        return referenceMaterialService.store(referenceMaterial);
    }

    /**
     * Deletes a referenceMaterial
     *
     * @param referenceMaterialId Long
     * @param principal Principal
     * @return ReferenceMaterial
     * @throws ResourceNotFoundException when reference material is not found
     */
    @DeleteMapping(path = {
            "/{referenceMaterialId}"
    })
    public ReferenceMaterial delete(
            @PathVariable("referenceMaterialId") long referenceMaterialId,
            Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return referenceMaterialService.delete(referenceMaterialId);
    }

    /**
     * Get all referenceMaterial
     *
     * @param principal Principal
     * @return list of ReferenceMaterial
     * @throws ResourceNotFoundException when reference material is not found
     */
    @GetMapping
    public List<ReferenceMaterial> list(Principal principal) throws ResourceNotFoundException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return referenceMaterialService.getAll();
    }
}
