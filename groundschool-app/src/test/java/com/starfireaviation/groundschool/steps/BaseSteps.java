/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.steps;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import com.starfireaviation.groundschool.controller.EventController;
import com.starfireaviation.groundschool.model.Role;
import com.starfireaviation.groundschool.model.User;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

/**
 * BaseSteps
 *
 * @author brianmichael
 */
public class BaseSteps {

    /**
     * Logger
     */
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * APPLICATION_HOST
     */
    protected static final String APPLICATION_HOST = "http://localhost:8080";

    /**
     * EventController
     */
    @Autowired
    protected EventController eventController;

    /**
     * Response
     */
    protected Response response;

    /**
     * ValidatableResponse
     */
    protected ValidatableResponse json;

    /**
     * RequestSpecification
     */
    protected RequestSpecification request;

    /**
     * ID
     */
    protected Long id;

    /**
     * Exception
     */
    protected Exception exception;

    /**
     * Role
     */
    protected Role role = Role.STUDENT;

    /**
     * User
     */
    protected User user;

    /**
     * Users
     */
    protected List<User> users;

    /**
     * Get HttpHeaders
     *
     * @return HttpHeaders
     */
    protected HttpHeaders getHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
        //headers.add(HttpHeaders.AUTHORIZATION, basicAuthUtil.generateBasicAuthString(username, password));
        return headers;
    }

}
