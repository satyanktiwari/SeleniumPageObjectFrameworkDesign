package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    
    /**
     * Initialize the WebDriver
     */
    private WebDriver driver;

    private ElementUtils elementUtils;

    /**
     * By Locators list for the Register Page
     */

    private By header = By.cssSelector("div#content h1");
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPassword = By.id("input-confirm");

    private By newsletterYes = By.xpath("(//input[@name= 'newsletter'])[1]");
    private By newsletterNo = By.xpath("(//input[@name= 'newsletter'])[2]");
    
    private By agreeCheckbox = By.xpath("//input[@type='checkbox' and @name='agree']");
    private By continueButton = By.xpath("//input[@type = 'submit' and @value='Continue']");
    private By successMessage = By.cssSelector("div#content h1");

    private By logOutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");


    /**
     * Constructor
     *
     * @param driver
     */
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        elementUtils = new ElementUtils(driver);
    }


    /**Use this method to fill the registration page</br>
     * Since password and confirm password will use same values</br>
     * only one parameter for password has been defined.
     * @param firstName
     * @param lastName
     * @param email
     * @param telephone
     * @param password
     * @param subscribe
     */

    public boolean doAccountRegistration(String firstName, 
                                        String lastName, String email, String telephone,
                                        String password, String subscribe) {

        elementUtils.doSendKeys(this.firstName, firstName);
        elementUtils.doSendKeys(this.lastName, lastName);
        elementUtils.doSendKeys(this.email, email);
        elementUtils.doSendKeys(this.telephone, telephone);
        elementUtils.doSendKeys(this.password, password);
        elementUtils.doSendKeys(this.confirmPassword, password);

        if(subscribe.equalsIgnoreCase("yes")){
            elementUtils.doClick(newsletterYes);
        }else{
            elementUtils.doClick(newsletterNo);
        }

        elementUtils.doClick(agreeCheckbox);
        elementUtils.doClick(continueButton);

        String successMessg =  elementUtils.doGetText(successMessage);
        System.out.println("Message is: "+ successMessg);

        if(successMessg.contains("Your Account Has Been Created!")){
            System.out.println("Account Created Successfully");
            elementUtils.doClick(logOutLink);
            elementUtils.doClick(registerLink);
            return true;
        }
        return false;


    }


}
