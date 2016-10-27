package org.epam.testing.pageobjects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import static org.epam.testing.LoginTest.myPersonalDriver;

/**
 * Created by AlexSh on 27.10.2016.
 */
public class FailureListener extends TestListenerAdapter{

        @Step("A Failure Listener")
        @Override
        public void onTestFailure(ITestResult tr) {
            System.out.println("I see you");
            makeScreenshot();
        }

        @Attachment(value = "Page screenshot", type = "image/png")

        public byte[] makeScreenshot() {
            return ((TakesScreenshot) myPersonalDriver).getScreenshotAs(OutputType.BYTES);
        }

}


