package org.goal.test.cases;

import org.goal.test.tools.ConfigData;
import org.goal.test.tools.ExcelDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kostya on 11/8/16.
 */
@RunWith(value = Parameterized.class)
public class Test0001_LoginToApp extends ChromeTestCase {

    private String userName;
    private String password;

    public Test0001_LoginToApp(
            String userName,
            String password

    ){
        this.userName=userName;
        this.password=password;
    }

    @Parameterized.Parameters
    //public static Collection<Object[]> data() throws IOException
    public static Collection<String[]> data() throws IOException {
        Map<String, String> dataFromSpreadSheetColumn = new HashMap<String, String>();
        ArrayList<String []> listOfParameters = new ArrayList<String []>() ;
        String [] sa;
        for (int i=1; i<=2; i++){
            dataFromSpreadSheetColumn = ExcelDriver.getDataByColumn(ConfigData.getCfgValue("TEST_DATA"), "Login",i);
            sa = new String []{
                    dataFromSpreadSheetColumn.get("USERNAME"),
                    dataFromSpreadSheetColumn.get("PASSWORD")
            };

            listOfParameters.add(sa);
        }
        return listOfParameters;
    }

    @Before
    public void beforeTest(){
        log.info("Test started");
    }

    @Test
    public void login() throws Exception {

        goalApp.startPage.clickLoginLink();

        goalApp.loginPage.inputLogin(userName);
        goalApp.loginPage.inputPassword(password);
        goalApp.loginPage.clickSubmitButton();

        Assert.assertTrue( goalApp.loginPage.checkLogoutLinkPresence());
        goalApp.loginPage.clickLogoutLink();
        Assert.assertTrue(goalApp.startPage.checkLoginLinkPresence());
        Assert.assertTrue(goalApp.startPage.checkSignInLinkPresence());


    }

    @After
    public void afterTest(){
        log.info("Test has been completed");
    }

}
