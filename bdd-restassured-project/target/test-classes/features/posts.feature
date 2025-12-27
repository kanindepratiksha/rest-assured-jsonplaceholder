Feature: Posts API

  Scenario: Create a post
    When I create a post with title "foo" and body "bar" and userId 1
    Then the post response status code should be 201
    And I store the post id

  Scenario: Update the created post
    When I update the created post title to "updated title"
    Then the post response status code should be 200

  Scenario: Delete the created post
    When I delete the created post
    Then the response status code should be 200 or 204
