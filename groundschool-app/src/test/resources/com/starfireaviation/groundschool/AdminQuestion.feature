@AdminQuestion
Feature: Question endpoint tests

Background:
  Given I am a admin user

  @acceptance
  Scenario: Admin lists all questions
    Given a question exists
    When I list all questions
    Then I should receive a list of questions

  @acceptance
  Scenario: Admin retrieves a question
    Given a question exists
    When I retrieve a question
    Then I should receive the question

  @acceptance
  Scenario: Admin updates a question
    Given a question exists
    When I update a question
    Then The question should be updated

  @acceptance
  Scenario: Admin updates a question
    Given a question does not exist
    When I update a question
    Then I should receive a question not found error

  @acceptance
  Scenario: Admin deletes a question
    Given a question exists
    When I delete a question
    Then The question should be deleted
