package org.epam.testing.components;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by AlexSh on 01.11.2016.
 */
public class WebDriverFactory {

    public WebDriver getWebDriver (String browser, String pathToDriver) {

        ArrayList<String> browsersHerd = new ArrayList<String>(Arrays.asList(new String[]{"firefox", "chrome", "ie", "opera", "phantom"}));

        //System's properties we have to set to use drivers
        String[] sysProperty = new String[]{
                "webdriver.gecko.driver",
                "webdriver.opera.driver",
                "webdriver.ie.driver",
                "webdriver.chrome.driver",
                "phantomjs.binary.path"
        };

        //Checking whether file is exist
        File f = new File(pathToDriver);
        if (!(f.exists() && !f.isDirectory())) {
            System.out.println("Error! Check your browser's path in testng.xml!");
            Assert.fail("Error! Check your browser's path in testng.xml!");
        }
        //Checking  whether browser of correct type
        if (!browsersHerd.contains(browser)) {
            System.out.println("Error! Check your browser type in testng.xml!");
            System.out.println("firefox,chrome,ie,opera");
            Assert.fail("Error! Check your browser type testng.xml!");
        }


        //Since Java 7 we can use String
        switch (browser.toLowerCase()) {
            case "firefox":
                System.setProperty(sysProperty[0], pathToDriver);
                return new FirefoxDriver();

            case "opera":
                System.setProperty(sysProperty[1], pathToDriver);
                return new OperaDriver();

            case "ie":
                System.setProperty(sysProperty[2], pathToDriver);
                return new InternetExplorerDriver();

            case "chrome":
                System.setProperty(sysProperty[3], pathToDriver);
                return new ChromeDriver();

            case "phantom":
                System.setProperty(sysProperty[4], pathToDriver);
                Capabilities caps = new DesiredCapabilities().phantomjs();
                ((DesiredCapabilities) caps).setJavascriptEnabled(true);
                ((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
                return new PhantomJSDriver(caps);

        }
        return null;
    }

}