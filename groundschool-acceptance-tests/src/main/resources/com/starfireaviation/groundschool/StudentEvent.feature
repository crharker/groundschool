@StudentEvent
Feature: Student interaction with events tests

Background:
  Given I am a student user

  @acceptance
  Scenario: Student attempts to create an event
    Given I want to create an event
    When I create an event
    Then I should receive a access denied error

  @acceptance
  Scenario: Student lists all events
    Given an event exists
    When I list all events
    Then I should receive a list of event summaries

  @acceptance
  Scenario: Student lists all future events
    Given a future event exists
    When I list all future events
    Then I should receive a list of event summaries

  @acceptance
  Scenario: Student retrieves an event
    Given an event exists
    When I retrieve an event
    Then I should receive the event summary

  @acceptance
  Scenario: Student updates an event
    When I update an event
    Then I should receive a access denied error

  @acceptance
  Scenario: Student cancels an event
    When I cancel an event
    Then I should receive a access denied error
