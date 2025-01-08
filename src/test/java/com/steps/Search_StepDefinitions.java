package com.steps;

import com.pages.SearchPage;
import com.utilities.BrowserUtils;
import com.utilities.ConfigurationReader;
import com.utilities.Driver;
import io.cucumber.java.en.*;

public class Search_StepDefinitions {

    SearchPage search = new SearchPage();

    @Given("I am on the main page")
    public void i_am_on_the_main_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @When("I click on search button")
    public void i_click_on_search_button() {
        search.searchBox.click();
    }

    @When("switch to Search page")
    public void switch_to_search_page() {
        String expectedTitle = "https://www.marvel.com/search";
        BrowserUtils.verifyTitle(expectedTitle);
    }

    @When("I search for {string} in {string} section")
    public void iSearchForInSection(String searchText, String searchSection) {
        search.searchBox.sendKeys(searchText);
        search.chooseSearchOptions(searchSection);
    }

    @Then("verify the number of results {int}")
    public void verifyTheNumberOfResults(int resultNo) {

    }

    @And("verify the number of pages {int}")
    public void verifyTheNumberOfPages(int resultPageNo) {
    }
}