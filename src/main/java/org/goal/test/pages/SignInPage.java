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
public class SignInPage {

    WebDriver driver;
    WebActions web;
    WebDriverWait waitForCondition;
    Logger log;

    public SignInPage(WebDriver driver, WebActions web){

        this.driver = driver;
        //webActions = new WebActions(driver);
        this.web = web;
        log = Logger.getLogger(SignInPage.class);
        //explicitly wait
        waitForCondition = new WebDriverWait (driver, 5 );
        //implicitly wait
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    //Method to input a user name
    public void inputLogin (String userName) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {

        web.inputText("SignInPage.LoginTextField", userName);
        log.info("'" + userName + "'" + " was entered into 'Login' field");
    }

    //Method to input a password
    public void inputPassword (String password) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {

        web.inputText("SignInPage.PasswordTextField", password);
        log.info("'" + password + "'" + " was entered into 'Password' field");
    }

    //Method to input a city name
    public void inputCityName (String password) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {

        web.inputText("SignInPage.CityNameTextField", password);
        log.info("'" + password + "'" + " was entered into 'CityName' field");
    }

    //Method to input an email address
    public void inputEmail (String password) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {

        web.inputText("SignInPage.EmailTextField", password);
        log.info("'" + password + "'" + " was entered into 'Email' field");
    }

    //this method is used to check out whether the 'Logout' link is present
    public boolean checkLogoutLinkPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        return web.isElementPresented("SignInPage.LogoutLink");
    }

    //this method is used to check out whether the 'StartPage' link is present
    public boolean checkStartPageLinkPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        return web.isElementPresented("SignInPage.StartPageLink");
    }

    //this method is used to check out whether the 'Login' label is present
    public boolean checkLoginLabelPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        return web.isElementPresented("SignInPage.LoginLabel");
    }

    //this method is used to check out whether the 'Password' label is present
    public boolean checkPasswordLabelPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        return web.isElementPresented("SignInPage.PasswordLabel");
    }

    //this method is used to check out whether the 'CityName' label is present
    public boolean checkCityNameLabelPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        return web.isElementPresented("SignInPage.CityNameLabel");
    }

    //this method is used to check out whether the 'EmailLabel' label is present
    public boolean checkEmailLabelLabelPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        return web.isElementPresented("SignInPage.EmailLabel");
    }
}
