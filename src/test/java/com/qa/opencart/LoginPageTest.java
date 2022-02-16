package com.qa.opencart;

import com.qa.opencart.utils.Constants;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic - pageClass01 - Login Page")
@Story("UserStory - Login Page features")
public class LoginPageTest extends BaseTest{
//Tests were passing when run individually but when run as part of a suite they fail.
//hence added priority to the test methods.

    @Description("Verify title of the login page")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 1, enabled = false)
    public void loginPageTitleTest() {
        String title = loginPage.getLoginPageTitle();
        System.out.println("Title is:"+title);
        Assert.assertEquals(title,Constants.LOGIN_PAGE_TITLE);
       
        
    } 
    @Severity(SeverityLevel.BLOCKER)  
    @Description("Verify login page url")
        @Test(priority = 2)
        public void loginPageUrlTest(){
            String url = loginPage.getLoginPageUrl();
            Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_URL_FRACTION));
        }
    
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify Forgot Password Link exists on login page")
        @Test(priority = 3)
        public void isForgotPasswordLinkExistTest(){
            // loginPage.isForgotPasswordLinkExist();
            Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
        }
        @Severity(SeverityLevel.BLOCKER)
        @Description("Login test with valid credentials")
        @Test(priority = 4, description = "Login test with valid username and password")
        public void doLoginTest(){
            loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
        }

}

  
