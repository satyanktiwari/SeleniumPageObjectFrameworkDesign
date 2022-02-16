package com.qa.opencart.pages;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("pageClass01 - Login Page")
@Story("Login Page features")
public class LoginPage {
    
    private WebDriver driver;
    ElementUtils elementUtils;

    By emailField = By.id("input-email");
    By passwordField = By.id("input-password");
    By loginButton = By.xpath("//input[@value='Login']");
    By forgotPasswordLink = By.linkText("Forgotten Password");
    By registerLink = By.linkText("Register");

    
    public LoginPage(WebDriver driver) {
        this.driver = DriverFactory.getDriver();
        elementUtils = new ElementUtils(driver);
    }
    /**
     * This is a constructor for LoginPage class
     * which uses the ThreadLocal variable to initialize the driver
     * here we do not need to pass the driver as a parameter
     */
    // public LoginPage() {
    //     // this.driver = driver;
    //     driver = DriverFactory.getDriver();
    //     elementUtils = new ElementUtils(driver);
    // }

    // public String getLoginPageTitle() {
    //     return driver.getTitle();
    // }


    @Step("Get the title of the login page")
        public String getLoginPageTitle(){
            return elementUtils.doGetPageTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_INT_TIMEOUT);
        }

    // public String getLoginPageUrl() {
    //     return driver.getCurrentUrl();
    // }
        @Step("Get the url of the login page")
    public String getLoginPageUrl() {
        return elementUtils.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_INT_TIMEOUT);
    }

    // public Boolean isForgotPasswordLinkExist() {
    //     return driver.findElement(forgotPasswordLink).isDisplayed();
    // }

    @Step("Verify Forgot Password Link exists on login page")
    public Boolean isForgotPasswordLinkExist(){
        return elementUtils.doIsDisplayed(forgotPasswordLink);
    }

    // public void doLogin(String email, String password){

    //     driver.findElement(emailField).sendKeys(email);
    //     driver.findElement(passwordField).sendKeys(password);
    //     // driver.findElement(loginButton).click();
    // }

    @Step("Login test with valid credentials with username : {0} and password : {1}")
    public AccountsPage doLogin(String email, String password){
        elementUtils.doSendKeys(emailField, email);
        elementUtils.doSendKeys(passwordField, password);
        elementUtils.doClick(loginButton);
        return new AccountsPage(driver);
    }

        @Step("Navigate to Register page")
    public RegisterPage goToRegisterPage() {
        elementUtils.doClick(registerLink);
        return new RegisterPage(driver);
    }
}
