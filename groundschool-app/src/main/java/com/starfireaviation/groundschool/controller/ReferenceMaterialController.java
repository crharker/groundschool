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

import com.starfireaviation.groundschool.model.ReferenceMaterial;
import com.starfireaviation.groundschool.service.ReferenceMaterialService;

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
     * @return ReferenceMaaterial
     */
    @PostMapping
    public ReferenceMaterial post(@RequestBody ReferenceMaterial referenceMaterial) {
        if (referenceMaterial == null) {
            return referenceMaterial;
        }
        return referenceMaterialService.store(referenceMaterial);
    }

    /**
     * Gets a referenceMaterial
     *
     * @param id Long
     * @return ReferenceMaterial
     */
    @GetMapping(path = {
            "/{id}"
    })
    public ReferenceMaterial get(@PathVariable("id") long id) {
        return referenceMaterialService.findReferenceMaterialById(id);
    }

    /**
     * Updates a referenceMaterial
     *
     * @param referenceMaterial ReferenceMaterial
     * @return ReferenceMaterial
     */
    @PutMapping
    public ReferenceMaterial put(@RequestBody ReferenceMaterial referenceMaterial) {
        if (referenceMaterial == null) {
            return referenceMaterial;
        }
        return referenceMaterialService.store(referenceMaterial);
    }

    /**
     * Deletes a referenceMaterial
     *
     * @param id Long
     * @return ReferenceMaterial
     */
    @DeleteMapping(path = {
            "/{id}"
    })
    public ReferenceMaterial delete(@PathVariable("id") long id) {
        return referenceMaterialService.delete(id);
    }

    /**
     * Get all referenceMaterial
     *
     * @return list of ReferenceMaterial
     */
    @GetMapping
    public List<ReferenceMaterial> list() {
        return referenceMaterialService.findAllReferenceMaterials();
    }
}
