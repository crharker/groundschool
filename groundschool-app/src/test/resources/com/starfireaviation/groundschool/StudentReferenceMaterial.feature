@StudentReferenceMaterial
Feature: ReferenceMaterial endpoint tests

Background:
  Given I am a student user

  @acceptance
  Scenario: Student lists all reference material
    Given a reference material exists
    When I list all reference material
    Then I should receive a list of reference material

  @acceptance
  Scenario: Student retrieves a reference material
    Given a reference material exists
    When I retrieve a reference material
    Then I should receive the reference material

  @acceptance
  Scenario: Student updates a reference material
    When I update a reference material
    Then I should receive a operation not permitted error

  @acceptance
  Scenario: Student deletes a reference material
    When I delete a reference material
    Then I should receive a operation not permitted error
