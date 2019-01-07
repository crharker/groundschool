@InstructorLessonPlan
Feature: LessonPlan endpoint tests

Background:
  Given I am a instructor user

  @acceptance
  Scenario: Instructor lists all lesson plans
    Given a lesson plan exists
    When I list all lesson plans
    Then I should receive a list of lesson plans

  @acceptance
  Scenario: Instructor retrieves a lesson plan
    Given a lesson plan exists
    When I retrieve a lesson plan
    Then I should receive the lesson plan

  @acceptance
  Scenario: Instructor updates a lesson plan
    Given a lesson plan exists
    When I update a lesson plan
    Then The lesson plan should be updated

  @acceptance
  Scenario: Instructor updates a lesson plan
    Given a lesson plan does not exist
    When I update a lesson plan
    Then I should receive a lesson plan not found error

  @acceptance
  Scenario: Instructor deletes a lesson plan
    Given a lesson plan exists
    When I delete a lesson plan
    Then The lesson plan should be deleted
