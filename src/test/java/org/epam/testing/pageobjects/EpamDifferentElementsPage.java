package org.epam.testing.pageobjects;

import org.epam.testing.pageobjects.components.ButtonComponent;
import org.epam.testing.pageobjects.components.CheckBoxButtons;
import org.epam.testing.pageobjects.components.DropDownMenu;
import org.epam.testing.pageobjects.components.RadioButtons;
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
    driverHere.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
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

public DropDownMenu getDropDownMenu() {return new DropDownMenu(driverHere);}

public ButtonComponent getButtonComponent()   {return new ButtonComponent(driverHere);}

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

     public boolean hasDropDownMenu () {
         return new DropDownMenu(driverHere).hasDropDownMenu();
    }

//the page must contain buttons to test
//it is first part of test
    public boolean hasButtonS() {
        return new ButtonComponent(driverHere).hasButtonS();
    }

    public List<WebElement> getButtonS () {
        return new ButtonComponent(driverHere).getButtonS();

    }


//Check whether last record contains correct string
//If  string contains correct button's name and all of words that we are looking for,  test is passed
public boolean lastLogRecordContains(String ... partsOfStringsWeAreLooking) {

    //get log list
    logElementS=driverHere.findElements(logRecord) ;
    boolean hasAllSubStrings =true;

    if (logElementS.size() !=0) {

        for (String partOfString : partsOfStringsWeAreLooking) {
            System.out.println(logElementS.get(0).getText());
            hasAllSubStrings = hasAllSubStrings &&
                    (logElementS.get(0).getText().toLowerCase().contains(partOfString.toLowerCase()));
        }

        return hasAllSubStrings;
    }
    else
        return false;


}

}
