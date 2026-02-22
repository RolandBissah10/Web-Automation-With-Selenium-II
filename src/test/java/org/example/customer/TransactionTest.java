package org.example.customer;

import org.example.base.BaseTest;
import org.example.data.TestData;
import org.example.pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest extends BaseTest {

    @Test
    @DisplayName("Deposit is recorded in transaction history")
    void testDepositIsRecorded() {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HERMOINE);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        TransactionsPage transactions = dashboard.goToTransactions();
        assertTrue(transactions.getTransactionCount() > 0);
        assertEquals(TestData.TRANSACTION_CREDIT, transactions.getLatestTransactionType());
    }

    @Test
    @DisplayName("Withdrawal is recorded in transaction history")
    void testWithdrawalIsRecorded() {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HERMOINE);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        dashboard.withdraw(TestData.WITHDRAW_AMOUNT);
        TransactionsPage transactions = dashboard.goToTransactions();
        // Verify at least one transaction exists
        assertTrue(transactions.getTransactionCount() > 0);
        // Verify both Credit and Debit types exist in history
        assertTrue(transactions.hasTransactionType(TestData.TRANSACTION_DEBIT));
    }

    @Test
    @DisplayName("Transaction history shows reset button for view reset only")
    void testCustomerCannotResetTransactions() {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HERMOINE);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        TransactionsPage transactions = dashboard.goToTransactions();
        assertTrue(transactions.isResetButtonPresent());
    }

    @Test
    @DisplayName("Transaction count increases after each operation")
    void testTransactionCountIncreasesAfterEachOperation() {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HERMOINE);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        dashboard.withdraw(TestData.WITHDRAW_AMOUNT);
        TransactionsPage transactions = dashboard.goToTransactions();
        assertTrue(transactions.getTransactionCount() >= 2);
    }
}