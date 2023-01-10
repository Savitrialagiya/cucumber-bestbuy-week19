Feature: CRUD test of Product
  As a user I want to test Product in best buy application

  Scenario Outline: CRUD Test
    When I create product with name "<name>" type "<type>" price"<price>" shipping"<shipping>" upc"<upc>" description"<description>" manufacture"<manufacture>" model"<model>" url"<url>" image"<image>"
    And I verify user created
    And I update product with name "<name>" type "<type>" price"<price>" shipping"<shipping>" upc"<upc>" description"<description>" manufacture"<manufacture>" model"<model>" url"<url>" image"<image>"
    And I check product updated successfully
    And I delete product
    Then I verify that product deleted successfully
    Examples:
      | name    | type      | price | shipping | upc    | description                              | manufacture | model     | url                    | image                    |
      | Battery | Chargable | 18    | 52       | 012345 | Compatible with select electronic device | Duracell    | MN2400B4Z | http://www.bestbuy.com | http://img.bbystatic.com |