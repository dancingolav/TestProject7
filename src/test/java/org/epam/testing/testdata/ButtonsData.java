package org.epam.testing.testdata;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;

/**
 *
 * Created by AlexSh on 17.10.2016.
 */


public class ButtonsData {
      @DataProvider(name="buttonsvalues")
       public static Object [][] createDataForButtons() {
           return new Object[][] {
                   {"Default Button"}, {"Button"},
                   { "Default Button"},{"Button"},
                   {"ButtonSXXX"}

           };
       }
}