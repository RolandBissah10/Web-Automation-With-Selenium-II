# XYZ Bank Test Automation

Automated test suite for the XYZ Bank web application using Selenium WebDriver, JUnit 5, and Allure Reports.

---

## Project Structure
```
xyz-bank-test-automation/
├── .github/workflows/ci.yml       # GitHub Actions pipeline
├── src/test/java/com/xyzbank/
│   ├── base/                      # BasePage and BaseTest
│   ├── config/                    # AppUrls
│   ├── pages/                     # Page Object Model classes
│   ├── tests/                     # JUnit 5 test classes
│   ├── utils/                     # DriverFactory and ElementHelper
│   └── data/                      # TestData constants
├── resources/                     # config.properties, allure.properties
├── Dockerfile                     # Container environment
├── docker-compose.yml             # Local orchestration
└── pom.xml                        # Maven dependencies and plugins
```

---

## Prerequisites

| Tool        | Version  |
|-------------|----------|
| Java        | 17+      |
| Maven       | 3.9+     |
| Docker      | 24+      |
| Chrome      | Latest   |

---

## Running Tests

### Locally with Maven
```bash
# Run all tests
mvn test

# Run tests in headless mode
mvn test -Dheadless=true

# Generate Allure report
mvn allure:report

# Open Allure report
mvn allure:serve
```

### Locally with Docker
```bash
# Build and run tests + Allure report
docker-compose up --build

# View Allure report at
http://localhost:5050
```

### CI/CD
Tests run automatically on every push or pull request to `main` or `develop` via GitHub Actions.

---

## Test Coverage

| Area             | Test Class           | Scenarios                                      |
|------------------|----------------------|------------------------------------------------|
| Add Customer     | AddCustomerTest      | Valid, numeric name, special chars, empty fields|
| Create Account   | CreateAccountTest    | Dollar, Pound, Rupee, no account access        |
| Delete Customer  | DeleteCustomerTest   | Delete, verify removed, count decreases        |
| Deposit          | DepositTest          | Valid, zero, negative, multiple deposits       |
| Withdraw         | WithdrawTest         | Valid, exceeds balance, zero, negative, full   |
| Transactions     | TransactionTest      | Recorded, credit, debit, cannot reset          |

---

## Reporting

Allure reports are generated after each test run.
```bash
# Generate report
mvn allure:report

# Report is available at
target/allure-report/index.html
```

In CI, surefire reports and execution logs are uploaded as artifacts after every run.

---

## Configuration

All environment settings are in `resources/config.properties`:
```properties
browser=chrome
headless=true
explicit.wait=10
base.url=https://www.globalsqa.com/angularJs-protractor/BankingProject/#/
```