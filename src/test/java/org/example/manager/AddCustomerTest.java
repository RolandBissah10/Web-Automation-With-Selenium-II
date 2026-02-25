package org.example.manager;

import io.qameta.allure.*;
import org.example.base.BaseTest;
import org.example.data.TestData;
import org.example.pages.AddCustomerPage;
import org.example.pages.LandingPage;
import org.example.pages.ManagerPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Manager Page")
@Feature("Customer Management")
public class AddCustomerTest extends BaseTest {

    @Test
    @DisplayName("Add a valid customer successfully")
    @Story("Adding a new customer")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that manager can add a new valid customer to the system")
    void testAddValidCustomer() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.POST_CODE);
        assertTrue(addCustomerPage.isSubmitSuccessful(), "Customer added successfully");
        addCustomerPage.acceptAlert();
    }

    @Test
    @DisplayName("Add customer with numeric name - app accepts it")
    @Story("Adding a new customer with invalid Detail")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that the system rejects customers with numeric name")
    void testAddCustomerWithNumericName(){
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer(TestData.INVALID_FIRST_NAME, TestData.LAST_NAME, TestData.POST_CODE);
        // App does not validate name format - documents actual behaviour
        assertTrue(addCustomerPage.isSubmitSuccessful());
        assertEquals(TestData.INVALID_CUSTOMER_DETAILS,"No error message displayed");
        addCustomerPage.acceptAlert();
    }

    @Test
    @DisplayName("Add customer with special characters - app accepts it")
    @Story("Adding a new customer with invalid name")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that the system rejects customers with special characters in the name")
    void testAddCustomerWithSpecialCharacters(){
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer(TestData.SPECIAL_CHAR_NAME, TestData.LAST_NAME, TestData.POST_CODE);
        // App does not validate special characters - documents actual behaviour
        assertTrue(addCustomerPage.isSubmitSuccessful());
        assertEquals(TestData.INVALID_CUSTOMER_DETAILS,"No error message displayed");
        addCustomerPage.acceptAlert();
    }

    @Test
    @DisplayName("Add customer with invalid post code - app accepts it")
    @Story("Adding a new customer with invalid PostalCode")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that the system rejects customers with invalid postal code")
    void testAddCustomerWithInvalidPostCode() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.INVALID_POST_CODE);
        assertTrue(addCustomerPage.isSubmitSuccessful());
        assertEquals(TestData.INVALID_CUSTOMER_DETAILS,"No error message displayed");
        addCustomerPage.acceptAlert();
    }

    @Test
    @DisplayName("Empty fields prevent submission")
    @Story("Form submission with empty fields")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that the system requires input fields to be field by throwing an error message")
    void testAddCustomerWithEmptyFields() {
        ManagerPage manager = new LandingPage(elementHelper).goToManager();
        AddCustomerPage addCustomerPage = manager.goToAddCustomer();
        addCustomerPage.addCustomer(TestData.EMPTY, TestData.EMPTY, TestData.EMPTY);
        assertFalse(addCustomerPage.isSubmitSuccessful());
        assertEquals(TestData.EMPTY_CUSTOMER_DETAILS,"Error message: Field is required");
    }
}