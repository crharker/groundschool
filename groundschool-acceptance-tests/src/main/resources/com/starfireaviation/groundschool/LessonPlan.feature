@lessonPlan
Feature: LessonPlan endpoint tests

  @acceptance
  Scenario: Admin lists all lesson plans
    Given I am a admin user
    And a lesson plan exists
    When I list all lesson plans
    Then I should receive a list of lesson plans

  @acceptance
  Scenario: Admin retrieves a lesson plan
    Given I am a admin user
    And a lesson plan exists
    When I retrieve a lesson plan
    Then I should receive the lesson plan

  @acceptance
  Scenario: Admin updates a lesson plan
    Given I am a admin user
    And a lesson plan exists
    When I update a lesson plan
    Then The lesson plan should be updated

  @acceptance
  Scenario: Admin updates a lesson plan
    Given I am a admin user
    And a lesson plan does not exist
    When I update a lesson plan
    Then I should receive a lesson plan not found error

  @acceptance
  Scenario: Admin deletes a lesson plan
    Given I am a admin user
    And a lesson plan exists
    When I delete a lesson plan
    Then The lesson plan should be deleted

  @acceptance
  Scenario: Instructor lists all lesson plans
    Given I am a instructor user
    And a lesson plan exists
    When I list all lesson plans
    Then I should receive a list of lesson plans

  @acceptance
  Scenario: Instructor retrieves a lesson plan
    Given I am a instructor user
    And a lesson plan exists
    When I retrieve a lesson plan
    Then I should receive the lesson plan

  @acceptance
  Scenario: Instructor updates a lesson plan
    Given I am a instructor user
    And a lesson plan exists
    When I update a lesson plan
    Then The lesson plan should be updated

  @acceptance
  Scenario: Instructor updates a lesson plan
    Given I am a instructor user
    And a lesson plan does not exist
    When I update a lesson plan
    Then I should receive a lesson plan not found error

  @acceptance
  Scenario: Instructor deletes a lesson plan
    Given I am a instructor user
    And a lesson plan exists
    When I delete a lesson plan
    Then The lesson plan should be deleted

  @acceptance
  Scenario: Student lists all lesson plans
    Given I am a student user
    And a lesson plan exists
    When I list all lesson plans
    Then I should receive a list of lesson plan summaries

  @acceptance
  Scenario: Student retrieves a lesson plan
    Given I am a student user
    And a lesson plan exists
    When I retrieve a lesson plan
    Then I should receive the lesson plan summary

  @acceptance
  Scenario: Student updates a lesson plan
    Given I am a student user
    When I update a lesson plan
    Then I should receive an operation not permitted error

  @acceptance
  Scenario: Student deletes a lesson plan
    Given I am a student user
    When I delete a lesson plan
    Then I should receive an operation not permitted error
