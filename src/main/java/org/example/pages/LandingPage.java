package org.example.pages;

import org.example.config.AppUrls;
import org.example.core.BasePage;
import org.example.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LandingPage extends BasePage {

    private final By managerLoginBtn  = By.cssSelector("button[ng-click='manager()']");
    private final By customerLoginBtn = By.cssSelector("button[ng-click='customer()']");

    public LandingPage(ElementHelper elementHelper) {
        super(elementHelper);
    }

    public ManagerPage goToManager() {
        elementHelper.navigateTo(AppUrls.HOME);
        new WebDriverWait(elementHelper.driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(managerLoginBtn));
        elementHelper.click(managerLoginBtn);
        return new ManagerPage(elementHelper);
    }

    public CustomerLoginPage goToCustomer() {
        elementHelper.navigateTo(AppUrls.HOME);
        new WebDriverWait(elementHelper.driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(customerLoginBtn));
        elementHelper.click(customerLoginBtn);
        return new CustomerLoginPage(elementHelper);
    }
}