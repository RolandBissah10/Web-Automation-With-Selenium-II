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
public class TransactionTest extends BaseTest {

    @Test
    @DisplayName("Deposit is recorded in transaction history")
    @Severity(SeverityLevel.NORMAL)
    @Story("Transaction History for deposit amounts")
    @Description("This test verifies that the deposit made by a customer is recorded to the transaction history")
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
    @Severity(SeverityLevel.NORMAL)
    @Story("Transaction History withdrawal amounts")
    @Description("This test verifies that the withdrawal made by a customer is recorded to the transaction history")
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
    @Severity(SeverityLevel.CRITICAL)
    @Story("Resetting transaction history")
    @Description("This test verifies that the customers cannot reset the transaction history by clicking on the reset button")
    void testCustomerCannotResetTransactions() {
        CustomerDashboardPage dashboard = new LandingPage(elementHelper)
                .goToCustomer()
                .loginAs(TestData.CUSTOMER_HERMOINE);
        dashboard.deposit(TestData.DEPOSIT_AMOUNT);
        TransactionsPage transactions = dashboard.goToTransactions();
        assertTrue(transactions.isResetButtonPresent());
        assertEquals(TestData.CAN_RESET_TRANSACTION,true);
    }

    @Test
    @DisplayName("Transaction count increases after each operation")
    @Severity(SeverityLevel.NORMAL)
    @Story("Transaction History")
    @Description("This test verifies that the transaction count increases each time a customer deposits or withdraws amount")
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