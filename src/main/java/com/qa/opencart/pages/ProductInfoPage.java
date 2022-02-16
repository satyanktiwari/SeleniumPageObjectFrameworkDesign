package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.qa.opencart.utils.ElementUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtils elementUtils;
    private Map <String, String> productMap;

    //private By locators
    private By productHeaderName = By.cssSelector("div#content h1");
    private By productImages = By.cssSelector("ul.thumbnails img");
    private By productMetadata =By.cssSelector("div#content .list-unstyled:nth-of-type(1) li");
    private By productPricedata = By.cssSelector("div#content .list-unstyled:nth-of-type(2) li");
    private By prodcutQuantitiy = By.xpath("//input[@name='quantity']");
    private By productAddToCart = By.id("button-cart");

    //constructor
    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
    }

    //action methods
    public String getProductHeaderName() {
        return elementUtils.doGetText(productHeaderName);
    }

    public int getProductImageCount(){
        return elementUtils.waitForElementsPresence(productImages, 10).size();
    }

    //HasMap -> doesn't maintain order
    //LinkedHashMap -> maintains order in which the keys were inserted
    //TreeMap -> maintains sorted order wrt keys

    public Map<String, String> getProdcutInfo(){
        productMap = new TreeMap<String, String>();
        productMap.put("name", getProductHeaderName());
        productMap.put("imageCount", String.valueOf(getProductImageCount()));
        getProductMetaData();
        getProductPriceData();
        return productMap;

    }

    public void getProductMetaData(){
        List<WebElement> metaDataList = elementUtils.getElements(productMetadata);
        for(WebElement e:metaDataList){
            String text = e.getText();
            String[] metaData = text.split(":");
            String key = metaData[0];
            String value = metaData[1];
            productMap.put(key, value);

        }
    }

    private void getProductPriceData(){
        List<WebElement> metaPriceList = elementUtils.getElements(productPricedata);
        for(WebElement e:metaPriceList){
            String price = metaPriceList.get(0).getText().trim();
            String extraPrice = metaPriceList.get(1).getText().trim();
            productMap.put("Price", price);
            productMap.put("ExtraPrice", extraPrice);

        }
    }

}
