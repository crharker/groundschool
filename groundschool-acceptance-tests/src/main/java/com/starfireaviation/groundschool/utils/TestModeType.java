/*
 *-----------------------------------------------------------------------------
 * Copyright 2018 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.utils;

/**
 * Enumeration of different test modes supported by BDD tests.
 *
 * @author rb185213
 */
public enum TestModeType {
    /**
     * Unit test mode.
     */
    UNIT("unit"),

    /**
     * Integration test mode (a test that runs against a real service over HTTP).
     */
    INTEGRATION("integration"),

    /**
     * Integration test where the gateway is not used (i.e. requests are sent directly to the service).
     */
    LOCAL("local"),

    /**
     * Integration test where the gateway is used (i.e. requests are sent to the real service).
     */
    ACCEPTANCE("acceptance");

    /**
     * The string value of the enum.
     */
    private String value;

    /**
     * Initializes an instance of <code>TestModeType</code> with the default data.
     *
     * @param value default data
     */
    TestModeType(String value) {
        this.value = value;
    }

    /**
     * Gets the string value of the type.
     *
     * @return the string value
     */
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return value;
    }
}
