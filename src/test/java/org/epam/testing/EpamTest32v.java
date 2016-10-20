package org.epam.testing;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.epam.testing.EpamTest31v.epamLoginPage;
import static org.epam.testing.EpamTest31v.myPersonalDriver;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by AlexSh on 19.10.2016.
 */
public class EpamTest32v {

private EpamDifferentElementsPage differentElements;
private RadioButtons radioButtons;
private ArrayList<String> buttonsToCheck = new ArrayList<String>( Arrays.asList(new String[]{"Gold","Silver","Bronze","Selen"}));

@Test
public void tryRadioButtons () {


    //For sure we are testing state here but not code as suppose
    //But in any case we need some setups for the test

    if (!epamLoginPage.isLoggedIn()) {

        //if login-logout menu is closed we will open it and log in
        if (!(epamLoginPage.isLoginOrLogoutMenuOpen())) {
            epamLoginPage.openLoginOrLogoutMenu();

        }
        epamLoginPage.login("epam", "1234");
    }

    //No sence to test radiobuttons if we are not logged in. Test is failed in this case.
    if (!epamLoginPage.isLoggedIn()) {
           //We are not logged in
           assertTrue(false);
    }

    differentElements = new EpamDifferentElementsPage(myPersonalDriver);
    differentElements.open();

    //We will click Radio Buttons on the EpamDifferentElementsPage one by one and check whether they are selected
    //It is our test

    //Does RadioButtons exist on the page?
    //differentElements.hasRadioButtons() ?
    //Then we will get it
    //myRadioButtons = differentElements.getRadioButtons()
    //else assertTrue(false)

     //click by one and check whether it is selected
     //myRadioButtons.getButtonsList

     //myRadioButton.click(button)
     //myRadioButton.isChecked (button)

    //Does RadioButtons exist on the page? If it does not the test is failed
     if (! differentElements.hasRadioButtons()) {
         assertTrue(false);
     }
     else {
         radioButtons = differentElements.getRadioButtons();
     }


     //click radio button and check log list. The log list works for "chrome". For "firefox" it does not work
     //Its last line has contain buttons name

     for (String str: buttonsToCheck ) {
         radioButtons.clickRadioButton(str);
         System.out.println(str);

         if (differentElements.lastLogRecordContains(str))
              assertTrue(true);
         else
             assertTrue(false);


     }


}
}
