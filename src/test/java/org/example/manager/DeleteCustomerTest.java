package org.example.manager;

import io.qameta.allure.*;
import org.example.base.BaseTest;
import org.example.data.TestData;
import org.example.pages.CustomerListPage;
import org.example.pages.CustomerLoginPage;
import org.example.pages.LandingPage;
import org.example.pages.ManagerPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Manager Page")
@Feature("Customer Management")
public class DeleteCustomerTest extends BaseTest {

    @Test
    @DisplayName("Delete an existing customer successfully")
    @Story("Deleting an existing customer")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that manager can delete existing customer on the system")
    void testDeleteExistingCustomer() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        CustomerListPage customerListPage = manager.goToCustomerList();
        customerListPage.deleteCustomer(TestData.CUSTOMER_HARRY);
        assertFalse(customerListPage.isCustomerPresent(TestData.CUSTOMER_HARRY), "Customer not found");
    }

    @Test
    @DisplayName("Deleted customer can no longer log in")
    @Story("Deleted customer can not log in")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that when manager deletes an existing customer, the customer shouldn't be able to log in to the system")
    void testDeletedCustomerCannotLogin() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        CustomerListPage customerListPage = manager.goToCustomerList();
        customerListPage.deleteCustomer(TestData.CUSTOMER_RON);

        CustomerLoginPage customerLoginPage = new LandingPage(elementHelper).goToCustomer();
        assertFalse(customerLoginPage.isCustomerAvailable(TestData.CUSTOMER_RON),"Customer cannot log in");
    }

    @Test
    @DisplayName("Customer list count decreases after deletion")
    @Story("Deleting an existing customer updates list")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies the customer lists updates when a customer is deleted system")
    void testCustomerListUpdatesAfterDeletion() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        CustomerListPage customerListPage = manager.goToCustomerList();
        int countBefore = customerListPage.getCustomerCount();
        customerListPage.deleteCustomer(TestData.CUSTOMER_HERMOINE);
        int countAfter = customerListPage.getCustomerCount();
        assertEquals(countBefore - 1, countAfter);
    }
}