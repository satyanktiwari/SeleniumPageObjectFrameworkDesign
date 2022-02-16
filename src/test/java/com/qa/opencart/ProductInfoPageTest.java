package com.qa.opencart;

import java.util.Map;

import com.qa.opencart.utils.Constants;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class ProductInfoPageTest extends BaseTest{
    @BeforeClass
    public void setUp(){
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void productHeaderTest(){
        resultsPage =accPage.doSearch("MacBook");
        productInfoPage = resultsPage.selectProduct("MacBook Pro");
        // productInfoPage.getProductHeaderName();
        Assert.assertEquals(productInfoPage.getProductHeaderName(), "MacBook Pro");

    }
    
    @DataProvider
    public Object [][] provideImageData(){

        return new Object[][]{
            {"MacBook","MacBook Pro",Constants.MACBOOKPRO_IMAGES_COUNT},
            {"MacBook","MacBook",Constants.MACBOOK_IMAGES_COUNT},
            {"imac","iMac",Constants.IMAC_IMAGES_COUNT}
        };
    }

    @Test(dataProvider = "provideImageData")
    public void productImageCount(String productName, String mainProductName, int expectedCount){
        resultsPage = accPage.doSearch(productName);
        productInfoPage = resultsPage.selectProduct(mainProductName);
        int actualCount = productInfoPage.getProductImageCount();
        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test
    public void productDataTest(){
        resultsPage = accPage.doSearch("MacBook");
        productInfoPage = resultsPage.selectProduct("MacBook Pro");
        Map<String, String> productMap = productInfoPage.getProdcutInfo();
        productMap.forEach((k,v)-> System.out.println(k+" : "+v));
        // Assert.assertEquals(productMap.get("name"), "MacBook Pro");
        // Assert.assertEquals(productMap.get("imageCount"), String.valueOf(Constants.MACBOOKPRO_IMAGES_COUNT));
        softAssert.assertEquals(productMap.get("name"), "MacBook Pro");
        softAssert.assertEquals(productMap.get("imageCount"), String.valueOf(Constants.MACBOOKPRO_IMAGES_COUNT));
        softAssert.assertAll();
    }
}
