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
 * LessonEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "LESSON")
public class LessonEntity extends BaseEntity {

    /**
     * Course
     */
    @Column(name = "course", nullable = false, length = 500)
    private String course;

    /**
     * Unit
     */
    @Column(name = "unit", nullable = false, length = 500)
    private String unit;

    /**
     * SubUnit
     */
    @Column(name = "sub_unit", length = 500)
    private String subUnit;

    /**
     * Text
     */
    @Column(name = "text", length = 4000)
    private String text;

    /**
     * Retrieves the value for {@link #course}.
     *
     * @return the current value
     */
    public String getCourse() {
        return course;
    }

    /**
     * Provides a value for {@link #course}.
     *
     * @param course the new value to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Retrieves the value for {@link #unit}.
     *
     * @return the current value
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Provides a value for {@link #unit}.
     *
     * @param unit the new value to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Retrieves the value for {@link #subUnit}.
     *
     * @return the current value
     */
    public String getSubUnit() {
        return subUnit;
    }

    /**
     * Provides a value for {@link #subUnit}.
     *
     * @param subUnit the new value to set
     */
    public void setSubUnit(String subUnit) {
        this.subUnit = subUnit;
    }

    /**
     * Retrieves the value for {@link #text}.
     *
     * @return the current value
     */
    public String getText() {
        return text;
    }

    /**
     * Provides a value for {@link #text}.
     *
     * @param text the new value to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
