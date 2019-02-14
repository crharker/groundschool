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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.starfireaviation.groundschool.properties.ApplicationProperties;
import com.starfireaviation.groundschool.properties.DatabaseProperties;

/**
 * WebSecurityConfiguration
 *
 * @author brianmichael
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties({
        ApplicationProperties.class,
        DatabaseProperties.class
})
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * UserDetailsService
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * DatabaseProperties
     */
    @Autowired
    private DatabaseProperties databaseProperties;

    /**
     * Configure authentication
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception when things go wrong
     */
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                // Allow non-logged in users to access
                .antMatchers(HttpMethod.POST, "/notifications/sms")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/users/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/users/username/**/available")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/events/rsvp/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/events/**/register/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/events/**/unregister/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/users/verify/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/users/logout")
                .permitAll()
                // Allow only Admin and Instructors to access
                .antMatchers(HttpMethod.POST, "/events/**/assign/**")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/events/**/unassign/**")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/events/**/start/**")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/events/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.PUT, "/events/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/events/**")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/events/**/start")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/events/**/complete")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers("/lessonplans/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/questions/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.PUT, "/questions/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/questions/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/questions/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/quizzes/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/quizzes/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.PUT, "/quizzes/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/quizzes/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/referencematerials/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.PUT, "/referencematerials/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/referencematerials/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.POST, "/statistics/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.PUT, "/statistics/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/statistics/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.DELETE, "/users/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                .antMatchers(HttpMethod.GET, "/users/*")
                .hasAnyAuthority("ADMIN", "INSTRUCTOR")
                // Everything else requires one of Admin, Instructor, or Student
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }

    /**
     * {@inheritDoc} Required implementation.
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    /**
     * CorsConfigurationSource
     *
     * @return CorsConfigurationSource
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
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
