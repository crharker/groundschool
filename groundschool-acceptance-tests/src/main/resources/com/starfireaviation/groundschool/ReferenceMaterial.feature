@referenceMaterial
Feature: ReferenceMaterial endpoint tests

  @acceptance
  Scenario: Admin lists all reference material
    Given I am a admin user
    And a reference material exists
    When I list all reference material
    Then I should receive a list of reference material

  @acceptance
  Scenario: Admin retrieves a reference material
    Given I am a admin user
    And a reference material exists
    When I retrieve a reference material
    Then I should receive the reference material

  @acceptance
  Scenario: Admin updates a reference material
    Given I am a admin user
    And a reference material exists
    When I update a reference material
    Then The reference material should be updated

  @acceptance
  Scenario: Admin updates a reference material
    Given I am a admin user
    And a reference material does not exist
    When I update a reference material
    Then I should receive a reference material not found error

  @acceptance
  Scenario: Admin deletes a reference material
    Given I am a admin user
    And a reference material exists
    When I delete a reference material
    Then The reference material should be deleted

  @acceptance
  Scenario: Instructor lists all reference material
    Given I am a instructor user
    And a reference material exists
    When I list all reference material
    Then I should receive a list of reference material

  @acceptance
  Scenario: Instructor retrieves a reference material
    Given I am a instructor user
    And a reference material exists
    When I retrieve a reference material
    Then I should receive the reference material

  @acceptance
  Scenario: Instructor updates a reference material
    Given I am a instructor user
    And a reference material exists
    When I update a reference material
    Then The reference material should be updated

  @acceptance
  Scenario: Instructor updates a reference material
    Given I am a instructor user
    And a reference material does not exist
    When I update a reference material
    Then I should receive a reference material not found error

  @acceptance
  Scenario: Instructor deletes a reference material
    Given I am a instructor user
    And a reference material exists
    When I delete a reference material
    Then The reference material should be deleted

  @acceptance
  Scenario: Student lists all reference material
    Given I am a student user
    And a reference material exists
    When I list all reference material
    Then I should receive a list of reference material

  @acceptance
  Scenario: Student retrieves a reference material
    Given I am a student user
    And a reference material exists
    When I retrieve a reference material
    Then I should receive the reference material

  @acceptance
  Scenario: Student updates a reference material
    Given I am a student user
    When I update a reference material
    Then I should receive an operation not permitted error

  @acceptance
  Scenario: Student deletes a reference material
    Given I am a student user
    When I delete a reference material
    Then I should receive an operation not permitted error
