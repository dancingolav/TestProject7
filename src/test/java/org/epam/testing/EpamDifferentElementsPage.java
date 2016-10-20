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
    //elements of log;
    private List<WebElement> logElementS;

    private By logRecord = By.xpath("//div[@class='info-panel-section']/ul[@class='panel-body-list logs']/li");

    public EpamDifferentElementsPage(WebDriver driver) {
        driverHere = driver;
    };

public void open () {
    //impicit timeout
    driverHere.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    driverHere.get(epamDiffElementsPageUrl);
    driverHere.manage().window().maximize();
}

public RadioButtons getRadioButtons() {
    return new RadioButtons(driverHere);
}

public boolean hasRadioButtons() {
   return new RadioButtons(driverHere).hasRadioButtons();

}

//Check whether last record contains string

public boolean lastLogRecordContains(String str) {

    //get log list
    logElementS=driverHere.findElements(logRecord) ;

    if (logElementS.size() !=0) {
        System.out.println((logElementS.get(0)).getText());
        System.out.println(str);
        return (logElementS.get(0)).getText().contains(str);
    }
    else
        return false;
}

}
