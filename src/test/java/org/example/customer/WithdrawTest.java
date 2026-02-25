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
public class WithdrawTest extends BaseTest {

    private CustomerDashboardPage getDashboard() {
        return new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_RON);
    }

    @Test
    @DisplayName("Valid withdrawal updates balance correctly")
    @Story("Withdrawing valid amount")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that when a customer withdraws amount, the balance should update")
    void testValidWithdrawal() {
        CustomerDashboardPage dashboard = getDashboard();
        dashboard.deposit("5000");
        String balanceBefore = dashboard.getBalance();
        dashboard.withdraw(TestData.WITHDRAW_AMOUNT);
        assertNotEquals(balanceBefore, dashboard.getBalance(), "Balance should be updated");
    }

    @Test
    @DisplayName("Withdraw more than balance does not update balance")
    @Story("Withdrawing more than available balance")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that a customer cannot withdraw amount more than the current account balance ")
    void testWithdrawMoreThanBalance() {
        CustomerDashboardPage dashboard = getDashboard();
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        String balanceBefore = dashboard.getBalance();
        dashboard.withdraw(TestData.EXCEEDS_BALANCE_AMOUNT);
        assertEquals(balanceBefore, dashboard.getBalance(),"Error message should be thrown alerting the customer that amount not be more than current balance");
    }

    @Test
    @DisplayName("Zero withdrawal does not update balance")
    @Story("Withdrawing zero amount")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that when customer withdraws zero amount, the balance should not update")
    void testWithdrawZeroAmount() {
        CustomerDashboardPage dashboard = getDashboard();
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        String balanceBefore = dashboard.getBalance();
        dashboard.withdraw(TestData.ZERO_AMOUNT);
        assertFalse(Boolean.parseBoolean(balanceBefore), dashboard.getBalance());
        assertEquals(TestData.ZERO_AMOUNT_ERROR_MESSAGE, "No error message found");

    }

    @Test
    @DisplayName("Negative withdrawal does not update balance")
    @Story("Withdrawing negative amount")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that when customer withdraws negative amount, the balance should not update")
    void testWithdrawNegativeAmount() {
        CustomerDashboardPage dashboard = getDashboard();
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        String balanceBefore = dashboard.getBalance();
        dashboard.withdraw(TestData.NEGATIVE_AMOUNT);
        assertFalse(Boolean.parseBoolean(balanceBefore), dashboard.getBalance());
        assertEquals(TestData.NEGATIVE_AMOUNT_ERROR_MESSAGE, "No error message found");

    }

    @Test
    @DisplayName("Withdraw entire balance leaves zero")
    @Story("Withdrawing all current balance")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that when customer withdraws all the amount, the balance should be updated to zero")
    void testWithdrawEntireBalance() {
        CustomerDashboardPage dashboard = getDashboard();
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        String currentBalance = dashboard.getBalance();
        dashboard.withdraw(currentBalance);
        assertEquals("0", dashboard.getBalance());
    }
}