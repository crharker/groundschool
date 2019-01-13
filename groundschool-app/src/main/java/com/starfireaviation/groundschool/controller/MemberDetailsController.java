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

import com.starfireaviation.groundschool.model.MemberDetails;
import com.starfireaviation.groundschool.service.ExternalDataRetrievalService;
import com.starfireaviation.groundschool.service.MemberDetailsService;

import java.security.Principal;
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
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberDetailsController.class);

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
     * @param principal Principal
     * @return MemberDetails
     */
    @PostMapping
    public MemberDetails post(@RequestBody MemberDetails memberDetails, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (memberDetails == null) {
            return memberDetails;
        }
        return memberDetailsService.store(memberDetails);
    }

    /**
     * Gets a member details
     *
     * @param memberDetailsId Long
     * @param principal Principal
     * @return MemberDetails
     */
    @GetMapping(path = {
            "/{memberDetailsId}"
    })
    public MemberDetails get(@PathVariable("memberDetailsId") long memberDetailsId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return memberDetailsService.findById(memberDetailsId);
    }

    /**
     * Updates a member details
     *
     * @param memberDetails MemberDetails
     * @param principal Principal
     * @return MemberDetails
     */
    @PutMapping
    public MemberDetails put(@RequestBody MemberDetails memberDetails, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        if (memberDetails == null) {
            return memberDetails;
        }
        return memberDetailsService.store(memberDetails);
    }

    /**
     * Deletes a member details
     *
     * @param memberDetailsId Long
     * @param principal Principal
     * @return MemberDetails
     */
    @DeleteMapping(path = {
            "/{memberDetailsId}"
    })
    public MemberDetails delete(@PathVariable("memberDetailsId") long memberDetailsId, Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return memberDetailsService.delete(memberDetailsId);
    }

    /**
     * Get all memberDetails
     *
     * @param principal Principal
     * @return list of MemberDetails
     */
    @GetMapping
    public List<MemberDetails> list(Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        return memberDetailsService.findAllMemberDetails();
    }

    /**
     * Rebuilds member details from EAA690.net
     *
     * @param principal Principal
     */
    @PostMapping(path = {
            "/rebuild"
    })
    public void rebuild(Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        externalDataRetrievalService.rebuildMemberDetails(false);
    }

    /**
     * Rebuilds member details from EAA690.net
     *
     * @param principal Principal
     */
    @PostMapping(path = {
            "/rebuild/init"
    })
    public void rebuildInitialize(Principal principal) {
        LOGGER.info(String.format("User is logged in as %s", principal.getName()));
        externalDataRetrievalService.rebuildMemberDetails(true);
    }

}
