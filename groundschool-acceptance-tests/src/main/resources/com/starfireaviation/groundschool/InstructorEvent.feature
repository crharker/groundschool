@InstructorEvent
Feature: Event endpoint tests

Background:
  Given I am a instructor user

  @acceptance
  Scenario: Instructor lists all events
    Given an event exists
    When I list all events
    Then I should receive a list of events

  @acceptance
  Scenario: Instructor lists all future events
    Given a future event exists
    When I list all future events
    Then I should receive a list of events

  @acceptance
  Scenario: Instructor retrieves an event
    Given an event exists
    When I retrieve an event
    Then I should receive the event

  @acceptance
  Scenario: Instructor updates an event
    Given an event exists
    When I update an event
    Then The event should be updated

  @acceptance
  Scenario: Instructor updates an event
    Given an event does not exist
    When I update an event
    Then I should receive an event not found error

  @acceptance
  Scenario: Instructor cancels an event
    Given an event exists
    When I cancel an event
    Then The event should be cancelled
