Feature: Search feature
  @search
  Scenario: Verify the search results and number of pages
    Given I am on the main page
    When I click on search button
    And switch to Search page
    When I search for "Avengers" in "COMICS section
    Then verify the number of pages 3583
    And verify the number of pages 180