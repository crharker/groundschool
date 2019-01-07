@InstructorStatistic
Feature: Statistic endpoint tests

Background:
  Given I am a instructor user

  @acceptance
  Scenario: Instructor lists all statistics
    Given a statistic exists
    When I list all statistics
    Then I should receive a list of statistics

  @acceptance
  Scenario: Instructor retrieves a statistic
    Given a statistic exists
    When I retrieve a statistic
    Then I should receive the statistic

  @acceptance
  Scenario: Instructor updates a statistic
    Given a statistic exists
    When I update a statistic
    Then The statistic should be updated

  @acceptance
  Scenario: Instructor updates a statistic
    Given a statistic does not exist
    When I update a statistic
    Then I should receive a statistic not found error

  @acceptance
  Scenario: Instructor deletes a statistic
    Given a statistic exists
    When I delete a statistic
    Then The statistic should be deleted
