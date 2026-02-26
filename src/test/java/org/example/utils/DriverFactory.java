package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
        } else {
            options.addArguments("--start-maximized");
        }

        return new ChromeDriver(options);
    }
}