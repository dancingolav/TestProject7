package org.epam.testing.components;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;


import static org.epam.testing.LoginTestSteps.myPersonalDriver;

/**
 * Created by AlexSh on 27.10.2016.
 */
public class FailureListener extends TestListenerAdapter {

    @Step("A Failure Listener")
    @Override
    public void onTestFailure(ITestResult tr) {
        try {
            System.out.println("I want a picture");
            makeScreenshot();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }


   @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshot() {
    try {
            Thread.sleep(1500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
          return ((TakesScreenshot) myPersonalDriver).getScreenshotAs(OutputType.BYTES);
    }

 }
