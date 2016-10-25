package org.epam.testing.testdata;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;

/**
 * Created by AlexSh on 17.10.2016.
 */



public class DropDownMenuOptionsData {
      @DataProvider(name="dropdownmenuoptions")
       public static Object [][] createDataForDropDownMenu() {
           return new Object[][] {
                   { "Red"}, {"Green"},  {"Blue" },  {"Yellow"},
                   { "Yellow"}, {"Blue" }, {"Green"}, {"Red"}, {"Blue"}
           };
       }
}
