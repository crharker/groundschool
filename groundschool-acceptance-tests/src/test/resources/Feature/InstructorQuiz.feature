@InstructorQuiz
Feature: Quiz endpoint tests

Background:
  Given I am a instructor user

  @acceptance
  Scenario: Instructor lists all quizzes
    Given a quiz exists
    When I list all quizzes
    Then I should receive a list of quizzes

  @acceptance
  Scenario: Instructor retrieves a quiz
    Given a quiz exists
    When I retrieve a quiz
    Then I should receive the quiz

  @acceptance
  Scenario: Instructor updates a quiz
    Given a quiz exists
    When I update a quiz
    Then The quiz should be updated

  @acceptance
  Scenario: Instructor updates a quiz
    Given a quiz does not exist
    When I update a quiz
    Then I should receive a quiz not found error

  @acceptance
  Scenario: Instructor deletes a quiz
    Given a quiz exists
    When I delete a quiz
    Then The quiz should be deleted
