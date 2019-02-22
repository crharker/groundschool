/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.starfireaviation.groundschool.properties.HazelcastProperties;

/**
 * RestConfig
 *
 * @author brianmichael
 */
@Configuration
@EnableConfigurationProperties({
        HazelcastProperties.class
})
public class HazelcastConfig {

    /**
     * HazelcastInstance
     *
     * @param hazelcastProperties HazelcastProperties
     * @return HazelcastInstance
     */
    @Bean
    public HazelcastInstance hazelcast(HazelcastProperties hazelcastProperties) {
        final Config cfg = new Config();

        final MapConfig addressesMapConfig = new MapConfig("addresses");
        addressesMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(addressesMapConfig);

        final MapConfig answersMapConfig = new MapConfig("answers");
        answersMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(answersMapConfig);

        final MapConfig eventsMapConfig = new MapConfig("events");
        eventsMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(eventsMapConfig);

        final MapConfig lessonsMapConfig = new MapConfig("lessons");
        lessonsMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(lessonsMapConfig);

        final MapConfig lessonPlansMapConfig = new MapConfig("lessonplans");
        lessonPlansMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(lessonPlansMapConfig);

        final MapConfig questionsMapConfig = new MapConfig("questions");
        questionsMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(questionsMapConfig);

        final MapConfig quizzesMapConfig = new MapConfig("quizzes");
        quizzesMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(quizzesMapConfig);

        final MapConfig referenceMaterialsMapConfig = new MapConfig("referencematerials");
        referenceMaterialsMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(referenceMaterialsMapConfig);

        final MapConfig statisticsMapConfig = new MapConfig("statistics");
        statisticsMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(statisticsMapConfig);

        final MapConfig usersMapConfig = new MapConfig("users");
        usersMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(usersMapConfig);

        final MapConfig usernamesMapConfig = new MapConfig("usernames");
        usernamesMapConfig.setTimeToLiveSeconds(hazelcastProperties.getTtl());
        cfg.addMapConfig(usernamesMapConfig);

        return Hazelcast.newHazelcastInstance(cfg);
    }
}
