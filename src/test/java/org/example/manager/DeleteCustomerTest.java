package org.example.manager;

import org.example.base.BaseTest;
import org.example.data.TestData;
import org.example.pages.CustomerListPage;
import org.example.pages.CustomerLoginPage;
import org.example.pages.LandingPage;
import org.example.pages.ManagerPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteCustomerTest extends BaseTest {

    @Test
    @DisplayName("Delete an existing customer successfully")
    void testDeleteExistingCustomer() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        CustomerListPage customerListPage = manager.goToCustomerList();
        customerListPage.deleteCustomer(TestData.CUSTOMER_HARRY);
        assertFalse(customerListPage.isCustomerPresent(TestData.CUSTOMER_HARRY));
    }

    @Test
    @DisplayName("Deleted customer can no longer log in")
    void testDeletedCustomerCannotLogin() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        CustomerListPage customerListPage = manager.goToCustomerList();
        customerListPage.deleteCustomer(TestData.CUSTOMER_RON);

        CustomerLoginPage customerLoginPage = new LandingPage(elementHelper).goToCustomer();
        assertFalse(customerLoginPage.isCustomerAvailable(TestData.CUSTOMER_RON));
    }

    @Test
    @DisplayName("Customer list count decreases after deletion")
    void testCustomerListUpdatesAfterDeletion() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        CustomerListPage customerListPage = manager.goToCustomerList();
        int countBefore = customerListPage.getCustomerCount();
        customerListPage.deleteCustomer(TestData.CUSTOMER_HERMOINE);
        int countAfter = customerListPage.getCustomerCount();
        assertEquals(countBefore - 1, countAfter);
    }
}