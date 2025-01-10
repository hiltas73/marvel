Feature: Shop task
  Scenario: Shop 2 Marvel Logo Pins
    Given I am on the main page
    When navigate to "SHOP" page
    And search for "Marvel Logo"
    And filter by Age "Kids"
    And filter by Gender "Men"
    And sort by "Price (low to high)"
    And click on Wish List
    Then the item should be added to my wish list
