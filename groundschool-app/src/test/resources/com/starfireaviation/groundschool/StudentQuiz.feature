@StudentQuiz
Feature: Quiz endpoint tests

Background:
  Given I am a student user

  @acceptance
  Scenario: Student lists all quizzes
    Given a quiz exists
    When I list all quizzes
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student retrieves a quiz
    Given a quiz exists
    When I retrieve a quiz
    Then I should receive the quiz

  @acceptance
  Scenario: Student updates a quiz
    When I update a quiz
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student deletes a quiz
    When I delete a quiz
    Then I should receive a operation not permitted error
