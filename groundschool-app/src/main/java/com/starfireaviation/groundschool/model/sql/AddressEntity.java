/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model.sql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * AddressEntity
 *
 * @author brianmichael
 */
@Entity
@Table(name = "ADDRESS")
public class AddressEntity extends BaseEntity {

    /**
     * AddressLine1
     */
    @Column(name = "address_line_1", length = 100)
    private String addressLine1;

    /**
     * AddressLine2
     */
    @Column(name = "address_line_2", length = 100)
    private String addressLine2;

    /**
     * City
     */
    @Column(name = "city", length = 100)
    private String city;

    /**
     * State
     */
    @Column(name = "state", length = 2)
    private String state;

    /**
     * ZipCode
     */
    @Column(name = "zip_code", length = 10)
    private String zipCode;

    /**
     * Event
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EventEntity event;

    /**
     * Retrieves the value for {@link #addressLine1}.
     *
     * @return the current value
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Provides a value for {@link #addressLine1}.
     *
     * @param addressLine1 the new value to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Retrieves the value for {@link #addressLine2}.
     *
     * @return the current value
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Provides a value for {@link #addressLine2}.
     *
     * @param addressLine2 the new value to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Retrieves the value for {@link #city}.
     *
     * @return the current value
     */
    public String getCity() {
        return city;
    }

    /**
     * Provides a value for {@link #city}.
     *
     * @param city the new value to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retrieves the value for {@link #state}.
     *
     * @return the current value
     */
    public String getState() {
        return state;
    }

    /**
     * Provides a value for {@link #state}.
     *
     * @param state the new value to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Retrieves the value for {@link #zipCode}.
     *
     * @return the current value
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Provides a value for {@link #zipCode}.
     *
     * @param zipCode the new value to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Retrieves the value for {@link #event}.
     *
     * @return the current value
     */
    public EventEntity getEvent() {
        return event;
    }

    /**
     * Provides a value for {@link #event}.
     *
     * @param event the new value to set
     */
    public void setEvent(EventEntity event) {
        this.event = event;
    }

}
