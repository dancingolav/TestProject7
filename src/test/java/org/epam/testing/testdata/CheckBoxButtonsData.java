package org.epam.testing.testdata;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;

/**
 *
 * Created by AlexSh on 17.10.2016.
 */


public class CheckBoxButtonsData {
    @DataProvider(name = "checkboxbuttons")
    public static Object[][] createDataForCheckBox() {
        return new Object[][]{
                {"Water"}, {"Earth"}, {"Wind"}, {"Fire"},
                {"Fire"}, {"Wind"}, {"Earth"}, {"Water"},
                {"Wind"}

        };

    }
}
