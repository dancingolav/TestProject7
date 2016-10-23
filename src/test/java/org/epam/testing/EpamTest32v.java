package org.epam.testing;

import org.testng.annotations.BeforeClass;
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
private CheckBoxButtons checkBoxButtons;
private ArrayList<String> radioButtonsToCheck=
        new ArrayList<>(Arrays.asList(new String [] {"Gold","Silver","Bronze","Selen","Selen","Bronze","Silver","Gold"}));
private ArrayList<String> checkBoxButtonsToCheck=
        new ArrayList<>(Arrays.asList(new String [] {"Water","Earth","Wind","Fire","Fire","Wind","Earth","Water" }));



@BeforeClass
public void checkLoginAndPrepareDiffElemPage(){

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
}


@Test
public void tryRadioButtons () {

    //Does RadioButtons exist on the page? If it does not the test is failed
     if (! differentElements.hasRadioButtons()) {
         assertTrue(false);
     }
     else {
         radioButtons = differentElements.getRadioButtons();
     }

     //click radio button and check log list.
     //The last line of log has to contain buttons name


     for (String radioButtonName : radioButtonsToCheck ) {
         radioButtons.clickRadioButton(radioButtonName);
         System.out.println(radioButtonName);


         if (! differentElements.lastLogRecordContains(radioButtonName))
             assertTrue(false);

     }
          assertTrue(true);


}

@Test
public void tryCheckBoxButtons() {

        //Does CheckBoxButtons exist on the page? If it does not the test is failed
    if (! differentElements.hasCheckBoxButtons()) {
        assertTrue(false);
    }
    else {
        checkBoxButtons = differentElements.getCheckBoxButtons();
    }

    //click checkbox button and check log list.
    //The last line of log has to contain buttons name


    for (String checkBoxButtonName : checkBoxButtonsToCheck ) {
        checkBoxButtons.clickCheckBoxButton(checkBoxButtonName);
        System.out.println(checkBoxButtonName);

        //if checkbox button is selected we are looking for substrings "checkBoxButtonName" and "true"
        //otherwise for substrings "checkBoxButtonName" and "false"

        String isSelectedTrueOrFalse = "trueOrfalse";

        if (checkBoxButtons.isSelectedCheckBoxButton(checkBoxButtonName)) {
            isSelectedTrueOrFalse = "true";
        } else {
            isSelectedTrueOrFalse = "false";
        }

         System.out.println(Boolean.toString(differentElements.lastLogRecordContains(checkBoxButtonName )));

        if ( ! differentElements.lastLogRecordContains(checkBoxButtonName, isSelectedTrueOrFalse)) {
            System.out.println(checkBoxButtonName+" "+isSelectedTrueOrFalse);
            assertTrue(false); }

    }

    assertTrue(true);

}

}
