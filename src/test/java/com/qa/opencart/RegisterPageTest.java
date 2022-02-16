package com.qa.opencart;

import java.util.Random;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest{

    @BeforeTest
    public void registerPageSetup() {
        registerPage = loginPage.goToRegisterPage();
    }

    /**
     * This method will return random string to be used as email
     * @return
     */
    public String getRandomNumber(){
        Random random = new Random();
        String email = "set" + random.nextInt(1000) + "@gmail.com";
        return email;
    }

    @DataProvider
    public Object[][]getRegisterData() {
        Object regData[][]= ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
        return regData;
    }

    @Test(dataProvider = "getRegisterData")
    public void userRegistrationTest(String firstName, 
                                        String lastName, String telephone,
                                            String password, String subscribe){
        Assert.assertTrue(registerPage.doAccountRegistration(firstName, 
                            lastName, getRandomNumber(), telephone, password, subscribe));  
    }
    
}
