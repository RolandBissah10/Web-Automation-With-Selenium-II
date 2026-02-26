package org.example.pages.managerPage;

import org.example.core.BasePage;
import org.example.helper.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OpenAccountPage extends BasePage {

    private final By customerDropdown = By.id("userSelect");
    private final By currencyDropdown = By.id("currency");
    private final By submitBtn        = By.cssSelector("button[type='submit']");

    public OpenAccountPage(ElementHelper elementHelper) {
        super(elementHelper);
        new WebDriverWait(elementHelper.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(customerDropdown));
    }

    public void openAccount(String customerName, String currency) {
        new Select(elementHelper.driver.findElement(customerDropdown))
                .selectByVisibleText(customerName);
        new Select(elementHelper.driver.findElement(currencyDropdown))
                .selectByVisibleText(currency);
        elementHelper.click(submitBtn);
    }

    public String getAlertMessage() {
        return elementHelper.driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        elementHelper.driver.switchTo().alert().accept();
    }
}