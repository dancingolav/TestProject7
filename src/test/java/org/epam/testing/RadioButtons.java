package org.epam.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexSh on 19.10.2016.
 */
public class RadioButtons {

    private WebDriver driverHere;
    private List<WebElement> elementS;
    private By radioElementSPath = By.xpath("//label[@class='label-radio']/input[@name='metal'][@type='radio']");
    //It also works fine:
    //private String partOfRadioButtonXPath = "//label[@class='label-radio'][normalize-space(.)=";
    private String partOfRadioButtonXPath = "//label[@class='label-radio'][contains(.,";


    public RadioButtons(WebDriver driver) {
        this.driverHere = driver;
        this.elementS = driverHere.findElements(radioElementSPath);
    }

    public void clickRadioButton(final String buttonName) {
        //It works. Looking for radio button by xpass and check(click) it
        driverHere.findElement(formRadioButtonByXPass ( buttonName, partOfRadioButtonXPath)).click();

    }

    public boolean isSelectedOnlyOneRadioButton () {
        ArrayList<Boolean> howMany = new ArrayList<Boolean>();
        for (WebElement e: elementS) {
            if (e.isSelected())
                howMany.add(true);
        }
        return howMany.size()==1 ;
    }


    public boolean isSelectedCorrectRadioButton(final String buttonName) {

        for (WebElement e: elementS) {
            if (e.isSelected() && e.equals(driverHere.findElement(formRadioButtonByXPass ( buttonName, partOfRadioButtonXPath))))
                return true;
        }
        return false;
    }

    public boolean hasRadioButtons(){
        return elementS.size() != 0;
    }

    public List<WebElement> getElements() {
        return elementS;
    }

    //Looking for radio button, we need, using its label's text
    public By formRadioButtonByXPass (final String buttonName, final String partOfRadioButtonXPath){
        return By.xpath(partOfRadioButtonXPath+"'"+buttonName+"'"+")]/input[@name='metal'][@type='radio']");
    }
}
