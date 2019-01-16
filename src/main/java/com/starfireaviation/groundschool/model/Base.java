/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

import java.io.Serializable;

/**
 * Base
 *
 * @author brianmichael
 */
public class Base implements Serializable {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * Retrieves the value for {@link #id}.
     *
     * @return the current value
     */
    public Long getId() {
        return id;
    }

    /**
     * Provides a value for {@link #id}.
     *
     * @param id the new value to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
