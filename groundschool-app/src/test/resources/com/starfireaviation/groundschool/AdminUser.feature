@AdminUser
Feature: User endpoint tests

Background:
  Given I am a admin user

  @acceptance
  Scenario: Admin invites a new user
    Given a user does not exist
    When I invite a new user
    Then An email is sent to the user

  @acceptance
  Scenario: Admin invites an existing user
    Given a user does exist
    When I invite a new user
    Then An email is not sent to the user

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
    Given I want to signup
    When I signup
    Then I should receive the user details

  @acceptance
  Scenario Outline: Student verifies user settings
    When I verify my <setting> setting
    Then My <setting> should be verified

  Examples:
    | setting |
    | email |
    | sms   |
    | slack |

  @acceptance
  Scenario: Admin logs in
    When I login
    Then I should receive an authentication token
