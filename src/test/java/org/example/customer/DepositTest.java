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
    void testValidDeposit() {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        assertEquals(TestData.DEPOSIT_AMOUNT, dashboard.getBalance());
    }

    @Test
    @DisplayName("Zero amount  deposit does not update balance")
    void testDepositZeroAmount() {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        String balanceBefore = dashboard.getBalance();
        dashboard.deposit(TestData.ZERO_AMOUNT);
        assertEquals(balanceBefore, dashboard.getBalance());
    }

    @Test
    @DisplayName("Negative amount deposit does not update balance")
    void testDepositNegativeAmount() {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        String balanceBefore = dashboard.getBalance();
        dashboard.deposit(TestData.NEGATIVE_AMOUNT);
        assertEquals(balanceBefore, dashboard.getBalance());
    }

    @Test
    @DisplayName("Multiple amount deposits accumulate correctly")
    void testMultipleDeposits() {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HARRY);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        assertEquals(TestData.DOUBLE_DEPOSIT_AMOUNT, dashboard.getBalance());
    }
}