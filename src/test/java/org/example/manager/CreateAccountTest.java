package org.example.manager;

import org.example.base.BaseTest;
import org.example.data.TestData;
import org.example.pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CreateAccountTest extends BaseTest {

    @Test
    @DisplayName("Create a Dollar account for Harry Potter")
    void testCreateDollarAccount() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        OpenAccountPage openAccountPage = manager.goToOpenAccount();
        openAccountPage.openAccount(TestData.CUSTOMER_HARRY, TestData.CURRENCY_DOLLAR);
        assertTrue(openAccountPage.getAlertMessage().contains("Account created successfully"));
        openAccountPage.acceptAlert();
    }

    @Test
    @DisplayName("Create a Pound account for Ron Weasley")
    void testCreatePoundAccount() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();

        // Add Ron Weasley first to ensure he exists
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer("Ron", "Weasley", "11111");
        addCustomerPage.acceptAlert();

        OpenAccountPage openAccountPage = manager.goToOpenAccount();
        openAccountPage.openAccount(TestData.CUSTOMER_RON, TestData.CURRENCY_POUND);
        assertTrue(openAccountPage.getAlertMessage().contains("Account created successfully"));
        openAccountPage.acceptAlert();
    }

    @Test
    @DisplayName("Create a Rupee account for Hermoine Granger")
    void testCreateRupeeAccount() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        OpenAccountPage openAccountPage = manager.goToOpenAccount();
        openAccountPage.openAccount(TestData.CUSTOMER_HERMOINE, TestData.CURRENCY_RUPEE);
        assertTrue(openAccountPage.getAlertMessage().contains("Account created successfully"));
        openAccountPage.acceptAlert();
    }

    @Test
    @DisplayName("Customer without account is not available in login dropdown")
    void testCustomerCannotLoginWithoutAccount() {
        CustomerLoginPage customerLoginPage = new LandingPage(elementHelper).goToCustomer();
        boolean customerAvailable = customerLoginPage.isCustomerAvailable(TestData.CUSTOMER_WITHOUT_ACCOUNT);
        assertFalse(customerAvailable);
    }
}