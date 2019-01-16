/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.starfireaviation.groundschool.model.sql.AddressEntity;

/**
 * AddressRepository
 *
 * @author brianmichael
 */
public interface AddressRepository extends Repository<AddressEntity, Long> {

    /**
     * Deletes a address
     *
     * @param address Address
     */
    void delete(AddressEntity address);

    /**
     * Gets all addresses
     *
     * @return list of Address
     */
    List<AddressEntity> findAll();

    /**
     * Gets all addresses for an event
     *
     * @param addressId address ID
     * @return list of Address
     */
    AddressEntity findAddressByEventId(Long addressId);

    /**
     * Gets a address
     *
     * @param id Long
     * @return Address
     */
    AddressEntity findById(long id);

    /**
     * Saves a address
     *
     * @param address Address
     * @return Address
     */
    AddressEntity save(AddressEntity address);
}
