package org.example.manager;

import org.example.base.BaseTest;
import org.example.data.TestData;
import org.example.pages.AddCustomerPage;
import org.example.pages.LandingPage;
import org.example.pages.ManagerPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddCustomerTest extends BaseTest {

    @Test
    @DisplayName("Add a valid customer successfully")
    void testAddValidCustomer() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POST_CODE);
        assertTrue(addCustomerPage.isSubmitSuccessful());
        addCustomerPage.acceptAlert();
    }

    @Test
    @DisplayName("Add customer with numeric name - app accepts it")
    void testAddCustomerWithNumericName() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer(TestData.INVALID_FIRST_NAME, TestData.LAST_NAME, TestData.POST_CODE);
        // App does not validate name format - documents actual behaviour
        assertTrue(addCustomerPage.isSubmitSuccessful());
        addCustomerPage.acceptAlert();
    }

    @Test
    @DisplayName("Add customer with special characters - app accepts it")
    void testAddCustomerWithSpecialCharacters() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer(TestData.SPECIAL_CHAR_NAME, TestData.LAST_NAME, TestData.POST_CODE);
        // App does not validate special characters - documents actual behaviour
        assertTrue(addCustomerPage.isSubmitSuccessful());
        addCustomerPage.acceptAlert();
    }

    @Test
    @DisplayName("Add customer with invalid post code - app accepts it")
    void testAddCustomerWithInvalidPostCode() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.INVALID_POST_CODE);
        // App does not validate post code format - documents actual behaviour
        assertTrue(addCustomerPage.isSubmitSuccessful());
        addCustomerPage.acceptAlert();
    }

    @Test
    @DisplayName("Empty fields prevent submission")
    void testAddCustomerWithEmptyFields() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer(TestData.EMPTY, TestData.EMPTY, TestData.EMPTY);
        assertFalse(addCustomerPage.isSubmitSuccessful());
    }
}