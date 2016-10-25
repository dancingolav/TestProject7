package org.epam.testing.pageobjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 *
 * Created by AlexSh on 23.10.2016.
 */
public class DropDownMenu {

    private WebDriver driverHere;
    private List<WebElement> dropDownOptions;
    private By dropDownMenuByXpath = By.xpath("//div[@class='colors']/select[@class='uui-form-element']/option");



    public DropDownMenu(WebDriver driver) {
        this.driverHere = driver;
        this.dropDownOptions = driverHere.findElements(dropDownMenuByXpath);
    }


    public List<WebElement> getDropDownOptions () {
        return  driverHere.findElements(dropDownMenuByXpath);
    }

    //Looking for option we need, and click
    public void clickDropDownMenu(String optionName) {
        for (WebElement opt : getDropDownOptions()) {
            if (opt.getAttribute("value").equals(optionName)) {
                opt.click();
                System.out.println(opt.getAttribute("value") + " " + "click!");
            }
        }
    }

    //Looking whether option is selected
    public boolean isSelectedDropDownMenuOption(String optionName){
        for (WebElement opt : getDropDownOptions()) {
            if (opt.getAttribute("value").equals(optionName))
                 return true;


        }
    return false;
    }

    public boolean hasDropDownMenu(){
        return getDropDownOptions().size()!=0;
    }

    public DropDownMenu getDropDownMenu () {
        return new DropDownMenu(driverHere);
    }
}
