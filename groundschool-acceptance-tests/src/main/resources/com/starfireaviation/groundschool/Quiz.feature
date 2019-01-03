@quiz
Feature: Quiz endpoint tests

  @acceptance
  Scenario: Admin lists all quizzes
    Given I am a admin user
    And a quiz exists
    When I list all quizzes
    Then I should receive a list of quizzes

  @acceptance
  Scenario: Admin retrieves a quiz
    Given I am a admin user
    And a quiz exists
    When I retrieve a quiz
    Then I should receive the quiz

  @acceptance
  Scenario: Admin updates a quiz
    Given I am a admin user
    And a quiz exists
    When I update a quiz
    Then The quiz should be updated

  @acceptance
  Scenario: Admin updates a quiz
    Given I am a admin user
    And a quiz does not exist
    When I update a quiz
    Then I should receive a quiz not found error

  @acceptance
  Scenario: Admin deletes a quiz
    Given I am a admin user
    And a quiz exists
    When I delete a quiz
    Then The quiz should be deleted

  @acceptance
  Scenario: Instructor lists all quizzes
    Given I am a instructor user
    And a quiz exists
    When I list all quizzes
    Then I should receive a list of quizzes

  @acceptance
  Scenario: Instructor retrieves a quiz
    Given I am a instructor user
    And a quiz exists
    When I retrieve a quiz
    Then I should receive the quiz

  @acceptance
  Scenario: Instructor updates a quiz
    Given I am a instructor user
    And a quiz exists
    When I update a quiz
    Then The quiz should be updated

  @acceptance
  Scenario: Instructor updates a quiz
    Given I am a instructor user
    And a quiz does not exist
    When I update a quiz
    Then I should receive a quiz not found error

  @acceptance
  Scenario: Instructor deletes a quiz
    Given I am a instructor user
    And a quiz exists
    When I delete a quiz
    Then The quiz should be deleted

  @acceptance
  Scenario: Student lists all quizzes
    Given I am a student user
    And a quiz exists
    When I list all quizzes
    Then I should receive an operation not permitted error

  @acceptance
  Scenario: Student retrieves a quiz
    Given I am a student user
    And a quiz exists
    When I retrieve a quiz
    Then I should receive the quiz

  @acceptance
  Scenario: Student updates a quiz
    Given I am a student user
    When I update a quiz
    Then I should receive an operation not permitted error

  @acceptance
  Scenario: Student deletes a quiz
    Given I am a student user
    When I delete a quiz
    Then I should receive an operation not permitted error
