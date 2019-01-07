@StudentQuestion
Feature: Question endpoint tests

Background:
  Given I am a student user

  @acceptance
  Scenario: Student lists all questions
    Given a question exists
    When I list all questions
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student retrieves a question
    Given a question exists
    When I retrieve a question
    Then I should receive the question

  @acceptance
  Scenario: Student updates a question
    When I update a question
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student deletes a question
    When I delete a question
    Then I should receive a operation not permitted error
