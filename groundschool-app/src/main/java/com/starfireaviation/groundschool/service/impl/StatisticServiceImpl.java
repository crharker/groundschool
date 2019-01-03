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

import com.starfireaviation.groundschool.model.Statistic;
import com.starfireaviation.groundschool.model.sql.StatisticEntity;
import com.starfireaviation.groundschool.repository.StatisticRepository;
import com.starfireaviation.groundschool.service.StatisticService;

import ma.glasnost.orika.MapperFacade;

/**
 * StatisticServiceImpl
 *
 * @author brianmichael
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    /**
     * StatisticRepository
     */
    @Autowired
    private StatisticRepository statisticRepository;

    /**
     * MapperFacade
     */
    @Autowired
    private MapperFacade mapper;

    /**
     * Initializes an instance of <code>StatisticServiceImpl</code> with the default data.
     */
    public StatisticServiceImpl() {
        // Default constructor
    }

    /**
     * Initializes an instance of <code>StatisticServiceImpl</code> with the default data.
     *
     * @param statisticRepository StatisticRepository
     * @param mapperFacade MapperFacade
     */
    public StatisticServiceImpl(StatisticRepository statisticRepository, MapperFacade mapperFacade) {
        this.statisticRepository = statisticRepository;
        mapper = mapperFacade;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Statistic store(Statistic statistic) {
        if (statistic == null) {
            return statistic;
        }
        return mapper.map(statisticRepository.save(mapper.map(statistic, StatisticEntity.class)), Statistic.class);
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Statistic delete(long id) {
        Statistic statistic = mapper.map(findById(id), Statistic.class);
        if (statistic != null) {
            statisticRepository.delete(mapper.map(statistic, StatisticEntity.class));
        }
        return statistic;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public List<Statistic> findAllStatistics() {
        List<Statistic> statistics = new ArrayList<>();
        List<StatisticEntity> statisticEntities = statisticRepository.findAll();
        for (StatisticEntity statisticEntity : statisticEntities) {
            statistics.add(mapper.map(statisticEntity, Statistic.class));
        }
        return statistics;
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public Statistic findById(long id) {
        return mapper.map(statisticRepository.findById(id), Statistic.class);
    }

}
