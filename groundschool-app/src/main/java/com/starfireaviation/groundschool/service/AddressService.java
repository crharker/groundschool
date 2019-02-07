/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service;

import java.util.List;

import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
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
     * @throws ResourceNotFoundException when no address is found
     */
    public Address delete(long id) throws ResourceNotFoundException;

    /**
     * Gets all addresses
     *
     * @return list of Address
     * @throws ResourceNotFoundException when an address is not found
     */
    public List<Address> getAll() throws ResourceNotFoundException;

    /**
     * Gets address for an event
     *
     * @param eventId Long
     * @return list of Address
     * @throws ResourceNotFoundException when no address is found
     */
    public Address findByEventId(Long eventId) throws ResourceNotFoundException;

    /**
     * Gets a address
     *
     * @param id Long
     * @return Address
     * @throws ResourceNotFoundException when no address is found
     */
    public Address get(long id) throws ResourceNotFoundException;

}
