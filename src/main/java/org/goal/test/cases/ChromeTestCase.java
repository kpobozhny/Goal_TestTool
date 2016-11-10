package org.goal.test.cases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.goal.test.pages.GoalApp;
import org.goal.test.tools.ConfigData;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Created by kostyap on 7/25/2016.
 */


public abstract class ChromeTestCase {

     static WebDriver driver;
     static GoalApp goalApp;
     static Logger log;
     private static final String EOL = System.getProperty("line.separator");
     private static StringBuilder builder = new StringBuilder();

    @Rule
    public TestWatcher watchman = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            if (description != null) {
                builder.append(description);
            }
            if (e != null) {
                builder.append(' ');
                builder.append(e);
            }
            builder.append(" FAIL");
            builder.append(EOL);
        }

        @Override
        protected void succeeded(Description description) {
            if (description != null) {
                builder.append(description);
            }
            builder.append(" OK");
            builder.append(EOL);
        }
    };

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        //in order to use ChromeDriver you need to download chromedriver.exe from https://sites.google.com/a/chromium.org/chromedriver/
        System.setProperty("webdriver.chrome.driver", "/home/kostya/libs/chromedriver");

        driver = new ChromeDriver();
        PropertyConfigurator.configure("src/main/resources/prop/log4j.properties");
        log = Logger.getLogger(ChromeTestCase.class);

        goalApp = new GoalApp(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        goalApp.webActions.openURL(ConfigData.getCfgValue("APP_URL"));
        log.info("Chrome has been run");
    }


    @AfterClass
    public static void tearDownAfterClass() {
        driver.quit();
        log.info("Chrome has been closed");
        log.info("-------------TESTS RESULTS -----------");
        log.info(builder);
        log.info("----------END OF TEST RESULTS---------");
    }
}
