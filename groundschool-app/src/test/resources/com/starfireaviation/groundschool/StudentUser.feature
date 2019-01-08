@StudentUser
Feature: Student user endpoint tests

Background:
  Given I am a student user

  @acceptance
  Scenario: Student lists all users
    When I list all users
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student lists all members
    When I list all members
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student retrieves a user
    When I retrieve a user
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student retrieves their details
    When I retrieve my details
    Then I should receive the user details

  @acceptance
  Scenario: Student signs up
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
  Scenario: Student logs in
    When I login
    Then I should receive an authentication token
