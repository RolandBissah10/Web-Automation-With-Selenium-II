package org.example.manager;

import io.qameta.allure.*;
import org.example.base.BaseTest;
import org.example.data.TestData;
import org.example.pages.homePage.CustomerLoginPage;
import org.example.pages.homePage.LandingPage;
import org.example.pages.managerPage.ManagerPage;
import org.example.pages.managerPage.OpenAccountPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
@Epic("Manage Page")
@Feature("Customer Account Management")
public class CreateAccountTest extends BaseTest {

    @Test
    @DisplayName("Create a Dollar account for Harry Potter")
    @Story("Creating customer account with dollar currency")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that customer's account is created with dollar currency")
    void testCreateDollarAccount() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        OpenAccountPage openAccountPage = manager.goToOpenAccount();
        openAccountPage.openAccount(TestData.CUSTOMER_HARRY, TestData.CURRENCY_DOLLAR);
        assertTrue(openAccountPage.getAlertMessage().contains("Account created successfully"));
        openAccountPage.acceptAlert();
    }

    @Test
    @DisplayName("Create a Pound account for Ron Weasley")
    @Story("Creating customer account with Pound currency")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that customer's account is created with pound currency")
    void testCreatePoundAccount() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        OpenAccountPage openAccountPage = manager.goToOpenAccount();
        openAccountPage.openAccount(TestData.CUSTOMER_RON, TestData.CURRENCY_POUND);
        assertTrue(openAccountPage.getAlertMessage().contains("Account created successfully"));
        openAccountPage.acceptAlert();
    }

    @Test
    @DisplayName("Create a Rupee account for Hermoine Granger")
    @Story("Creating customer account with dollar currency")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that customer's account is created with Rupee currency")
    void testCreateRupeeAccount() throws InterruptedException {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        OpenAccountPage openAccountPage = manager.goToOpenAccount();
        openAccountPage.openAccount(TestData.CUSTOMER_HERMOINE, TestData.CURRENCY_RUPEE);
        assertTrue(openAccountPage.getAlertMessage().contains("Account created successfully"));
        openAccountPage.acceptAlert();
    }

    @Test
    @DisplayName("Customer without account is not available in login dropdown")
    @Story(" Customer without account trying to login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that the system rejects customers without account to log in")
    void testCustomerCannotLoginWithoutAccount() {
        CustomerLoginPage customerLoginPage = new LandingPage(elementHelper).goToCustomer();
        boolean customerAvailable = customerLoginPage.isCustomerAvailable(TestData.CUSTOMER_WITHOUT_ACCOUNT);
        assertFalse(customerAvailable);
    }
}