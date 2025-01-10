Feature: API Task-1 Characters endpoint
  @api
  Scenario: Ensure that every character record has all the JSON properties: id,name,description,modified,thumbnail,resourceURI,comics,series,stories,events,urls
    When send "GET" request to "/characters" endpoint
    Then status code should be 200
    And all records should have the same schema