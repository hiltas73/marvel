package com.pages;

import com.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{

    @FindBy(xpath = "//*[text()='Search']")
    public WebElement searchBox;

    public void chooseSearchOptions(String searchOpt){
        String searchOption = searchOpt.toLowerCase();
        Driver.getDriver().findElement(By.xpath("//a[.='"+ searchOption +"']")).click();
    }

}
