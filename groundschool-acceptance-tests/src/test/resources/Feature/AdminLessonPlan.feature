@AdminLessonPlan
Feature: LessonPlan endpoint tests

Background:
  Given I am a admin user

  @acceptance
  Scenario: Admin lists all lesson plans
    Given a lesson plan exists
    When I list all lesson plans
    Then I should receive a list of lesson plans

  @acceptance
  Scenario: Admin retrieves a lesson plan
    Given a lesson plan exists
    When I retrieve a lesson plan
    Then I should receive the lesson plan

  @acceptance
  Scenario: Admin updates a lesson plan
    Given a lesson plan exists
    When I update a lesson plan
    Then The lesson plan should be updated

  @acceptance
  Scenario: Admin updates a lesson plan
    Given a lesson plan does not exist
    When I update a lesson plan
    Then I should receive a lesson plan not found error

  @acceptance
  Scenario: Admin deletes a lesson plan
    Given a lesson plan exists
    When I delete a lesson plan
    Then The lesson plan should be deleted
