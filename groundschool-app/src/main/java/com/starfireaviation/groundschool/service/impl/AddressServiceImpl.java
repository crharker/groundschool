/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.starfireaviation.groundschool.exception.ResourceNotFoundException;
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
     * HazelcastInstance
     */
    @Autowired
    private HazelcastInstance hazelcastInstance;

    /**
     * AddressCache
     */
    private Map<Long, Address> addressCache;

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
     * @param hazelcastInstance HazelcastInstance
     */
    public AddressServiceImpl(
            AddressRepository addressRepository,
            MapperFacade mapperFacade,
            HazelcastInstance hazelcastInstance) {
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
        initCache();
        addressCache.remove(eventId);
        return mapper.map(addressRepository.save(addressEntity), Address.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Address delete(final long id) throws ResourceNotFoundException {
        Address address = mapper.map(get(id), Address.class);
        if (address != null) {
            initCache();
            addressCache.remove(id);
            addressRepository.delete(mapper.map(address, AddressEntity.class));
        }
        return address;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Address> getAll() throws ResourceNotFoundException {
        List<Address> addresses = new ArrayList<>();
        List<AddressEntity> addressEntities = addressRepository.findAll();
        for (AddressEntity addressEntity : addressEntities) {
            addresses.add(get(addressEntity.getId()));
        }
        return addresses;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Address findByEventId(final Long eventId) throws ResourceNotFoundException {
        final AddressEntity addressEntity = addressRepository.findAddressByEventId(eventId);
        if (addressEntity == null) {
            throw new ResourceNotFoundException();
        }
        return get(addressEntity.getId());
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Address get(final long id) throws ResourceNotFoundException {
        initCache();
        if (addressCache.containsKey(id)) {
            return addressCache.get(id);
        }
        final AddressEntity addressEntity = addressRepository.findById(id);
        if (addressEntity == null) {
            throw new ResourceNotFoundException();
        }
        final Address address = mapper.map(addressEntity, Address.class);
        addressCache.put(id, address);
        return address;
    }

    /**
     * Initializes Hazelcast cache
     */
    private void initCache() {
        if (addressCache == null) {
            //hazelcastInstance = Hazelcast.newHazelcastInstance(new Config());
            addressCache = hazelcastInstance.getMap("addresses");
        }
    }

}
