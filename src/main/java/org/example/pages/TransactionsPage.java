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
        // App sorts newest first, so row index 0 is the latest transaction
        List<WebElement> cols = rows.get(0).findElements(By.xpath("./td"));
        if (cols.size() >= 3) return cols.get(2).getText();
        if (cols.size() >= 2) return cols.get(1).getText();
        return "";
    }

    public boolean isResetButtonPresent() {
        return elementHelper.isElementPresent(resetBtn);
    }
}