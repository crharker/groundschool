@event
Feature: Event endpoint tests

  @acceptance
  Scenario: Admin lists all events
    Given I am a admin user
    And an event exists
    When I list all events
    Then I should receive a list of events

  @acceptance
  Scenario: Admin lists all future events
    Given I am a admin user
    And a future event exists
    When I list all future events
    Then I should receive a list of events

  @acceptance
  Scenario: Admin retrieves an event
    Given I am a admin user
    And an event exists
    When I retrieve an event
    Then I should receive the event

  @acceptance
  Scenario: Admin updates an event
    Given I am a admin user
    And an event exists
    When I update an event
    Then The event should be updated

  @acceptance
  Scenario: Admin updates an event
    Given I am a admin user
    And an event does not exist
    When I update an event
    Then I should receive an event not found error

  @acceptance
  Scenario: Admin cancels an event
    Given I am a admin user
    And an event exists
    When I cancel an event
    Then The event should be cancelled

  @acceptance
  Scenario: Instructor lists all events
    Given I am a instructor user
    And an event exists
    When I list all events
    Then I should receive a list of events

  @acceptance
  Scenario: Instructor lists all future events
    Given I am a instructor user
    And a future event exists
    When I list all future events
    Then I should receive a list of events

  @acceptance
  Scenario: Instructor retrieves an event
    Given I am a instructor user
    And an event exists
    When I retrieve an event
    Then I should receive the event

  @acceptance
  Scenario: Instructor updates an event
    Given I am a instructor user
    And an event exists
    When I update an event
    Then The event should be updated

  @acceptance
  Scenario: Instructor updates an event
    Given I am a instructor user
    And an event does not exist
    When I update an event
    Then I should receive an event not found error

  @acceptance
  Scenario: Instructor cancels an event
    Given I am a instructor user
    And an event exists
    When I cancel an event
    Then The event should be cancelled

  @acceptance
  Scenario: Student lists all events
    Given I am a student user
    And an event exists
    When I list all events
    Then I should receive a list of event summaries

  @acceptance
  Scenario: Student lists all future events
    Given I am a student user
    And a future event exists
    When I list all future events
    Then I should receive a list of event summaries

  @acceptance
  Scenario: Student retrieves an event
    Given I am a student user
    And an event exists
    When I retrieve an event
    Then I should receive the event summary

  @acceptance
  Scenario: Student updates an event
    Given I am a student user
    When I update an event
    Then I should receive an operation not permitted error

  @acceptance
  Scenario: Student cancels an event
    Given I am a student user
    When I cancel an event
    Then I should receive an operation not permitted error
