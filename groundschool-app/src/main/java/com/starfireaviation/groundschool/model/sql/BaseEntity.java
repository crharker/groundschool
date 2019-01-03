/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * BaseEntity
 *
 * @author brianmichael
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {
                "createdAt", "updatedAt"
        },
        allowGetters = true)
public class BaseEntity {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Created At
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    /**
     * Updated At
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

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

    /**
     * Retrieves the value for {@link #createdAt}.
     *
     * @return the current value
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Provides a value for {@link #createdAt}.
     *
     * @param createdAt the new value to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Retrieves the value for {@link #updatedAt}.
     *
     * @return the current value
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Provides a value for {@link #updatedAt}.
     *
     * @param updatedAt the new value to set
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
