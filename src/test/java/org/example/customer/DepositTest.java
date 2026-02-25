package org.example.customer;

import io.qameta.allure.*;
import org.example.base.BaseTest;
import org.example.data.TestData;
import org.example.pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


@Epic("Customer Page")
@Feature("Transactions")
public class DepositTest extends BaseTest {

    @Test
    @DisplayName("Valid amount deposit updates balance correctly")
    @Story("Depositing valid fund")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that when customer deposits valid amount, the balance should update correctly")
    void testValidDeposit(){
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        assertEquals(TestData.DEPOSIT_AMOUNT, dashboard.getBalance(),"Should update balance with valid amount deposit");
    }

    @Test
    @DisplayName("Zero amount  deposit does not update balance")
    @Story("Depositing Funds with zero amount")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that when customer deposits zero amount, the balance should not update")
    void testDepositZeroAmount(){
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        String balanceBefore = dashboard.getBalance();
        dashboard.deposit(TestData.ZERO_AMOUNT);
        assertFalse(Boolean.parseBoolean(balanceBefore), dashboard.getBalance());
        assertEquals(TestData.ZERO_AMOUNT_ERROR_MESSAGE, "No error message found");
    }

    @Test
    @DisplayName("Negative amount deposit should display error message")
    @Story("Depositing Funds with negative amount")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that when customer deposits negative amount, there should be an error message displayed")
    void testDepositNegativeAmount(){
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        String balanceBefore = dashboard.getBalance();
        dashboard.deposit(TestData.NEGATIVE_AMOUNT);
        assertFalse(Boolean.parseBoolean(balanceBefore), dashboard.getBalance());
        assertEquals(TestData.NEGATIVE_AMOUNT_ERROR_MESSAGE, "No error message found");
    }

    @Test
    @DisplayName("Multiple amount deposits accumulate correctly")
    @Story("Depositing multiple funds")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that when customer deposits multiple amounts, the balance should update dynamically")
    void testMultipleDeposits() {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        assertEquals(TestData.DOUBLE_DEPOSIT_AMOUNT, dashboard.getBalance(), "Should update balance with multiple deposits");
    }
}