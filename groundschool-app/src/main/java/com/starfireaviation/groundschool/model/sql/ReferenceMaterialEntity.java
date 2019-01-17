/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ReferenceMaterialEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "REFERENCE_MATERIAL")
public class ReferenceMaterialEntity extends BaseEntity {

    /**
     * Title
     */
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    /**
     * URL
     */
    @Column(name = "resource_location", nullable = false, length = 255)
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
