@user
Feature: User endpoint tests

  @acceptance
  Scenario: Admin lists all users
    Given I am a admin user
    And a user exists
    When I list all users
    Then I should receive a list of users

  @acceptance
  Scenario: Admin lists all members
    Given I am a admin user
    And a member exists
    When I list all members
    Then I should receive a list of members

  @acceptance
  Scenario: Admin retrieves a user
    Given I am a admin user
    And a user exists
    When I retrieve a user
    Then I should receive the user details

  @acceptance
  Scenario: Admin signs up a user
    Given I am a admin user
    When I signup a user
    Then I should receive the user details

  @acceptance
  Scenario: Admin logs in
    Given I am a admin user
    When I login
    Then I should receive an authentication token

  @acceptance
  Scenario: Instructor lists all users
    Given I am a instructor user
    And a user exists
    When I list all users
    Then I should receive a list of users

  @acceptance
  Scenario: Instructor lists all members
    Given I am a instructor user
    And a member exists
    When I list all members
    Then I should receive a list of members

  @acceptance
  Scenario: Instructor retrieves a user
    Given I am a instructor user
    And a user exists
    When I retrieve a user
    Then I should receive the user details

  @acceptance
  Scenario: Instructor signs up a user
    Given I am a instructor user
    When I signup a user
    Then I should receive the user details

  @acceptance
  Scenario: Instructor logs in
    Given I am a instructor user
    When I login
    Then I should receive an authentication token

  @acceptance
  Scenario: Student lists all users
    Given I am a student user
    When I list all users
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student lists all members
    Given I am a student user
    When I list all members
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student retrieves a user
    Given I am a student user
    When I retrieve a user
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student retrieves their details
    Given I am a student user
    When I retrieve my details
    Then I should receive the user details

  @acceptance
  Scenario: Student signs up another user
    Given I am a student user
    When I signup a user
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student signs up
    Given I am a student user
    When I signup myself
    Then I should receive the user details

  @acceptance
  Scenario: Student logs in
    Given I am a student user
    When I login
    Then I should receive an authentication token
