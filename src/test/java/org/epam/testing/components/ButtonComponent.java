package org.epam.testing.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 *
 * Created by AlexSh on 23.10.2016.
 */
public class ButtonComponent {
    private WebDriver driverHere;
    private List<WebElement> elementS;
    private By buttonXPath = By.xpath("//*[@class='uui-button']");


    //Two constructors
    public ButtonComponent(WebDriver driver) {
        this.driverHere = driver;
        this.elementS = driverHere.findElements(buttonXPath);

    }

    public ButtonComponent(WebDriver driver, String xPath) {
        this.driverHere = driver;
        this.buttonXPath = By.xpath(xPath);
        this.elementS = driverHere.findElements(this.buttonXPath);

    }

    public WebElement getButtoN(String valueString ) {

        for (WebElement wE : elementS ) {
            System.out.println (elementS.size());

                    System.out.println (valueString);
                    System.out.println (wE.getAttribute("value"));
                    System.out.println (wE.getAttribute("value").equals(valueString));

            if (wE.getAttribute("value").equals(valueString))
                return wE;
        }

        return  null;

    }

  public void clickButtonFromComponent (String valueString){
      WebElement button = getButtoN(valueString);
      if (button != null)  {
           button.click();
           System.out.println("I've just clicked "+ valueString);
      }
      else
          System.out.println("I can not find "+ valueString+". Nothing to click!");

  }

  public List<WebElement> getButtonS(){
      return elementS;
  }

  public boolean hasButton (String valueString) {

      return elementS.size()!= 0 && getButtoN(valueString)!=null ;
  }

}
