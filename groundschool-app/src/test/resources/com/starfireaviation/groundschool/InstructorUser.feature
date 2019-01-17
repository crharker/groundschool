@InstructorUser
Feature: User endpoint tests

Background:
  Given I am a instructor user

  @acceptance
  Scenario: Instructor invites a new user
    Given a user does not exist
    When I invite a new user
    Then An email is sent to the user

  @acceptance
  Scenario: Instructor invites an existing user
    Given a user does exist
    When I invite a new user
    Then An email is not sent to the user

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
  Scenario Outline: Student verifies user settings
    When I verify my <setting> setting
    Then My <setting> should be verified

  Examples:
    | setting |
    | email |
    | sms   |
    | slack |

  @acceptance
  Scenario: Instructor logs in
    When I login
    Then I should receive an authentication token
