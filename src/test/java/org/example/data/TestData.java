package org.example.data;

public class TestData {

    // Customer Names — each test class uses a different customer to avoid state conflicts
    public static final String CUSTOMER_HARRY    = "Harry Potter";     // Used by DepositTest
    public static final String CUSTOMER_RON      = "Ron Weasly";// Used by WithdrawTest
    public static final String CUSTOMER_HERMOINE = "Hermoine Granger"; // Used by TransactionTest
    public static final String CUSTOMER_WITHOUT_ACCOUNT = "Unknown Person";

    // Add Customer Form Data
    public static final String FIRST_NAME         = "John";
    public static final String LAST_NAME          = "Doe";
    public static final String POST_CODE          = "12345";
    public static final String INVALID_FIRST_NAME = "John123";
    public static final String SPECIAL_CHAR_NAME  = "John@#$";
    public static final String INVALID_POST_CODE  = "ABC";
    public static final String EMPTY              = "";

    // Currencies
    public static final String CURRENCY_DOLLAR = "Dollar";
    public static final String CURRENCY_POUND  = "Pound";
    public static final String CURRENCY_RUPEE  = "Rupee";

    // Amounts
    public static final String DEPOSIT_AMOUNT           = "1000";
    public static final String WITHDRAW_AMOUNT          = "500";
    public static final String ZERO_AMOUNT              = "0";
    public static final String NEGATIVE_AMOUNT          = "-100";
    public static final String EXCEEDS_BALANCE_AMOUNT   = "99999";
    public static final String DOUBLE_DEPOSIT_AMOUNT    = "2000";
    public static final String BALANCE_AFTER_WITHDRAWAL = "500";
    public static final String NEGATIVE_AMOUNT_ERROR_MESSAGE = "Error message: Amount should not be a negative number";
    public static final String ZERO_AMOUNT_ERROR_MESSAGE = "Error message: Amount should greater than zero";
    public static final String INVALID_CUSTOMER_DETAILS = "Invalid customer details";
    public static final String EMPTY_CUSTOMER_DETAILS = "Error message: Field is required";

    // Transaction Types
    public static final String TRANSACTION_CREDIT = "Credit";
    public static final String TRANSACTION_DEBIT  = "Debit";

    public static final boolean CAN_RESET_TRANSACTION  = false;
}