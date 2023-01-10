Feature: Testing a different request on store

  Scenario: Verify that store is created
    When I create a store
    Then I must get a valid response 201

  Scenario: Verify that I get created store with storeId
    When I get store with storeId
    Then I must get valid response 200

  Scenario: Verify that I update the store with storeId
    When I update store with storeId
    Then I must get valid response 200

  Scenario: Verify that I can delete the store
    When I delete store with storeId
    Then  I check store is deleted