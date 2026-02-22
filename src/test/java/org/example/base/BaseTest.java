package org.example.base;

import org.example.config.AppUrls;
import org.example.utils.DriverFactory;
import org.example.utils.ElementHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        elementHelper.driver.quit();
    }
}