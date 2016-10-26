package org.epam.testing.pageobjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by AlexSh on 19.10.2016.
 */
public class CheckBoxButtons {

    private WebDriver driverHere;
    private List<WebElement> elementS;
    private By checkBoxElementSPath = By.xpath("//label[@class='label-checkbox']/input[@type='checkbox']");
    //It also works fine:
    //private String partOfCheckBoxButtonXPath = "//label[@class='label-checkbox'][normalize-space(.)=";
    private String partOfCheckButtonXPath = "//label[@class='label-checkbox'][contains(.,";


    public CheckBoxButtons(WebDriver driver) {
        this.driverHere = driver;
        this.elementS = driverHere.findElements(checkBoxElementSPath);
    }

    public void clickCheckBoxButton(String buttonName) {
        //It works. Looking for checkbox button by xpass
        driverHere.findElement(formCheckButtonByXPass ( buttonName, partOfCheckButtonXPath)).click();

    }

    public boolean isSelectedCheckBoxButton(String buttonName) {

        if (driverHere.findElement(formCheckButtonByXPass ( buttonName, partOfCheckButtonXPath)).isSelected()) {
                return true;
        }
        return false;
    }

    public boolean hasCheckBoxButtons(){
        return elementS.size() != 0;
    }

    public List<WebElement> getElements() {
        return elementS;
    }

    //Looking for checkbox button we need, using its label's text
    public By formCheckButtonByXPass ( String buttonName, String partOfCheckButtonXPath){

        return By.xpath(partOfCheckButtonXPath+"'"+buttonName+"'"+")]/input[@type ='checkbox']");
    }

}
