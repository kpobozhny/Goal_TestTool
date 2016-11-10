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
public class LogInPage {

    WebDriver driver;
    WebActions web;
    WebDriverWait waitForCondition;
    Logger log;

    public LogInPage(WebDriver driver, WebActions web){

        this.driver = driver;
        //webActions = new WebActions(driver);
        this.web = web;
        log = Logger.getLogger(LogInPage.class);
        //explicitly wait
        waitForCondition = new WebDriverWait (driver, 15 );
        //implicitly wait
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    //Method to input a user name
    public void inputLogin (String userName) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {

        web.inputText("LoginPage.LoginTextField", userName);
        log.info("'" + userName + "'" + " was entered into 'Login' field");
    }

    //Method to input a password
    public void inputPassword (String password) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {

        web.inputText("LoginPage.PasswordTextField", password);
        log.info("'" + password + "'" + " was entered into 'Password' field");
        waitForCondition.until(ExpectedConditions.elementToBeClickable(ui("LoginPage.SubmitButton")));
        log.info("'Submit' button is active");
    }

    //Metod to click on "Submit" button
    public void clickSubmitButton() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        web.clickElement("LoginPage.SubmitButton");
        log.info("'Submit' button has been clicked");
        //waitForCondition.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LoginPage.StartPageLink)));
        waitForCondition.until(ExpectedConditions.presenceOfElementLocated(ui("LoginPage.LogoutLink")));

    }

    //Metod to click on "Logout" link
    public void clickLogoutLink() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        web.clickElement("LoginPage.LogoutLink");
        log.info("'Logout' link has been clicked");
    }

    //this method is used to check out whether the 'Submit' button is present
    public boolean checkLoginButtonPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        return web.isElementPresented("LoginPage.SubmitButton");
    }

    //this method is used to check out whether the 'Logout' link is present
    public boolean checkLogoutLinkPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        return web.isElementPresented("LoginPage.LogoutLink");
    }

    //this method is used to check out whether the 'StartPage' link is present
    public boolean checkStartPageLinkPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        return web.isElementPresented("LoginPage.StartPageLink");
    }

    //this method is used to check out whether the 'Login' label is present
    public boolean checkLoginLabelPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        return web.isElementPresented("LoginPage.LoginLabel");
    }

    //this method is used to check out whether the 'Password' label is present
    public boolean checkPasswordLabelPresence() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        return web.isElementPresented("LoginPage.PasswordLabel");
    }


}
