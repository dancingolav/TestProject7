package org.epam.testing;

import org.epam.testing.pageobjects.EpamDifferentElementsPage;
import org.epam.testing.components.FailureListener;
import org.epam.testing.components.CheckBoxButtons;
import org.epam.testing.components.DropDownMenu;
import org.epam.testing.testdata.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;
import static org.epam.testing.LoginTest.epamLoginPage;
import static org.epam.testing.LoginTest.myPersonalDriver;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by AlexSh on 19.10.2016.
 */
@Listeners({ FailureListener.class })
public class DifferenElementsTests {

    private EpamDifferentElementsPage differentElements;

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
        if (!epamLoginPage.isLoggedIn())  assertTrue(false);

        differentElements = new EpamDifferentElementsPage(myPersonalDriver);
        differentElements.open();
    }

    @Step("TESTING RADIO BUTTONS")
    @Test (dataProviderClass=RadioButtonsData.class, dataProvider="radiobuttons")
    public void tryRadioButtons(String radioButtonName) {

        System.out.println("RADIO BUTTONS TEST");
        //Does RadioButtons exist on the page? If it does not the test is failed
        if (!differentElements.hasRadioButtons()) assertTrue(false);

        //click radio button and check log list.
        //The last line of log has to contain buttons name
        differentElements.getRadioButtons().clickRadioButton(radioButtonName);;

        //Looking in logs for radio button's name
        assertTrue(differentElements.lastLogRecordContains(radioButtonName));
     }

    @Step("TESTING CHECKBOX BUTTONS")
    @Test (dataProviderClass=CheckBoxButtonsData.class, dataProvider="checkboxbuttons")
    public void tryCheckBoxButtons(String checkBoxButtonName ) {

        System.out.println("CHECKBOX BUTTONS TEST");

        //Does CheckBoxButtons exist on the page? If it does not the test is failed
        if (!differentElements.hasCheckBoxButtons()) assertTrue(false);

        CheckBoxButtons checkBoxButtons = differentElements.getCheckBoxButtons();

        //click checkbox button and check log list.
        //The last line of log has to contain buttons name
        checkBoxButtons.clickCheckBoxButton(checkBoxButtonName);;

        //if checkbox button is selected we are looking for substrings "checkBoxButtonName" and "true"
        //otherwise for substrings "checkBoxButtonName" and "false"

         String isSelectedTrueOrFalse =
                 (checkBoxButtons.isSelectedCheckBoxButton(checkBoxButtonName))? "true": "false";

            //Looking in logs for button's name and "true" (selected) or "false" (unselected)
            //The method is using varang, so we can add parameters (substrings to look for) if we need

         assertTrue(differentElements.lastLogRecordContains(checkBoxButtonName, isSelectedTrueOrFalse));
    }
    @Step("TESTING DROPDOWN MENU")
    @Test (dataProviderClass=DropDownMenuOptionsData.class, dataProvider="dropdownmenuoptions")
    public void tryDropDownMenu(String dropDownOption) {

        System.out.println("DROPDOWN MENU TEST");

        //First part of test. Do we have smth to test on the page?
        if (!differentElements.hasDropDownMenu()) assertTrue(false);

        DropDownMenu dropDownMenu = differentElements.getDropDownMenu();

        //If the chosen option is already in "selected" state we will not see any changes in logs
        //It's OK but if it is not in "selected" state we will check....

        if (dropDownMenu.isSelectedDropDownMenuOption(dropDownOption))
            assertTrue(true);
        else  {

            dropDownMenu.clickDropDownMenu(dropDownOption);
             //Looking in logs for dropdown menu option
            assertTrue (differentElements.lastLogRecordContains(dropDownOption));
        }


    }
    @Step ("Button \"{0}\"")
    @Test (dataProviderClass=ButtonsData.class, dataProvider="buttonsvalues")
    public void tryButtons(String buttonValue) {

        System.out.println("BUTTONS TEST");

        //First part of test. Do we have smth to test on the page?
        if (!differentElements.hasButton(buttonValue)) assertTrue(false);

        differentElements.getButtonComponent().clickButtonFromComponent(buttonValue);
        //Click button and check log. It is our test.
        assertTrue(differentElements.lastLogRecordContains(buttonValue, "button clicked"));

      }


}
