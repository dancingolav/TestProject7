package org.epam.testing;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.epam.testing.components.WebDriverFactory;
import org.epam.testing.pageobjects.EpamLoginPage;
import org.epam.testing.components.FailureListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertTrue;


@Listeners({ FailureListener.class })
public class LoginTestSteps {

    //it is static & public. It is for all. Love it or leave it.
    public static WebDriver myPersonalDriver;
    public static EpamLoginPage epamLoginPage;
    public String browser;
    public String pathToDriver;
    public String loginPageUrl;


    //We initialize test here
    @Given ("^I have a browser, path to its driver and login page url$")
    public void setEnvironment(List<List<String>> table) {

       browser = table.get(1).get(1).trim();
       pathToDriver = table.get(2).get(1).trim();
       loginPageUrl = table.get(3).get(1).trim();

        System.out.println( browser);
        System.out.println( pathToDriver);
        System.out.println(loginPageUrl);

       }


    @Then ("^I have to open the login page of our test web site$")
    public void openLoginPage () {

        myPersonalDriver= new WebDriverFactory().getWebDriver(browser, pathToDriver);

        //At a "very beginning" we open fist page. This is really door to eternity
        epamLoginPage = new EpamLoginPage(myPersonalDriver);
        epamLoginPage.open(loginPageUrl);

    }

    @Given ("^I have the login web page open$")
    public void isLoginPageOpen() {
        if (epamLoginPage==null || myPersonalDriver==null) assertTrue(false);

    }


   @When("I test the login procedure providing user's name ([^\"]*) and password ([^\"]*)$")

        public void tryLogin(String userName , String userPassword) {
           epamLoginPage.login(userName, userPassword);
    }


   @Then("^I should be logged in if the credentials ([^\"]*) are correct and I shouldn't if they are not$")

       public void isUserLogin(String correctOrNot) {

       boolean testType = correctOrNot.trim().equals("true");

        if (! testType)
            //Is login failed? It has to be...
            assertTrue(epamLoginPage.isLoginFailed());
        else {
            //Is login succeed? It has to be...
            assertTrue(epamLoginPage.isLoginSucceed());
        }
    }


    @Given("^Test is completed$")
     public void testCompleted()  {
        //Close the driver
        myPersonalDriver.close();
        myPersonalDriver.quit();

    }


}
