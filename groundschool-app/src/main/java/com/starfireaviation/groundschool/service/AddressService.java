/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.model.Address;

/**
 * AddressService
 *
 * @author brianmichael
 */
public interface AddressService {

    /**
     * Creates a address
     *
     * @param eventId Long
     * @param address Address
     * @return Address
     */
    public Address store(Long eventId, Address address);

    /**
     * Deletes a address
     *
     * @param id Long
     * @return Address
     */
    public Address delete(long id);

    /**
     * Gets all addresses
     *
     * @return list of Address
     */
    public List<Address> findAllAddresses();

    /**
     * Gets address for an event
     *
     * @param eventId Long
     * @return list of Address
     */
    public Address findByEventId(Long eventId);

    /**
     * Gets a address
     *
     * @param id Long
     * @return Address
     */
    public Address findAddressById(long id);

}
