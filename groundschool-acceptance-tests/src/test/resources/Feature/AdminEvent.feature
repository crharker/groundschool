@AdminEvent
Feature: Event endpoint tests

Background:
  Given I am a admin user

  @acceptance
  Scenario: Admin lists all events
    Given an event exists
    When I list all events
    Then I should receive a list of events

  @acceptance
  Scenario: Admin lists all future events
    Given a future event exists
    When I list all future events
    Then I should receive a list of events

  @acceptance
  Scenario: Admin retrieves an event
    Given an event exists
    When I retrieve an event
    Then I should receive the event

  @acceptance
  Scenario: Admin updates an event
    Given an event exists
    When I update an event
    Then The event should be updated

  @acceptance
  Scenario: Admin updates an event
    Given an event does not exist
    When I update an event
    Then I should receive an event not found error

  @acceptance
  Scenario: Admin cancels an event
    Given an event exists
    When I cancel an event
    Then The event should be cancelled
