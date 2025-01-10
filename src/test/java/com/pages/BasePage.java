package com.pages;

import com.utilities.BrowserUtils;
import com.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "search")
    public WebElement searchMainBtn;

    public void navigateMainMenuItems(String menuName){
        menuName = menuName.substring(0,1).toUpperCase() + menuName.substring(1).toLowerCase();
        BrowserUtils.hover(Driver.getDriver().findElement(By.linkText(menuName)));
    }

}
