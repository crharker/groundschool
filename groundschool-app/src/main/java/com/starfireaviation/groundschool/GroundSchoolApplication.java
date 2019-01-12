/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * GroundSchoolApplication
 *
 * @author brianmichael
 */
@SpringBootApplication
@EnableJpaAuditing
public class GroundSchoolApplication {

    /**
     * APPLICATION_NAME
     */
    public static final String APPLICATION_NAME = "Ground School";

    /**
     * Main method
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(GroundSchoolApplication.class, args);
    }

}
