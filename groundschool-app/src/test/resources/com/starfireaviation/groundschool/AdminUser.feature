@AdminUser
Feature: User endpoint tests

Background:
  Given I am a admin user

  @acceptance
  Scenario: Admin lists all users
    Given a user exists
    When I list all users
    Then I should receive a list of users

  @acceptance
  Scenario: Admin lists all members
    Given a member exists
    When I list all members
    Then I should receive a list of members

  @acceptance
  Scenario: Admin retrieves a user
    Given a user exists
    When I retrieve a user
    Then I should receive the user details

  @acceptance
  Scenario: Admin signs up
    When I signup
    Then I should receive the user details

  @acceptance
  Scenario: Admin logs in
    When I login
    Then I should receive an authentication token
