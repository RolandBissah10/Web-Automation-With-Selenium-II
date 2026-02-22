package org.example.pages;

import org.example.core.BasePage;
import org.example.utils.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddCustomerPage extends BasePage {

    private final By firstNameField = By.cssSelector("input[ng-model='fName']");
    private final By lastNameField  = By.cssSelector("input[ng-model='lName']");
    private final By postCodeField  = By.cssSelector("input[ng-model='postCd']");
    private final By submitBtn      = By.cssSelector("button[type='submit']");

    public AddCustomerPage(ElementHelper elementHelper) {
        super(elementHelper);
        new WebDriverWait(elementHelper.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
    }

    public void addCustomer(String firstName, String lastName, String postCode) {
        elementHelper.type(firstNameField, firstName);
        elementHelper.type(lastNameField, lastName);
        elementHelper.type(postCodeField, postCode);
        elementHelper.click(submitBtn);
    }

    public String getAlertMessage() {
        return elementHelper.driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        elementHelper.driver.switchTo().alert().accept();
    }

    public boolean isSubmitSuccessful() {
        try {
            new WebDriverWait(elementHelper.driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}