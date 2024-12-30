@api
Feature: Product Controller Test
  As a user
  I want to test the product controller API endpoints
  So that I can ensure the CRUD operations work correctly

  @product
  Scenario: Create, retrieve, update, and delete a product
    Given the endpoint "/api/myaccount/products"
    And I create a new product
    When I retrieve the same product by its ID
    Then I validate the response body and ensure the status code is 200
    When I update the same product by its ID
    Then I validate the updated product details in the response
    When I delete the same product by its ID
    Then I confirm the product no longer exists in the system
