Feature: Users API

  Scenario: Get all users
    When I send GET request to "/users"
    Then the response status code should be 200
    And the response should contain at least 1 user

  Scenario: Get user by id
    When I send GET request to "/users/1"
    Then the response status code should be 200
    And the field "id" should be 1

  Scenario: User not found
    When I send GET request to "/users/9999"
    Then the response status code should be 404
