/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.time.LocalDateTime;

/**
 * User
 *
 * @author brianmichael
 */
public class Event extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Title
     */
    private String title;

    /**
     * LocalDateTime - startTime
     */
    private LocalDateTime startTime;

    /**
     * Address
     */
    private Address address;

    /**
     * Retrieves the value for {@link #title}.
     *
     * @return the current value
     */
    public String getTitle() {
        return title;
    }

    /**
     * Provides a value for {@link #title}.
     *
     * @param title the new value to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the value for {@link #startTime}.
     *
     * @return the current value
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Provides a value for {@link #startTime}.
     *
     * @param startTime the new value to set
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the value for {@link #address}.
     *
     * @return the current value
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Provides a value for {@link #address}.
     *
     * @param address the new value to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

}
