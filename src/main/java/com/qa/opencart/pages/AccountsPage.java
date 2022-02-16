package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountsPage {
    //private webdriver
    private WebDriver driver;
    private ElementUtils elementUtils;

    //Private By locators
    private By header = By.cssSelector("div#logo a");
    private By sections = By.cssSelector("div#content h2");
    private By search = By.cssSelector("div#search input");
    private By searchIcon = By.cssSelector("div#search button");
    private By logoutLink = By.linkText("Logout");

    //constructor

    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
    }

    //action methods
    public String getAccountPageTitle() {
        return elementUtils.doGetPageTitleIs(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_INT_TIMEOUT);
    }

    public String getAccountPageUrl() {
        return elementUtils.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, Constants.DEFAULT_INT_TIMEOUT);
    }

    public boolean isAccountPageHeaderExist(){
        return elementUtils.doIsDisplayed(header);
    }

    public String getAccountPageHeader(){
        return elementUtils.doGetText(header);
    }

    public boolean isLogoutLinkExist(){
       return elementUtils.doIsDisplayed(logoutLink);
    }

    public boolean doLogout(){
        if(isLogoutLinkExist()){
            elementUtils.doClick(logoutLink);
            return true;
        } return false;
    }
    
    public List <String> getAccountPgeSections(){
       List <WebElement> sectionList = elementUtils.waitForElementsPresence(sections, Constants.DEFAULT_INT_TIMEOUT);
       List <String> sectionValueList = new ArrayList<String>();
         for (WebElement section : sectionList){
              String value  = section.getText();
                sectionValueList.add(value);
         } return sectionValueList;
    }

    public boolean isSearchExists(){
        return elementUtils.doIsDisplayed(search);
    }

    public boolean isSearchIconExists(){
        return elementUtils.doIsDisplayed(searchIcon);
    }

    public ResultsPage doSearch(String productName){
        if(isSearchExists()){
            elementUtils.doSendKeys(search, productName);
            elementUtils.doClick(searchIcon);
            
        }
        return new ResultsPage(driver);
    }
}
