package com.qa.opencart.pages;

import java.util.List;

import com.qa.opencart.utils.ElementUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultsPage {

    private WebDriver driver;
    private ElementUtils elementUtils;

    // Private By locators
    private By header = By.cssSelector("div#logo h1");
    private By productResults = By.cssSelector("div.caption a");

    // constructor
    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
    }

    public int getProductListCount(){
        int count = elementUtils.waitForElementsVisible(productResults, 10).size();
        System.out.println("Count: " + count);
        return count;
    }

    public ProductInfoPage selectProduct(String mainProdcutName){
       List <WebElement> searchList = elementUtils.waitForElementsVisible(productResults, 10);
       for(WebElement e:searchList){
           String text = e.getText();
           System.out.println("Text: " + text);
           if(text.equals(mainProdcutName)){
               e.click();
               break;
           }
       }
    
       return new ProductInfoPage(driver);
       
    }


}
