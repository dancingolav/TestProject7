package org.epam.testing.components;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;


import java.io.File;

import static java.lang.Thread.*;
import static org.epam.testing.LoginTest.myPersonalDriver;

/**
 * Created by AlexSh on 27.10.2016.
 */
public class FailureListener extends TestListenerAdapter {

    @Step("A Failure Listener")
    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println("I see you");


        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        makeScreenshot();
    }


    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        byte[] ScreenShot = ((TakesScreenshot) myPersonalDriver).getScreenshotAs(OutputType.BYTES);

        return ScreenShot;
    }



}


