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

import com.starfireaviation.groundschool.model.MemberDetails;
import com.starfireaviation.groundschool.service.ExternalDataRetrievalService;
import com.starfireaviation.groundschool.service.MemberDetailsService;

import java.util.List;

/**
 * MemberDetailsController
 *
 * @author brianmichael
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({
        "/memberdetails"
})
public class MemberDetailsController {

    /**
     * MemberDetailsService
     */
    @Autowired
    private MemberDetailsService memberDetailsService;

    /**
     * ExternalDataRetrievalService
     */
    @Autowired
    private ExternalDataRetrievalService externalDataRetrievalService;

    /**
     * Initializes an instance of <code>MemberDetailsController</code> with the default data.
     */
    public MemberDetailsController() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>MemberDetailsController</code> with the default data.
     *
     * @param memberDetailsService MemberDetailsService
     */
    public MemberDetailsController(MemberDetailsService memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }

    /**
     * Creates a member detail
     *
     * @param memberDetails MemberDetails
     * @return MemberDetails
     */
    @PostMapping
    public MemberDetails post(@RequestBody MemberDetails memberDetails) {
        if (memberDetails == null) {
            return memberDetails;
        }
        return memberDetailsService.store(memberDetails);
    }

    /**
     * Gets a member details
     *
     * @param id Long
     * @return MemberDetails
     */
    @GetMapping(path = {
            "/{id}"
    })
    public MemberDetails get(@PathVariable("id") long id) {
        return memberDetailsService.findById(id);
    }

    /**
     * Updates a member details
     *
     * @param memberDetails MemberDetails
     * @return MemberDetails
     */
    @PutMapping
    public MemberDetails put(@RequestBody MemberDetails memberDetails) {
        if (memberDetails == null) {
            return memberDetails;
        }
        return memberDetailsService.store(memberDetails);
    }

    /**
     * Deletes a member details
     *
     * @param id Long
     * @return MemberDetails
     */
    @DeleteMapping(path = {
            "/{id}"
    })
    public MemberDetails delete(@PathVariable("id") long id) {
        return memberDetailsService.delete(id);
    }

    /**
     * Get all memberDetails
     *
     * @return list of MemberDetails
     */
    @GetMapping
    public List<MemberDetails> list() {
        return memberDetailsService.findAllMemberDetails();
    }

    /**
     * Rebuilds member details from EAA690.net
     */
    @PostMapping(path = {
            "/rebuild"
    })
    public void rebuild() {
        externalDataRetrievalService.rebuildMemberDetails(false);
    }

    /**
     * Rebuilds member details from EAA690.net
     */
    @PostMapping(path = {
            "/rebuild/init"
    })
    public void rebuildInitialize() {
        externalDataRetrievalService.rebuildMemberDetails(true);
    }

}
