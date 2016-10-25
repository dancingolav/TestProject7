package org.epam.testing;

import org.epam.testing.pageobjects.EpamDifferentElementsPage;
import org.epam.testing.pageobjects.components.ButtonComponent;
import org.epam.testing.pageobjects.components.CheckBoxButtons;
import org.epam.testing.pageobjects.components.DropDownMenu;
import org.epam.testing.pageobjects.components.RadioButtons;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.epam.testing.LoginTest.epamLoginPage;
import static org.epam.testing.LoginTest.myPersonalDriver;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by AlexSh on 19.10.2016.
 */
public class DifferenElementsTests {

    private EpamDifferentElementsPage differentElements;
    private RadioButtons radioButtons;
    private CheckBoxButtons checkBoxButtons;
    private DropDownMenu dropDownMenu;
    private ButtonComponent buttonComponent;

    private ArrayList<String> radioButtonsToCheck =
            new ArrayList<>(Arrays.asList(new String[]{"Gold", "Silver", "Bronze", "Selen",
                                                       "Selen", "Bronze", "Silver", "Gold"}));
    private ArrayList<String> checkBoxButtonsToCheck =
            new ArrayList<>(Arrays.asList(new String[]{"Water", "Earth", "Wind", "Fire",
                                                        "Fire", "Wind", "Earth", "Water"}));
    private ArrayList<String> dropDownMenuOptions =
            new ArrayList<>(Arrays.asList(new String[]{"Red", "Green", "Blue", "Yellow",
                                                        "Yellow", "Blue", "Green", "Red"}));
    private ArrayList<String> buttonsCompomentToCheck =
            new ArrayList<>(Arrays.asList(new String[]{"Default Button", "Button",
                                                        "Default Button", "Button" }));

    @BeforeClass
    public void checkLoginAndPrepareDiffElemPage() {

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
    public void tryRadioButtons() {
        System.out.println("RADIO BUTTONS TEST");
        //Does RadioButtons exist on the page? If it does not the test is failed
        if (!differentElements.hasRadioButtons()) {
            assertTrue(false);
        } else {
            radioButtons = differentElements.getRadioButtons();
        }

        //click radio button and check log list.
        //The last line of log has to contain buttons name


        for (String radioButtonName : radioButtonsToCheck) {
            radioButtons.clickRadioButton(radioButtonName);
            System.out.println(radioButtonName);

            //Looking in logs for button's name
            if (!differentElements.lastLogRecordContains(radioButtonName))
                assertTrue(false);

        }
        assertTrue(true);


    }

    @Test
    public void tryCheckBoxButtons() {
        System.out.println("CHECKBOX BUTTONS TEST");
        //Does CheckBoxButtons exist on the page? If it does not the test is failed
        if (!differentElements.hasCheckBoxButtons()) {
            assertTrue(false);
        } else {
            checkBoxButtons = differentElements.getCheckBoxButtons();
        }

        //click checkbox button and check log list.
        //The last line of log has to contain buttons name


        for (String checkBoxButtonName : checkBoxButtonsToCheck) {
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

            //Looking in logs for button's name and "true" (selected) or "false" (unselected)
            if (!differentElements.lastLogRecordContains(checkBoxButtonName, isSelectedTrueOrFalse)) {
                System.out.println(checkBoxButtonName + " " + isSelectedTrueOrFalse);
                assertTrue(false);
            }

        }

        assertTrue(true);

    }

    @Test
    public void tryDropDownMenu() {

        System.out.println("DROPDOWN MENU TEST");
        if (!differentElements.hasDropDownMenu()) {
            assertTrue(false);
        } else {
            dropDownMenu = differentElements.getDropDownMenu();
        }


        for (String dropDownOption : dropDownMenuOptions) {
            dropDownMenu.clickDropDownMenu(dropDownOption);

            System.out.println(dropDownOption);

            //If the chosen option is in "selected" state we will not see any changes in logs
            if (!dropDownMenu.isSelectedDropDownMenuOption(dropDownOption)) {
                //Looking in logs for dropdown menu option
                if (!differentElements.lastLogRecordContains(dropDownOption))
                    assertTrue(false);
            }
        }
        assertTrue(true);
    }

    @Test
    public void tryButtons() {

        System.out.println("BUTTONS TEST");


        assertTrue(differentElements.hasButtonS());
        buttonComponent = differentElements.getButtonComponent();

        for (String buttonValue :buttonsCompomentToCheck) {
             buttonComponent.clickButtonFromComponent(buttonValue);
            //Click button and check log. It is our test.
             assertTrue(differentElements.lastLogRecordContains(buttonValue, "button clicked"));

        }
        assertTrue(true);
    }

}
