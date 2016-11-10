package org.goal.test.cases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.goal.test.pages.GoalApp;
import org.goal.test.tools.ConfigData;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by kostyap on 7/25/2016.
 */


public abstract class FirefoxTestCase {

     static WebDriver driver;
     static GoalApp goalApp;
     static Logger log;



    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        System.setProperty("webdriver.gecko.driver", "/home/kostya/libs/geckodriver");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        driver = new FirefoxDriver();
        PropertyConfigurator.configure("src/main/resources/prop/log4j.properties");
        log = Logger.getLogger(FirefoxTestCase.class);

        goalApp = new GoalApp(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        goalApp.webActions.openURL(ConfigData.getCfgValue("APP_URL"));
        log.info("Firefox has been run");
    }


    @AfterClass
    public static void tearDownAfterClass() {
        driver.quit();
        log.info("Firefox has been closed");
    }
}
