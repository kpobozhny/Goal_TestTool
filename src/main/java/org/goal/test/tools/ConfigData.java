package org.goal.test.tools;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by kostyap on 7/23/2016.
 */
/*
 *  That class provides static methods for getting values from Config and UI mapping files
 */
public class ConfigData {
    private static String cfgFile = "src/main/resources/prop/config.properties";
    private static String uiMappingFile = "src/main/resources/prop/UIMapping.properties";

    /*
     * Return value from .properties file
     */
    public static String getValueFromFile(String key, String fileName)
            throws IOException {
        Properties p = new Properties();
        // Create stream for reading from file
        FileInputStream cfg = new FileInputStream(fileName);
        // Load Properties from input stream
        p.load(cfg);
        cfg.close();

        // Return value for the property
        return (p.getProperty(key));
    }

    /*
     * Return value from UI mapping file. Note, please, that returned value is
     * String. We should take care of value's type by himself when will use
     * config data value in the test.
     */
    public static String getUiMappingValue(String key) throws IOException {

        return (getValueFromFile(key, uiMappingFile));
    }

    /*
     * Return value from config file. Note, please, that returned value is
     * String. We should take care of value's type by himself when will use
     * config data value in the test.
     */
    public static String getCfgValue(String key) throws IOException {

        return (getValueFromFile(key, cfgFile));
    }

    /*
     * Return By class with finding method and target for WebElement from UI mapping file
     */
    public static By ui(String key) throws IOException, ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        // Get WebElement's locator from UI mapping file and identify a finding method and a target
        String[] partsOfLocator = getValueFromFile(key, uiMappingFile).split("\"");
        String findMethod = partsOfLocator[0];
        String target = partsOfLocator[1];

        // Return By class with appropriate method and target
        switch (findMethod.toLowerCase()) {

            case "id":        	return By.id(target);
            case "xpath":      	return By.xpath(target);
            case "name":      	return By.name(target);
            case "linktext":   	return By.linkText(target);
            case "tagname":   	return By.tagName(target);
            case "classname":	return By.className(target);
            case "cssselector":	return By.cssSelector(target);
            default: 			return By.partialLinkText(target);
        }
    }

}