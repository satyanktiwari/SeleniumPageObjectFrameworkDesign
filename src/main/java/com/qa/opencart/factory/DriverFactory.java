package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    public WebDriver driver;
    public OptionsManager optionsManager;
    public Properties prop;
    public static String highlight;
    //Setting up ThreadLocal variable for WebDriver
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();


    /**
     * This method is used to initialize the driver
     * it uses ThreadLocal variable to initialize the driver
     * @param prop
     * @return
     */

    public WebDriver init_driver(Properties prop) {
        String browser = prop.getProperty("browser").trim();
        String browserversion = prop.getProperty("browserversion").trim();
        highlight = prop.getProperty("highlight").trim();

        System.out.println("Browser is: " + browser + " and version is: " + browserversion);

        optionsManager = new OptionsManager(prop);
       
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            // driver = new ChromeDriver(optionsManager.getChromeOptions());
            if(Boolean.parseBoolean(prop.getProperty("remote").trim())){
                //remote execution
                init_remoteDriver("chrome");
            }
            else{
                //local execution
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
            }
        } 
        else if(browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            // driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
            if(Boolean.parseBoolean(prop.getProperty("remote").trim())){
                //remote execution
                init_remoteDriver("firefox");
            }
            else{
                //local execution
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
            }
            
        } 
        else if(browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            // driver = new EdgeDriver(optionsManager.getEdgeOptions());
            if(Boolean.parseBoolean(prop.getProperty("remote").trim())){
                //remote execution
                init_remoteDriver("edge");
            }
            else{
                //local execution
                tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
            }
            
        } 
        else {
            System.out.println("Browser is not supported");
        }        
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        
        // driver.get("https://demo.opencart.com/index.php?route=account/login");
        getDriver().get(prop.getProperty("url"));
        return getDriver();

    }

    private void init_remoteDriver(String browser) {
        System.out.println("Remote driver is being initialized: "+ browser);
        if(browser.equalsIgnoreCase("chrome")){
            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")) , optionsManager.getChromeOptions()));
            } catch (MalformedURLException e) {
                
                e.printStackTrace();
            }
        } else if(browser.equalsIgnoreCase("firefox")){
            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")) , optionsManager.getFirefoxOptions()));
            } catch (MalformedURLException e) {
                
                e.printStackTrace();
            }
        } else if(browser.equalsIgnoreCase("edge")){
            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")) , optionsManager.getEdgeOptions()));
            } catch (MalformedURLException e) {
                
                e.printStackTrace();
            }
        }
    }

    /**
     * WebDriver java.lang.ThreadLocal.get()
        Returns the value in the current thread's copy of this thread-local variable.</br>
        If the variable has no value for the current thread,</br>
         it is first initialized to the value returned by an invocation </br>
         of the initialValue method.</br>

        Returns:</br>
        the current thread's value of this thread-local
     */
    public static WebDriver getDriver() {
       return  tlDriver.get();
    }

    public Properties init_properties(){
        prop = new Properties();
        try {
            
            FileInputStream fis = 
                new FileInputStream("./src/test/java/com/qa/opencart/resources/config/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
        return prop;
        
    }
    public String getScreenshot(){
        File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(screenshotPath);
        try {
            // TakesScreenshot screenshot = (TakesScreenshot)driver;
            // File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, destination);
            // System.out.println("Screenshot taken");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
        return screenshotPath;
    }
    
}
