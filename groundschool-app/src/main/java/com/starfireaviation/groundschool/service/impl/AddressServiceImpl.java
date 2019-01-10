/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starfireaviation.groundschool.model.Address;
import com.starfireaviation.groundschool.model.sql.AddressEntity;
import com.starfireaviation.groundschool.repository.AddressRepository;
import com.starfireaviation.groundschool.repository.EventRepository;
import com.starfireaviation.groundschool.service.AddressService;
import ma.glasnost.orika.MapperFacade;

/**
 * AddressServiceImpl
 *
 * @author brianmichael
 */
@Service
public class AddressServiceImpl implements AddressService {

    /**
     * AddressRepository
     */
    @Autowired
    private AddressRepository addressRepository;

    /**
     * EventRepository
     */
    @Autowired
    private EventRepository eventRepository;

    /**
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * Initializes an instance of <code>AddressServiceImpl</code> with the default data.
     */
    public AddressServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>AddressServiceImpl</code> with the default data.
     *
     * @param addressRepository AddressRepository
     * @param mapperFacade MapperFacade
     */
    public AddressServiceImpl(AddressRepository addressRepository, MapperFacade mapperFacade) {
        this.addressRepository = addressRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Address store(Long eventId, Address address) {
        if (eventId == null || address == null) {
            return address;
        }
        AddressEntity addressEntity = mapper.map(address, AddressEntity.class);
        addressEntity.setEvent(eventRepository.findById(eventId));
        return mapper.map(addressRepository.save(addressEntity), Address.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Address delete(long id) {
        Address address = mapper.map(findAddressById(id), Address.class);
        if (address != null) {
            addressRepository.delete(mapper.map(address, AddressEntity.class));
        }
        return address;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Address> findAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        List<AddressEntity> addressEntities = addressRepository.findAll();
        for (AddressEntity addressEntity : addressEntities) {
            addresses.add(mapper.map(addressEntity, Address.class));
        }
        return addresses;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Address findByEventId(Long eventId) {
        return mapper.map(addressRepository.findAddressByEventId(eventId), Address.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Address findAddressById(long id) {
        return mapper.map(addressRepository.findById(id), Address.class);
    }

}
