package org.example.base;

import io.qameta.allure.Allure;
import org.example.config.AppUrls;
import org.example.utils.DriverFactory;
import org.example.helper.ElementHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected ElementHelper elementHelper;

    @BeforeEach
    void setUp() {
        WebDriver driver = DriverFactory.createDriver();
        elementHelper    = new ElementHelper(driver);
        elementHelper.navigateTo(AppUrls.HOME);
    }

    @AfterEach
    void tearDown() {
        try {
            if (elementHelper.driver instanceof TakesScreenshot) {
                byte[] png = ((TakesScreenshot) elementHelper.driver).getScreenshotAs(OutputType.BYTES);
                Allure.getLifecycle().addAttachment("Final screenshot", "image/png", "png", png);
            }
        } catch (Exception ignored) {
        }
        elementHelper.driver.quit();
    }
}