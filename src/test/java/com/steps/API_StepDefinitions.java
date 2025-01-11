package com.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.utilities.ConfigurationReader;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.given;

public class API_StepDefinitions {

    Response response;

    List<String> keyList = Arrays.asList(
            "id", "name", "description", "modified", "thumbnail",
            "resourceURI", "comics", "series", "stories", "events", "urls"
    );
    //HashSet<String> REQUIRED_KEYS = new HashSet<>(Arrays.asList(keyList));

    @When("send {string} request to {string} endpoint")
    public void send_request_to_endpoint(String httpMethod, String endpoint) {

        response = given().accept(ContentType.JSON).
                and().queryParam("ts", ConfigurationReader.getProperty("timeStamp")).
                and().queryParam("apikey", ConfigurationReader.getProperty("publicKey")).
                and().queryParam("hash", ConfigurationReader.getProperty("hashValue")).
                when().
                get(ConfigurationReader.getProperty("marvel.baseUri") + endpoint).then().extract().response();

        //response.prettyPrint();


    }

    @Then("status code should be {int}")
    public void status_code_should_be(Integer expectedStatusCode) {
        System.out.println("expectedStatusCode = " + expectedStatusCode);
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals("Status Code Test", Integer.parseInt(expectedStatusCode.toString()), response.statusCode());
    }
/*
    @Then("all records should have the same schema")
    public void all_records_should_have_the_same_schema() throws JsonProcessingException {

        int offset = 0;
        int limit = 100; // Adjust limit based on API documentation
        boolean hasMoreData = true;

        while (hasMoreData) {
            response = given().accept(ContentType.JSON).
                    queryParam("ts", ConfigurationReader.getProperty("timeStamp")).
                    queryParam("apikey", ConfigurationReader.getProperty("publicKey")).
                    queryParam("hash", ConfigurationReader.getProperty("hashValue")).
                    queryParam("offset", offset).
                    queryParam("limit", limit).
                    when().
                    get(ConfigurationReader.getProperty("marvel.baseUri") + "/characters").
                    then().
                    extract().response();

            if (response.getStatusCode() == 200) {
                // Process the response
                System.out.println("Fetched page with offset: " + offset);

                // Here you can parse the response and perform your assertions
                int totalCharacters = response.jsonPath().getInt("data.total");
                int currentCount = response.jsonPath().getList("data.results").size();

                // validate each character in the response
                List<Map<String, Object>> characters = response.jsonPath().getList("data.results");
                for (Map<String, Object> character : characters) {
                    HashSet<String> characterKeys = new HashSet<>(character.keySet());

                    // Assert that all required keys are present
                    Assert.assertTrue("Character validation failed. Missing keys: " +
                                    REQUIRED_KEYS.stream().filter(key -> !characterKeys.contains(key)).toList(),
                            characterKeys.containsAll(REQUIRED_KEYS));

                    System.out.println("Character validated successfully: " + character.get("name"));

                    // Check if we have fetched all characters
                    if (offset + currentCount >= totalCharacters) {
                        hasMoreData = false; // No more data to fetch
                    }

                    // Increment offset for the next request
                    offset += limit;
                }
            } else {
                System.out.println("Error fetching data: " + response.getStatusCode());
                hasMoreData = false; // Exit loop on error
            }
        }

    }
*/
    @And("all response result keys match with the following")
    public void allResponseResultKeysMatchWithTheFollowing(List<String> expectedKeyList) {

        int offset = 0;     // to start from the first response character
        int limit = 100;    // Adjust limit based on API documentation

        response = given().accept(ContentType.JSON).
                queryParam("ts", ConfigurationReader.getProperty("timeStamp")).
                queryParam("apikey", ConfigurationReader.getProperty("publicKey")).
                queryParam("hash", ConfigurationReader.getProperty("hashValue")).
                queryParam("offset", offset).
                queryParam("limit", limit).
                when().
                get(ConfigurationReader.getProperty("marvel.baseUri") + "/characters").
                then().
                extract().response();

        // Process the response
        //System.out.println("Fetched page with offset: " + offset);

        // Here you can parse the response and calculate request times based on total characters
        int totalCharacters = response.jsonPath().getInt("data.total");
        int currentCount = response.jsonPath().getList("data.results").size();

        int requestRepeatTime = totalCharacters / limit;
        System.out.println("requestRepeatTime = " + requestRepeatTime);

        for (int i = 1; i <= requestRepeatTime; i++) {
            response = given().accept(ContentType.JSON).
                    queryParam("ts", ConfigurationReader.getProperty("timeStamp")).
                    queryParam("apikey", ConfigurationReader.getProperty("publicKey")).
                    queryParam("hash", ConfigurationReader.getProperty("hashValue")).
                    queryParam("offset", offset).
                    queryParam("limit", limit).
                when().
                    get(ConfigurationReader.getProperty("marvel.baseUri") + "/characters").
                then().
                    extract().response();
            // to start from the next 100 characters
            offset += limit;
            System.out.println("request/response time = " + i);

            // validate each character in the response
            List<Map<String, Object>> characters = response.jsonPath().getList("data.results");
            for (Map<String, Object> character : characters) {
                List<String> characterKeys = new ArrayList<>(character.keySet());
                System.out.println("characterKeys = " + characterKeys);
                Assert.assertEquals(keyList,characterKeys);
            }


            System.out.println("======================================================");

        }

    }
}


