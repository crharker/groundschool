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

import com.starfireaviation.groundschool.exception.AccessDeniedException;
import com.starfireaviation.groundschool.exception.InvalidPayloadException;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.service.ReferenceMaterialService;
import com.starfireaviation.groundschool.validation.ReferenceMaterialValidator;

import java.security.Principal;
import java.util.List;

/**
 * ReferenceMaterialController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
     * EventValidator
     */
    @Autowired
    private ReferenceMaterialValidator referenceMaterialValidator;

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
     * @throws ResourceNotFoundException when no reference material is found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     * @throws InvalidPayloadException when invalid data is provided
     */
    @PostMapping
    public ReferenceMaterial post(@RequestBody ReferenceMaterial referenceMaterial, Principal principal)
            throws InvalidPayloadException, ResourceNotFoundException, AccessDeniedException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        referenceMaterialValidator.validate(referenceMaterial);
        referenceMaterialValidator.access(principal);
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
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     * @throws ResourceNotFoundException when no reference material is found
     * @throws InvalidPayloadException when invalid data is provided
     */
    @PutMapping
    public ReferenceMaterial put(@RequestBody ReferenceMaterial referenceMaterial, Principal principal)
            throws ResourceNotFoundException, AccessDeniedException, InvalidPayloadException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        referenceMaterialValidator.validate(referenceMaterial);
        referenceMaterialValidator.access(principal);
        return referenceMaterialService.store(referenceMaterial);
    }

    /**
     * Deletes a referenceMaterial
     *
     * @param referenceMaterialId Long
     * @param principal Principal
     * @return ReferenceMaterial
     * @throws ResourceNotFoundException when reference material is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @DeleteMapping(path = {
            "/{referenceMaterialId}"
    })
    public ReferenceMaterial delete(
            @PathVariable("referenceMaterialId") long referenceMaterialId,
            Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        referenceMaterialValidator.access(principal);
        return referenceMaterialService.delete(referenceMaterialId);
    }

    /**
     * Get all referenceMaterial
     *
     * @param principal Principal
     * @return list of ReferenceMaterial
     * @throws ResourceNotFoundException when reference material is not found
     * @throws AccessDeniedException when user doesn't have permission to perform operation
     */
    @GetMapping
    public List<ReferenceMaterial> list(Principal principal) throws ResourceNotFoundException, AccessDeniedException {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        referenceMaterialValidator.access(principal);
        return referenceMaterialService.getAll();
    }
}
