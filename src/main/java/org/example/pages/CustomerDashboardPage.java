package org.example.pages;

import org.example.core.BasePage;
import org.example.helper.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CustomerDashboardPage extends BasePage {

    private final By depositTab      = By.cssSelector("button[ng-click='deposit()']");
    private final By withdrawTab     = By.cssSelector("button[ng-click='withdrawl()']");
    private final By transactionsTab = By.cssSelector("button[ng-click='transactions()']");
    private final By amountField     = By.xpath("//input[@type='number']");
    private final By balanceText     = By.xpath("(//strong[@class='ng-binding'])[2]");

    public CustomerDashboardPage(ElementHelper elementHelper) {
        super(elementHelper);
        new WebDriverWait(elementHelper.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(depositTab));
    }

    public void deposit(String amount) {
        elementHelper.click(depositTab);
        sleep(500);
        typeAmount(amount);
        clickSubmit();
        sleep(1500);
    }

    public void withdraw(String amount) {
        elementHelper.click(withdrawTab);
        sleep(500); // Wait for Angular to switch form context
        typeAmount(amount);
        clickSubmit();
        sleep(2000); // waits 3 seconds after submission because Withdrawal takes slightly longer for Angular to process and update the balance
    }

    private void typeAmount(String amount) {
        WebElement field = new WebDriverWait(elementHelper.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(amountField));
        ((JavascriptExecutor) elementHelper.driver).executeScript(
                "arguments[0].value = arguments[1];" +
                        "angular.element(arguments[0]).triggerHandler('input');",
                field, amount);
    }

    private void clickSubmit() {
        WebElement btn = new WebDriverWait(elementHelper.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("button.btn-default[type='submit']")));
        ((JavascriptExecutor) elementHelper.driver).executeScript("arguments[0].click();", btn);
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public String getBalance() {
        return elementHelper.getText(balanceText).trim().replace(",", "");
    }

    public TransactionsPage goToTransactions() {
        sleep(2000); // wait for 2 seconds to ensure all transactions have been recorded by the application
        elementHelper.click(transactionsTab);
        return new TransactionsPage(elementHelper);
    }
}