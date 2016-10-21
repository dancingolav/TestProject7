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
    private String radioName = "//label[@class='label-radio'][normalize-space(.)=";


    public RadioButtons(WebDriver driver) {
        this.driverHere = driver;
        this.elementS = driverHere.findElements(radioElementSPath);
    }

    public void clickRadioButton(String buttonName) {


        //Why does it not work? It looks like e.getText().contains(buttonName) does not return what i heed
        //Why? Who know?

        /*Ask me. And I will laugh quietly.
          Give me any question. You will not have my answer.
          My name is just time worn Hieroglyph.
          My clothes are patched with the wind.
        */

       /* for (WebElement e: elementS) {
            System.out.println(e.getText());

             if (e.getText().contains(buttonName)) {
                 System.out.println(e.getText());
                 e.click();
             }
        }*/

        //This works.
        driverHere.findElement(By.xpath(radioName+"'"+buttonName+"']")).click();

    }

    public boolean isSelectedOnlyOneRadioButton () {
        ArrayList<Boolean> howMany = new ArrayList<Boolean>();
        for (WebElement e: elementS) {
            if (e.isSelected())
                howMany.add(true);
        }
        return howMany.size()==1 ;
    }


    public boolean isSelectedCorrectRadioButton(String buttonName) {

        for (WebElement e: elementS) {
            if (e.isSelected() && e.equals(buttonName))
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
}
