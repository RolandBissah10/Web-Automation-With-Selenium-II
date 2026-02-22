package org.example.pages;

import org.example.core.BasePage;
import org.example.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CustomerListPage extends BasePage {

    private final By customerRows = By.cssSelector("tr.ng-scope");

    public CustomerListPage(ElementHelper elementHelper) {
        super(elementHelper);
        new WebDriverWait(elementHelper.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(customerRows));
    }

    public void deleteCustomer(String customerName) {
        List<WebElement> rows = elementHelper.driver.findElements(customerRows);
        for (WebElement row : rows) {
            if (row.getText().contains(customerName)) {
                row.findElement(By.cssSelector("button[ng-click='deleteCust(cust)']")).click();
                return;
            }
        }
    }

    public boolean isCustomerPresent(String customerName) {
        List<WebElement> rows = elementHelper.driver.findElements(customerRows);
        for (WebElement row : rows) {
            if (row.getText().contains(customerName)) return true;
        }
        return false;
    }

    public int getCustomerCount() {
        return elementHelper.driver.findElements(customerRows).size();
    }
}