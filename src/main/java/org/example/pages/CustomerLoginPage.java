package org.example.pages;

import org.example.core.BasePage;
import org.example.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CustomerLoginPage extends BasePage {

    private final By customerDropdown = By.id("userSelect");
    private final By loginBtn         = By.cssSelector("button.btn-default[type='submit']");

    public CustomerLoginPage(ElementHelper elementHelper) {
        super(elementHelper);
        new WebDriverWait(elementHelper.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(customerDropdown));
    }

    public CustomerDashboardPage loginAs(String customerName) {
        new Select(elementHelper.driver.findElement(customerDropdown))
                .selectByVisibleText(customerName);
        elementHelper.click(loginBtn);
        return new CustomerDashboardPage(elementHelper);
    }

    public boolean isCustomerAvailable(String customerName) {
        return elementHelper.isElementPresent(
                By.xpath("//option[text()='" + customerName + "']")
        );
    }
}