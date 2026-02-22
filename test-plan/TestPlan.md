# Test Plan — XYZ Bank Application

---

## 1. Introduction

This test plan covers the functional testing of the XYZ Bank web application.
The application serves two user groups: Bank Managers and Customers.
Testing will be performed manually and via automated Selenium WebDriver tests.

---

## 2. Objectives

- Verify that Bank Managers can add, manage and delete customer accounts
- Verify that Customers can deposit, withdraw and view transactions
- Ensure input validation works correctly across all forms
- Ensure the application behaves correctly for both valid and invalid inputs

---

## 3. Scope

### In Scope
- Bank Manager: Add Customer, Create Account, Delete Customer
- Customer: Login, Deposit, Withdraw, View Transactions
- Input validation for all forms
- Balance updates after transactions
- Transaction history recording

### Out of Scope
- Performance and load testing
- Mobile responsiveness
- Browser compatibility beyond Chrome
- Security and penetration testing

---

## 4. User Stories

### User Story 1 — Bank Manager
As a Bank Manager, I want to add customers, create accounts and delete accounts
so that I can manage customer accounts efficiently.

### User Story 2 — Customer
As a Customer, I want to view my transactions, deposit funds and withdraw money
so that I can manage my finances effectively.

---

## 5. Test Environment

| Item        | Detail                                                                 |
|-------------|------------------------------------------------------------------------|
| Application | XYZ Bank — https://www.globalsqa.com/angularJs-protractor/BankingProject |
| Browser     | Google Chrome (latest)                                                 |
| OS          | Ubuntu (CI), Windows/Mac (local)                                       |
| Java        | JDK 17                                                                 |
| Framework   | Selenium WebDriver + JUnit 5                                           |
| Reporting   | Allure Reports                                                         |
| CI/CD       | GitHub Actions                                                         |

---

## 6. Test Types

| Type             | Description                                              |
|------------------|----------------------------------------------------------|
| Functional       | Verify features work as per acceptance criteria          |
| Negative         | Verify system handles invalid inputs correctly           |
| Boundary         | Verify system handles zero, empty and extreme values     |
| End-to-End       | Verify complete user flows from login to transaction     |

---

## 7. Test Cases

### Manager — Add Customer

| ID    | Description                          | Input                          | Expected Result              |
|-------|--------------------------------------|--------------------------------|------------------------------|
| TC-01 | Add valid customer                   | John, Doe, 12345               | Customer added successfully  |
| TC-02 | Add customer with numeric name       | John123, Doe, 12345            | Submission rejected          |
| TC-03 | Add customer with special characters | John@#$, Doe, 12345            | Submission rejected          |
| TC-04 | Add customer with invalid post code  | John, Doe, ABC                 | Submission rejected          |
| TC-05 | Add customer with empty fields       | empty, empty, empty            | Submission rejected          |

### Manager — Create Account

| ID    | Description                          | Input                          | Expected Result              |
|-------|--------------------------------------|--------------------------------|------------------------------|
| TC-06 | Create Dollar account                | Harry Potter, Dollar           | Account created successfully |
| TC-07 | Create Pound account                 | Ron Weasley, Pound             | Account created successfully |
| TC-08 | Create Rupee account                 | Hermoine Granger, Rupee        | Account created successfully |
| TC-09 | Customer without account cannot login| Neville Longbottom             | Not available in dropdown    |

### Manager — Delete Customer

| ID    | Description                          | Input                          | Expected Result              |
|-------|--------------------------------------|--------------------------------|------------------------------|
| TC-10 | Delete existing customer             | Harry Potter                   | Customer removed from list   |
| TC-11 | Deleted customer cannot login        | Ron Weasley                    | Not available in dropdown    |
| TC-12 | Customer count decreases after delete| Hermoine Granger               | Count reduces by 1           |

### Customer — Deposit

| ID    | Description                          | Input                          | Expected Result              |
|-------|--------------------------------------|--------------------------------|------------------------------|
| TC-13 | Valid deposit updates balance        | 1000                           | Balance shows 1000           |
| TC-14 | Zero deposit does not update balance | 0                              | Balance unchanged            |
| TC-15 | Negative deposit rejected            | -100                           | Balance unchanged            |
| TC-16 | Multiple deposits accumulate         | 1000 + 1000                    | Balance shows 2000           |

### Customer — Withdraw

| ID    | Description                          | Input                          | Expected Result              |
|-------|--------------------------------------|--------------------------------|------------------------------|
| TC-17 | Valid withdrawal updates balance     | Deposit 1000, Withdraw 500     | Balance shows 500            |
| TC-18 | Withdraw more than balance rejected  | 99999                          | Balance unchanged            |
| TC-19 | Zero withdrawal rejected             | 0                              | Balance unchanged            |
| TC-20 | Negative withdrawal rejected         | -100                           | Balance unchanged            |
| TC-21 | Withdraw entire balance              | Deposit 1000, Withdraw 1000    | Balance shows 0              |

### Customer — Transactions

| ID    | Description                          | Input                          | Expected Result              |
|-------|--------------------------------------|--------------------------------|------------------------------|
| TC-22 | Deposit recorded in history          | Deposit 1000                   | Transaction count > 0        |
| TC-23 | Withdrawal recorded in history       | Deposit 1000, Withdraw 500     | Latest type is Debit         |
| TC-24 | Customer cannot reset transactions   | View transactions page         | No reset button present      |
| TC-25 | Transaction count increases          | Deposit + Withdraw             | Count equals 2               |

---

## 8. Entry and Exit Criteria

### Entry Criteria
- Application is accessible and stable
- Test environment is configured
- All dependencies are installed

### Exit Criteria
- All test cases have been executed
- All critical and high priority defects are resolved
- Allure report has been generated and reviewed

---

## 9. Risks and Mitigations

| Risk                              | Mitigation                                      |
|-----------------------------------|-------------------------------------------------|
| App UI changes break locators     | Use stable locators, review after each release  |
| Flaky tests due to timing issues  | Use explicit waits in ElementHelper             |
| Chrome version mismatch           | WebDriverManager handles driver version auto    |
| CI environment differences        | Dockerfile ensures consistent environment       |

---

## 10. Deliverables

- Test Plan (this document)
- Automated test suite
- Allure HTML report
- GitHub Actions CI/CD pipeline
- Docker setup for local execution