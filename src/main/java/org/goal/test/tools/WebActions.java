package org.goal.test.tools;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.goal.test.tools.ConfigData.ui;


/**
 * Created by kostyap on 7/23/2016.
 */
public class WebActions {

    WebDriver driver;
    Logger log ;
    WebDriverWait waitForCondition ;

    //constructor
    public WebActions(WebDriver driver){
        this.driver = driver;
        waitForCondition = new WebDriverWait(driver, 5 );
        log = Logger.getLogger(WebActions.class.getName());

        //implicitly wait
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    // This method is used to open site URL
    public void openURL(String url){
        driver.manage().window().maximize();
        driver.get(url);
        //if Exception - comment next line
        //driver.switchTo().frame(0);
        log.info("Url was opened: "+url);
    }

    // This method is used to det the inner text of a web element
    public String getValueOfElement(String elementLocator) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        WebElement element = driver.findElement(ui(elementLocator));
        //WebElement element = driver.findElement(By.xpath(elementLocator));
        return element.getText();
    }

    // This method is used to click on some button
    public void clickButton(String buttonLocator) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        WebElement button = driver.findElement(ui(buttonLocator));
        //WebElement button = driver.findElement(By.xpath(buttonLocator));
        button.click();
    }

    // This method is used to click on some link
    public void clickLink(String linkText) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        WebElement link = driver.findElement(ui(linkText));
        //WebElement link = driver.findElement(By.xpath(linkText));
        link.click();
    }

    // This method is used to click on some web element
    public void clickElement(String elementLocator) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        WebElement element = driver.findElement(ui(elementLocator));
        //WebElement element = driver.findElement(By.xpath(elementLocator));
        element.click();
    }

    // This method is used to click on some radioButton
    public void selectRadioButton(String radioButtonLocator) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {

        WebElement radioButton = driver.findElement(ui(radioButtonLocator));
        //WebElement radioButton = driver.findElement(By.xpath(radioButtonLocator));
        radioButton.click();
    }

    //This method is used to click on some radiobutton from block by text
    public void selectRadioButtonFromBlockByText(String blockLocator, String radioButtonName){

        String radioBtnXpath = blockLocator+"//*[contains(text(), '"+radioButtonName+"')]";
        WebElement radioButton = driver.findElement(By.xpath(radioBtnXpath));
        radioButton.click();
    }


   //This method is used to click on some radiobutton from block by value
    public void selectRadioButtonFromBlockByValue(String blockLocator, String radioButtonValue){

        String radioBtnXpath = blockLocator+"//*[@value='"+radioButtonValue+"']";
        WebElement radioButton= driver.findElement(By.xpath(radioBtnXpath));
        radioButton.click();
    }

    // This method is used to input some text to some field
    public void inputText(String fieldLocator, String text) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{

        WebElement textField= driver.findElement(ui(fieldLocator));
        //WebElement textField = driver.findElement(By.xpath(fieldLocator));
        waitForCondition.until(ExpectedConditions.presenceOfElementLocated(ui(fieldLocator)));
        textField.clear();
        textField.sendKeys(text);

    }

    ////////////

    //This method is used to clear some text field
    public void clearTextField(String textFieldLocator) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{

        WebElement textField = driver.findElement(ui(textFieldLocator));
        //WebElement textField = driver.findElement(By.xpath(textFieldLocator));
        waitForCondition.until(ExpectedConditions.presenceOfElementLocated(ui(textFieldLocator)));
        textField.clear();

    }

    //This method is used to input some text into some field and click ENTER key
    public void inputSomeTextAndClickEnter(String textFieldLocator, String text) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        inputText(textFieldLocator, text);
        //driver.findElement(By.xpath(textFieldLocator)).sendKeys(Keys.ENTER);
        driver.findElement(ui(textFieldLocator)).sendKeys(Keys.ENTER);

    }


     // This method is used to select checkbox
    public void selectCheckBox(String checkBoxLocator) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        WebElement checkBox;
        //checkBox = driver.findElement(By.xpath(checkBoxLocator));
        checkBox = driver.findElement(ui(checkBoxLocator));
        if (!checkBox.isSelected())	{
            checkBox.click();
        }
        log.info(checkBoxLocator + "Checkbox was selected");
    }


     //This method is used to deselect checkbox
     public void deselectCheckBox(String checkBoxLocator) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        WebElement checkBox;
        //checkBox = driver.findElement(By.xpath(checkBoxLocator));
        checkBox = driver.findElement(ui(checkBoxLocator));
        if (checkBox.isSelected())	{
            checkBox.click();
        }
        log.info(checkBoxLocator + "Checkbox was deselected");
    }

    /*
     * A common method to select/deselect checkbox
     * checkBoxState should correspond to the next format "YES/NO"
     *  */
    public void selectCheckBox2(String checkBoxLocator, String checkBoxState ) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        WebElement checkBox;
        //checkBox = driver.findElement(By.xpath(checkBoxLocator));
        checkBox = driver.findElement(ui(checkBoxLocator));

        if (checkBox.isSelected() && checkBoxState.equals("YES")){
            //waitForCondition.until(ExpectedConditions.elementToBeSelected(By.xpath(checkBoxLocator)));
            waitForCondition.until(ExpectedConditions.elementToBeSelected(ui(checkBoxLocator)));
            log.info("CheckBox is already selected");
        }

        if (checkBox.isSelected() && checkBoxState.equals("NO")){
            checkBox.click();
            //waitForCondition.until(ExpectedConditions.elementSelectionStateToBe(By.xpath(checkBoxLocator), false));
            waitForCondition.until(ExpectedConditions.not(ExpectedConditions.elementToBeSelected(By.xpath(checkBoxLocator))));
            log.info("Checkbox is un-checked");
        }

        if (!checkBox.isSelected() && checkBoxState.equals("YES")){
            checkBox.click();
            waitForCondition.until(ExpectedConditions.elementToBeSelected(By.xpath(checkBoxLocator)));
            log.info("Checkbox is checked");
        }

        if (!checkBox.isSelected() && checkBoxState.equals("NO")){
            //waitForCondition.until(ExpectedConditions.not(ExpectedConditions.elementToBeSelected(By.xpath(checkBoxLocator))));
            log.info("CheckBox is already deselected");
        }



    }


     // This method is used to refresh page
    public void refreshPageByF5 () throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{

        //driver.findElement(By.tagName("html")).sendKeys(Keys.F5);
        driver.findElement(ui("html")).sendKeys(Keys.F5);
    }



     //This is second method used to refresh page
    public void refreshPage (){
        driver.navigate().refresh();
    }


     //This method is used to delete all cookies for the current domain
    public void clearCookies(){
        driver.manage().deleteAllCookies();
    }

    //This method is used to delete some particular cookie by its name
    public void deleteNamedCookie(String cookieName) {
        driver.manage().deleteCookieNamed(cookieName);
    }

    //This method is used to delete some particular cookie
    public void deleteNamedCookie(Cookie cookie) {
        driver.manage().deleteCookie(cookie);
    }

    //This method is used to select an item from a dropdown list
    public void selectItemFromDropDownList(String listLocator, String itemName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        WebElement list = driver.findElement(ui(listLocator));
        //WebElement list  = driver.findElement(By.xpath(listLocator));
        list.click();
        Select select = new Select(list);
        select.selectByVisibleText(itemName);

    }

    //This method is used to select an item from a dropdown list
    public void selectItemFromDropDownListByFocus(String listLocator, String itemName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{
        WebElement list;
        //list  = driver.findElement(By.xpath(listLocator));
        list  = driver.findElement(ui(listLocator));
        waitForCondition.until(ExpectedConditions.presenceOfElementLocated(ui(listLocator)));
        focusOnElement(listLocator);
        Select select = new Select(list);
        select.selectByVisibleText(itemName);

    }

    //This method is used to focus on some web element
    public void focusOnElement(String elementLocator) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException{

        WebElement element = driver.findElement(ui(elementLocator));
        //WebElement element  = driver.findElement(By.xpath(elementLocator));
        waitForCondition.until(ExpectedConditions.presenceOfElementLocated(ui(elementLocator)));
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    // This method is used to check whether the element is presented on the page.
    public boolean isElementPresented(String elementLocator) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException  {
        try {
            //driver.findElement(By.xpath(elementLocator));
            driver.findElement(ui(elementLocator));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

}
