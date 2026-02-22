package org.example.customer;

import org.example.base.BaseTest;
import org.example.data.TestData;
import org.example.pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WithdrawTest extends BaseTest {

    private CustomerDashboardPage getDashboard() {
        return new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_RON);
    }

    @Test
    @DisplayName("Valid withdrawal updates balance correctly")
    void testValidWithdrawal() {
        CustomerDashboardPage dashboard = getDashboard();
        dashboard.deposit("5000");
        String balanceBefore = dashboard.getBalance();
        dashboard.withdraw(TestData.WITHDRAW_AMOUNT);
        assertNotEquals(balanceBefore, dashboard.getBalance());
    }

    @Test
    @DisplayName("Withdraw more than balance does not update balance")
    void testWithdrawMoreThanBalance() {
        CustomerDashboardPage dashboard = getDashboard();
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        String balanceBefore = dashboard.getBalance();
        dashboard.withdraw(TestData.EXCEEDS_BALANCE_AMOUNT);
        assertEquals(balanceBefore, dashboard.getBalance());
    }

    @Test
    @DisplayName("Zero withdrawal does not update balance")
    void testWithdrawZeroAmount() {
        CustomerDashboardPage dashboard = getDashboard();
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        String balanceBefore = dashboard.getBalance();
        dashboard.withdraw(TestData.ZERO_AMOUNT);
        assertEquals(balanceBefore, dashboard.getBalance());
    }

    @Test
    @DisplayName("Negative withdrawal does not update balance")
    void testWithdrawNegativeAmount() {
        CustomerDashboardPage dashboard = getDashboard();
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        String balanceBefore = dashboard.getBalance();
        dashboard.withdraw(TestData.NEGATIVE_AMOUNT);
        assertEquals(balanceBefore, dashboard.getBalance());
    }

    @Test
    @DisplayName("Withdraw entire balance leaves zero")
    void testWithdrawEntireBalance() {
        CustomerDashboardPage dashboard = getDashboard();
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        String currentBalance = dashboard.getBalance();
        dashboard.withdraw(currentBalance);
        assertEquals("0", dashboard.getBalance());
    }
}