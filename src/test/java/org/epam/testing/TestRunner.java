package org.epam.testing;

/**
 * Created by AlexSh on 01.11.2016.
 */


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;



    @RunWith(Cucumber.class)
    @CucumberOptions (features = "src/test/java/features/")
    public class TestRunner {
    }


