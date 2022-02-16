package com.qa.opencart;

import java.util.List;

import com.qa.opencart.utils.Constants;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountsPageTest extends BaseTest{
    /**
     * create a setup method to initialize the driver from the login page
     * Precondition: user is logged in to the application
     * So first using the login page to login to the application
     * Since this needs to be run before methods in this class</br>
     * '@BeforeClass' is used so that it runs after the BaseTest class</br>
     * Which uses '@BeforeTest' tag.
     */
    @BeforeClass
     public void accPageSetUp(){
        accPage = loginPage
        .doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
     }

     @Test
     public void accPageTitleTest(){
         String title = accPage.getAccountPageTitle();
         System.out.println("Title is:"+title);
         Assert.assertEquals(title,Constants.ACCOUNT_PAGE_TITLE);
     }

     @Test
        public void accPageUrlTest(){
            String url = accPage.getAccountPageUrl();
            Assert.assertTrue(url.contains(Constants.ACCOUNT_PAGE_URL_FRACTION));
        }

        @Test
        public void accPageHeaderExistsTest(){
            Assert.assertTrue(accPage.isAccountPageHeaderExist());
        }

        @Test
        public void accPageHeaderTest(){
            String header = accPage.getAccountPageHeader();
            Assert.assertEquals(header,Constants.ACCOUNT_PAGE_HEADER);
        }


     @Test
        public void accAogoutLinkExistTest(){
            Assert.assertTrue(accPage.isLogoutLinkExist());
        }

        // @Test
        //     public void doLogoutTest(){
        //         accPage.doLogout();
        //     }
        @Test
        public void accSearchExistsTest(){
            Assert.assertTrue(accPage.isSearchExists());
        }

        @Test
        public void accSearchIconExistsTest(){
            Assert.assertTrue(accPage.isSearchIconExists());
        }

        @Test
        public void accountPgeSectionsTest(){
            List<String> accList = accPage.getAccountPgeSections();
            System.out.println("Account Page Sections are:"+accList);
            Assert.assertEquals(accList, Constants.ACCOUNT_PAGE_SECTIONS_List);
        }

        @DataProvider
        public Object[][] provideSearchProductData(){
            return new Object[][]{
                {"Macbook"},
                {"imac"},
                {"Apple"}

            };

        }

        @Test(dataProvider = "provideSearchProductData")
        public void doSearchTest(String productName){
            resultsPage = accPage.doSearch(productName.trim());
            Assert.assertTrue(resultsPage.getProductListCount()>0);

        }

        @DataProvider
        public Object[][] provideSearchAndSelectData(){
            return new Object[][]{
                {"MacBook","MacBook"},
                {"MacBook","MacBook Pro"},
                {"MacBook","MacBook Air"},
                {"iMac","iMac"},
                {"Apple","Apple Cinema 30\""}
            };
        }

        @Test(dataProvider = "provideSearchAndSelectData")
        public void selectProduct(String productName, String mainProductName){
            resultsPage = accPage.doSearch(productName);
            productInfoPage = resultsPage.selectProduct(mainProductName);
            Assert.assertEquals(productInfoPage.getProductHeaderName(), mainProductName);
        }
}
