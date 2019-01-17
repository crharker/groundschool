/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.model;

/**
 * Address
 *
 * @author brianmichael
 */
public class Address extends Base {

    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * AddressLine1
     */
    private String addressLine1;

    /**
     * AddressLine2
     */
    private String addressLine2;

    /**
     * City
     */
    private String city;

    /**
     * State
     */
    private String state;

    /**
     * ZipCode
     */
    private String zipCode;

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
     * {@inheritDoc} Required implementation.
     */
    @Override
    public String toString() {
        return (addressLine1 != null ? addressLine1 + ", " : "")
                + (addressLine2 != null ? addressLine2 + ", " : "")
                + (city != null ? city + ", " : "")
                + (state != null ? state + " " : "")
                + (zipCode != null ? zipCode : "");
    }

}
