package com.qa.opencart;

import java.util.Properties;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.utils.ElementUtils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    WebDriver driver;
    DriverFactory driverFactory;
    Properties prop;
    ElementUtils elementUtils;
    

//  Only refrence needs to be created do not create object here
    LoginPage loginPage;
    AccountsPage accPage;
    RegisterPage registerPage;
    ResultsPage resultsPage;
    ProductInfoPage productInfoPage;
    SoftAssert softAssert;


    @Parameters({"browser","browserversion"})
    @BeforeTest
    public void setup(String browser,  String browserversion) {
        driverFactory = new DriverFactory();
        prop = driverFactory.init_properties();

        if(browser!=null){
            prop.setProperty("browser", browser);
            prop.setProperty("browserversion", browserversion);
        }

        // driver = driverFactory.init_driver("chrome");
        driver = driverFactory.init_driver(prop);
        loginPage = new LoginPage(driver);
        //To use the ThreadLocal variable and contstructor without parameter use the below line
        // loginPage = new LoginPage();
        softAssert = new SoftAssert();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    
}
