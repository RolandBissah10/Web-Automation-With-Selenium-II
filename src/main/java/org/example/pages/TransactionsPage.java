package org.example.pages;

import org.example.core.BasePage;
import org.example.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class TransactionsPage extends BasePage {

    private final By transactionRows = By.xpath("//tr[contains(@ng-repeat,'tx in transactions')]");
    private final By resetBtn        = By.cssSelector("button[ng-click='reset()']");

    public TransactionsPage(ElementHelper elementHelper) {
        super(elementHelper);
        new WebDriverWait(elementHelper.driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(transactionRows, 0));
    }

    public int getTransactionCount() {
        return elementHelper.driver.findElements(transactionRows).size();
    }

    public String getLatestTransactionType() {
        List<WebElement> rows = elementHelper.driver.findElements(transactionRows);
        if (rows.isEmpty()) return "";
        // App shows newest transaction first
        return rows.get(0).findElement(By.xpath("./td[3]")).getText();
    }

    public boolean hasTransactionType(String type) {
        List<WebElement> rows = elementHelper.driver.findElements(transactionRows);
        for (WebElement row : rows) {
            if (row.findElement(By.xpath("./td[3]")).getText().equals(type)) {
                return true;
            }
        }
        return false;
    }

    public boolean isResetButtonPresent() {
        return elementHelper.isElementPresent(resetBtn);
    }
}