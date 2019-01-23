/*
 *-----------------------------------------------------------------------------
 * Copyright 2019 Starfire Aviation, LLC
 *-----------------------------------------------------------------------------
 */
package com.starfireaviation.groundschool.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.starfireaviation.groundschool.properties.AuthorizationProperties;
import com.starfireaviation.groundschool.properties.DatabaseProperties;

/**
 * WebSecurityConfiguration
 *
 * @author brianmichael
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties({
        DatabaseProperties.class,
        AuthorizationProperties.class
})
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * USERNAME_PASSWORD_QUERY
     */
    public static final String USERNAME_PASSWORD_QUERY = "SELECT username, password, true FROM user WHERE username = ?";

    /**
     * USERNAME_ROLE_QUERY
     */
    public static final String USERNAME_ROLE_QUERY = "SELECT username, role FROM user WHERE username = ?";

    /**
     * AuthenticationEntryPoint
     */
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * DatabaseProperties
     */
    @Autowired
    private DatabaseProperties databaseProperties;

    /**
     * AuthorizationProperties
     */
    @Autowired
    private AuthorizationProperties authorizationProperties;

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                // Allow non-logged in users to access
                .antMatchers(HttpMethod.POST, "/notifications/sms")
                .authenticated()
                .antMatchers(HttpMethod.GET, "/events/rsvp/**")
                .authenticated()
                .antMatchers(HttpMethod.GET, "/events/**/register/**")
                .authenticated()
                .antMatchers(HttpMethod.GET, "/events/**/unregister/**")
                .authenticated()
                .antMatchers(HttpMethod.POST, "/users/verify/**")
                .authenticated()
                .antMatchers(HttpMethod.POST, "/users/logout")
                .authenticated()
                // Allow only Admin and Instructors to access
                .antMatchers(HttpMethod.POST, "/events/**/assign/**")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/events/**/unassign/**")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/events/**/start/**")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/events")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.PUT, "/events")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/events/**")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/events/**/start")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/events/**/complete")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers("/lessonplans/**")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers("/memberdetails/**")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/questions")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.PUT, "/questions")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/questions")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/questions/**")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/quizzes")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/quizzes/**")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.PUT, "/quizzes")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/quizzes")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/referencematerials")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.PUT, "/referencematerials")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/referencematerials")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/statistics")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.PUT, "/statistics")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/statistics")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/users")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.GET, "/users")
                .hasAnyRole("ADMIN", "INSTRUCTOR")
                // Everything else requires one of Admin, Instructor, or Student
                .anyRequest()
                .hasAnyRole("ADMIN", "INSTRUCTOR", "STUDENT")
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    /**
     * Configure admin user
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception when things go wrong
     */
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(authorizationProperties.getAdminPassword())
                .roles("ADMIN");
        auth
                .jdbcAuthentication()
                .dataSource(dataSource())
                .usersByUsernameQuery(USERNAME_PASSWORD_QUERY)
                .authoritiesByUsernameQuery(USERNAME_ROLE_QUERY)
                .passwordEncoder(encoder());
    }

    /**
     * BCryptPasswordEncoder
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * DataSource
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(databaseProperties.getDriver());
        dataSource.setUrl(databaseProperties.getUrl());
        dataSource.setUsername(databaseProperties.getUsername());
        dataSource.setPassword(databaseProperties.getPassword());
        return dataSource;
    }
}
