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
    @Column(name = "state", length = 50)
    private String state;

    /**
     * ZipCode
     */
    @Column(name = "zip_code", length = 10)
    private String zipCode;

}
