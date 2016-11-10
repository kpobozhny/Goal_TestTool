package org.goal.test.pages;

import org.goal.test.tools.WebActions;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by kostya on 11/8/16.
 */
public class GoalApp {

    public WebActions webActions;
    public StartPage startPage;
    public LogInPage loginPage;
    public SignInPage signinPage;

    public GoalApp(WebDriver driver) throws IOException {

        webActions = new WebActions(driver);
        startPage = new StartPage(driver, webActions);
        loginPage = new LogInPage(driver, webActions);
        signinPage = new SignInPage(driver, webActions);
    }


}
