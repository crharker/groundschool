@InstructorReferenceMaterial
Feature: ReferenceMaterial endpoint tests

Background:
  Given I am a instructor user

  @acceptance
  Scenario: Instructor lists all reference material
    Given a reference material exists
    When I list all reference material
    Then I should receive a list of reference material

  @acceptance
  Scenario: Instructor retrieves a reference material
    Given a reference material exists
    When I retrieve a reference material
    Then I should receive the reference material

  @acceptance
  Scenario: Instructor updates a reference material
    Given a reference material exists
    When I update a reference material
    Then The reference material should be updated

  @acceptance
  Scenario: Instructor updates a reference material
    Given a reference material does not exist
    When I update a reference material
    Then I should receive a reference material not found error

  @acceptance
  Scenario: Instructor deletes a reference material
    Given a reference material exists
    When I delete a reference material
    Then The reference material should be deleted
