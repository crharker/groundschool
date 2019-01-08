@InstructorUser
Feature: User endpoint tests

Background:
  Given I am a instructor user

  @acceptance
  Scenario: Instructor lists all users
    Given a user exists
    When I list all users
    Then I should receive a list of users

  @acceptance
  Scenario: Instructor lists all members
    Given a member exists
    When I list all members
    Then I should receive a list of members

  @acceptance
  Scenario: Instructor retrieves a user
    Given a user exists
    When I retrieve a user
    Then I should receive the user details

  @acceptance
  Scenario: Instructor signs up
    When I signup
    Then I should receive the user details

  @acceptance
  Scenario: Instructor logs in
    When I login
    Then I should receive an authentication token
