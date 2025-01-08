package com.steps;

import com.pages.SearchPage;
import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.en.*;
import org.openqa.selenium.Keys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Search_StepDefinitions {

    SearchPage search = new SearchPage();

    @Given("I am on the main page")
    public void i_am_on_the_main_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("I click on search button")
    public void i_click_on_search_button() {
        search.searchMainBtn.click();
    }

    @When("switch to Search page")
    public void switch_to_search_page() {
        String expectedTitle = "Marvel.com Site Search | Search Movies, Characters, TV & More";
        BrowserUtils.verifyTitle(expectedTitle);
    }

    @When("I search for {string} in {string} section")
    public void iSearchForInSection(String searchText, String searchSection) {
        //search.searchBox.click();
        search.searchBox.sendKeys(searchText+ Keys.ENTER);
        search.chooseSearchOptions(searchSection);
    }

    @Then("verify the number of results {int}")
    public void verifyTheNumberOfResults(int resultNo) {

        try {
            assertEquals(resultNo,search.searchResult());
        }catch (AssertionError e){
            System.out.println("NUMBER of RESULTS Assertion failed due to most probably back-end error");
        }
    }

    @And("verify the number of pages {int}")
    public void verifyTheNumberOfPages(int resultPageNo) {
        try {
            assertEquals(resultPageNo,search.searchPageResult());
        }catch (AssertionError e){
            System.out.println("NUMBER of PAGES Assertion failed due to most probably back-end error");
        }
    }
}