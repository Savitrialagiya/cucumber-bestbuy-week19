Feature: Testing a different request on product


  Scenario: Verify that product is created
    When As a User I create a product
    Then User must get back a valid response 201

  Scenario: Verify that I can get created product with productId
    When I get product with productId
    Then  I must get back a valid response 200

  Scenario: Verify that the product is updated with productId
    When I update product with productId
    Then I must get back a again valid response 200

  Scenario: Verify that the created product is deleted
    When I delete product with productId
   Then  I check product is deleted