package com.pages;

import com.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{

    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement searchBox;

    public int searchResult(){
        String resultText = Driver.getDriver().findElement(By.xpath("//div[@class='search-list__results-count']")).getText();
        //System.out.println("resultText = " + resultText);
        String[] str = resultText.split("");
        String s = str[0];
        return Integer.parseInt(s);
    }

    public int searchPageResult(){
        String resultText = Driver.getDriver().findElement(By.xpath("//li[@rel='next']/preceding-sibling::li[1]")).getText();
        System.out.println("resultText = " + resultText);
        return Integer.parseInt(resultText);
    }

    public void chooseSearchOptions(String searchOpt){
        String searchOption = searchOpt.toLowerCase();
        Driver.getDriver().findElement(By.xpath("//a[.='"+ searchOption +"']")).click();
    }

}
