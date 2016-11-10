package org.goal.test.pages;

import org.apache.log4j.Logger;
import org.goal.test.tools.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.goal.test.tools.ConfigData.ui;

/**
 * Created by kostya on 11/8/16.
 */
public class StartPage {

    WebDriver driver;
    WebActions web;
    WebDriverWait waitForCondition;
    Logger log;

    public StartPage(WebDriver driver, WebActions web) {

        this.driver = driver;
        //webActions = new WebActions(driver);
        this.web = web;
        log = Logger.getLogger(StartPage.class);
        //explicitly wait
        waitForCondition = new WebDriverWait(driver, 5);
        //implicitly wait
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }


    //Method to click on "Login" link
    public void clickLoginLink() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        web.clickElement("StartPage.LoginLink");
        log.info("'Authorization' link has been clicked");
        //waitForCondition.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPage.LogoutLink)));
        waitForCondition.until(ExpectedConditions.presenceOfElementLocated(ui("LoginPage.SubmitButton")));
        log.info("'LoginPage' has been displayed");
    }

    //Method to click on "SignIn" link
    public void clickSignInLink() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        web.clickElement("StartPage.SignInLink");
        log.info("'Registration' link has been clicked");
        //waitForCondition.until(ExpectedConditions.presenceOfElementLocated(By.xpath(HomePage.LOGOUT_LINK)));
        waitForCondition.until(ExpectedConditions.presenceOfElementLocated(ui("SignInPage.SubmitButton")));
        log.info("'SignInPage' has been displayed");
    }

    //this method is used to check out whether the 'Login' link is presented
    public boolean checkLoginLinkPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        return web.isElementPresented("StartPage.LoginLink");
    }

    //this method is used to check out whether the 'SignIn' link is presented
    public boolean checkSignInLinkPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        return web.isElementPresented("StartPage.SignInLink");
    }
}