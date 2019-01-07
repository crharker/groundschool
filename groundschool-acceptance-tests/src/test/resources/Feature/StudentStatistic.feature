@StudentStatistic
Feature: Statistic endpoint tests

Background:
  Given I am a student user

  @acceptance
  Scenario: Student lists all statistics
    Given a statistic exists
    When I list all statistics
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student retrieves a statistic
    Given a statistic exists
    When I retrieve a statistic
    Then I should receive the statistic

  @acceptance
  Scenario: Student updates a statistic
    When I update a statistic
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student deletes a statistic
    When I delete a statistic
    Then I should receive a operation not permitted error
