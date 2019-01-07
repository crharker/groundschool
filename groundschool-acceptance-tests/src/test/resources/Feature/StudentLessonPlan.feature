@StudentLessonPlan
Feature: LessonPlan endpoint tests

Background:
  Given I am a student user

  @acceptance
  Scenario: Student lists all lesson plans
    Given a lesson plan exists
    When I list all lesson plans
    Then I should receive a list of lesson plan summaries

  @acceptance
  Scenario: Student retrieves a lesson plan
    Given a lesson plan exists
    When I retrieve a lesson plan
    Then I should receive the lesson plan summary

  @acceptance
  Scenario: Student updates a lesson plan
    When I update a lesson plan
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student deletes a lesson plan
    When I delete a lesson plan
    Then I should receive a operation not permitted error
