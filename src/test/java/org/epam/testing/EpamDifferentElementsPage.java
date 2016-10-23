package org.epam.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by AlexSh on 19.10.2016.
 */
public class EpamDifferentElementsPage {
    //page address
    private String epamDiffElementsPageUrl = "https://jdi-framework.github.io/tests/page8.htm";
    //it's obvious
    private WebDriver driverHere;
    //elements of log that shows buttons checked;
    private List<WebElement> logElementS;
    //record with info about button checked
    private By logRecord = By.xpath("//div[@class='info-panel-section']/ul[@class='panel-body-list logs']/li");

    //Two constructors
    public EpamDifferentElementsPage(final WebDriver driver) {
        driverHere = driver;
    };
    public EpamDifferentElementsPage(final WebDriver driver, final String pageUrl) {
           driverHere = driver;
           epamDiffElementsPageUrl = pageUrl;
    };

public void open () {
    //impicit timeout
    driverHere.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    driverHere.get(epamDiffElementsPageUrl);
    driverHere.manage().window().maximize();
}

public void close () {
    driverHere.close();
}


public RadioButtons getRadioButtons() {
    return new RadioButtons(driverHere);
}

public CheckBoxButtons getCheckBoxButtons() {return new CheckBoxButtons(driverHere);}



//the page must contain radio buttons
//it is first part of test
public boolean hasRadioButtons() {
   return new RadioButtons(driverHere).hasRadioButtons();

}

//the page must contain checkbox buttons
//it is first part of test
    public boolean hasCheckBoxButtons() {
        return new CheckBoxButtons(driverHere).hasCheckBoxButtons();

    }




//Check whether last record contains correct string
//If  string contains correct button's name and any words that we are looking for,  test is passed
public boolean lastLogRecordContains(String ... partsOfStringsWeAreLooking) {

    //get log list
    logElementS=driverHere.findElements(logRecord) ;
    boolean hasAllSubStrings =true;
    System.out.println(logElementS.size());
    if (logElementS.size() !=0) {
        System.out.println((logElementS.get(0)).getText());

        for (String partOfString : partsOfStringsWeAreLooking) {
            System.out.println(partOfString);
            System.out.println(logElementS.get(0).getText());
            hasAllSubStrings = hasAllSubStrings && (logElementS.get(0).getText().contains(partOfString));
        }

        return hasAllSubStrings;
    }
    else
        return false;
}




}
