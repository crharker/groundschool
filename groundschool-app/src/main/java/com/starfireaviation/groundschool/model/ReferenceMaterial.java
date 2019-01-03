/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

/**
 * ReferenceMaterial
 *
 * @author brianmichael
 */
public class ReferenceMaterial extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Title
     */
    private String title;

    /**
     * URL
     */
    private String resourceLocation;

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
     * Retrieves the value for {@link #resourceLocation}.
     *
     * @return the current value
     */
    public String getResourceLocation() {
        return resourceLocation;
    }

    /**
     * Provides a value for {@link #resourceLocation}.
     *
     * @param resourceLocation the new value to set
     */
    public void setResourceLocation(String resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

}
