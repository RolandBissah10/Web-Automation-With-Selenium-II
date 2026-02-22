package org.example.pages;

import org.example.core.BasePage;
import org.example.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ManagerPage extends BasePage {

    private final By addCustomerBtn  = By.cssSelector("button[ng-click='addCust()']");
    private final By openAccountBtn  = By.cssSelector("button[ng-click='openAccount()']");
    private final By showCustomerBtn = By.cssSelector("button[ng-click='showCust()']");

    public ManagerPage(ElementHelper elementHelper) {
        super(elementHelper);
        new WebDriverWait(elementHelper.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(addCustomerBtn));
    }

    public AddCustomerPage goToAddCustomer() {
        elementHelper.click(addCustomerBtn);
        return new AddCustomerPage(elementHelper);
    }

    public OpenAccountPage goToOpenAccount() {
        elementHelper.click(openAccountBtn);
        return new OpenAccountPage(elementHelper);
    }

    public CustomerListPage goToCustomerList() {
        elementHelper.click(showCustomerBtn);
        return new CustomerListPage(elementHelper);
    }
}