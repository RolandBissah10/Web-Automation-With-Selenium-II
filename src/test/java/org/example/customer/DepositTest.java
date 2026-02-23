package org.example.customer;

import org.example.base.BaseTest;
import org.example.data.TestData;
import org.example.pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepositTest extends BaseTest {

    @Test
    @DisplayName("Valid amount deposit updates balance correctly")
    void testValidDeposit() throws InterruptedException {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        Thread.sleep(2000);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        Thread.sleep(2000);
        assertEquals(TestData.DEPOSIT_AMOUNT, dashboard.getBalance(),"Should update balance with valid amount deposit");
    }

    @Test
    @DisplayName("Zero amount  deposit does not update balance")
    void testDepositZeroAmount() throws InterruptedException {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        Thread.sleep(2000);
        String balanceBefore = dashboard.getBalance();
        dashboard.deposit(TestData.ZERO_AMOUNT);
        Thread.sleep(2000);
        assertEquals(balanceBefore, dashboard.getBalance(), "Should be zero or the current balance");
    }

    @Test
    @DisplayName("Negative amount deposit does not update balance")
    void testDepositNegativeAmount() throws InterruptedException {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        Thread.sleep(2000);
        String balanceBefore = dashboard.getBalance();
        Thread.sleep(2000);
        dashboard.deposit(TestData.NEGATIVE_AMOUNT);
        assertEquals(balanceBefore, dashboard.getBalance(),"Should not update with negative amount");
    }

    @Test
    @DisplayName("Multiple amount deposits accumulate correctly")
    void testMultipleDeposits() throws InterruptedException {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        Thread.sleep(2000);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        Thread.sleep(1000);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        Thread.sleep(1000);
        assertEquals(TestData.DOUBLE_DEPOSIT_AMOUNT, dashboard.getBalance(), "Should update balance with multiple deposits");
    }
}