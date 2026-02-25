# TEST PLAN
## XYZ Bank Application

**Prepared By:** Francis Roland Bissah, Quality Assurance Engineer  
**Project:** XYZ Bank Application

---

## 1.0 Introduction

This section provides an overview of the test plan, the testing approach, and the tools used. It sets the context for all testing activities carried out on the XYZ Bank Application.

This Test Plan outlines the automated testing approach for the XYZ Bank Application. It defines the scope, objectives, resources, and testing approach required to ensure the application meets its functional and non-functional requirements. Testing is conducted using Selenium WebDriver with JUnit 5, following the Page Object Model (POM) design pattern. Allure is used for test reporting, Docker for containerized test execution, and GitHub Actions for continuous integration and delivery.

The project follows an Agile, sprint-based development approach, with each sprint lasting two (2) weeks. Automated test execution is integrated into every sprint to ensure continuous quality assurance and early detection of defects.

---

## 2.0 Test Objectives

This section explains the goals of the testing effort. It defines what the team is trying to achieve through automation to ensure the application is reliable, functional, and stable.

- Ensure the application meets all specified functional and non-functional requirements defined in the user stories.
- Verify that customer and bank manager functionalities operate correctly through automated test execution.
- Detect, log, and manage defects early through continuous automated testing on every code change.
- Ensure accurate handling of financial transactions such as deposits and withdrawals.
- Verify that only authorized users can access specific features based on their roles.
- Ensure regression stability after new changes or enhancements through automated test execution.
- Generate clear and readable test reports using Allure after every test run.

---

## 3.0 Scope of Testing

This section defines what will and will not be tested within the automated test suite. It clearly lists the testing types, their definitions, whether they are in scope, and who is responsible. This avoids ambiguity and ensures clear ownership of all testing activities.

| Type                                        | Definition | In Scope | Responsibility               |
|---------------------------------------------|-----------|----------|------------------------------|
| Review of Requirements                      | A process to review and understand customer requirements | Yes      | PO, Developers, QA           |
| Unit Testing                                | Testing individual components or functions in isolation | Yes      | Developers                   |
| Integration Testing                         | Testing interaction between combined modules | Yes      | Developers                   |
| Functional Testing                          | Testing application functionality against requirements via automation | Yes      | QA                           |
| Regression Testing                          | Automated tests triggered on every push to ensure no existing functionality is broken | Yes      | QA                           |
| Exploratory Testing                         | Active exploration of the application without predefined test cases | Yes      | QA                           |
| Accessibility Testing                       | Ensures the application is usable by people with disabilities | Yes      | Developers, QA               |
| Compatibility Testing                       | Verifies performance across different browsers, OS, and devices | Yes      | Developers, QA               |
| API Testing                                 | Validating application programming interfaces directly | Yes      | Developers, QA               |
| User Acceptance Testing                     | End-users test the software to ensure it meets requirements | Yes      | Customer/PO                  |
| Security Testing                            | Identifying vulnerabilities and ensuring data protection | Yes      | Pentesters/Bug Bounties      |
| Hardware and Network Infrastructure Testing | Physical server, network devices, and infrastructure-level validation are not included. | No       | Hardware / Nertwork Engineer |

---

## 4.0 Testing Resources

This section describes the people and tools needed to carry out testing. It ensures the right team members are assigned and the correct tools are in place before testing begins.

### 4.1 Human Resources
Human resources are the people that will be used to carry out the testing activities.

- **QA Engineer** – Design, build, and maintain the automated test suite; verify defects
- **Development Team** – Fix defects and provide technical support
- **Product Owner** – Clarify requirements and perform acceptance testing

### 4.2 Test Tools
Test tools are the tools that will be used in the testing activities.

| Tool Category | Tool Name | Purpose |
|--------------|-----------|---------|
| Automation Framework | Selenium WebDriver 4.x | Browser automation for functional and regression testing |
| Test Runner | JUnit 5 | Test execution, assertions, and lifecycle management |
| Build Tool | Maven | Dependency management and test execution via `mvn test` |
| Design Pattern | Page Object Model (POM) | Separates UI locators from test logic for maintainability |
| Test Reporting | Allure | Generates detailed HTML reports with pass/fail results and history |
| Containerization | Docker | Ensures consistent test execution environment across machines |
| CI/CD Pipeline | GitHub Actions | Triggers automated tests on every push or pull request |
| Browser Driver | WebDriverManager | Automatically manages ChromeDriver and browser versions |
| Cross-Browser Testing | LambdaTest | Browser compatibility testing across Chrome, Firefox, and Edge |

---

## 5.0 Testing Design Techniques

This section explains how test cases are structured and designed. It describes the techniques used to ensure effective coverage with fewer redundant tests and a maintainable codebase.

- **Use Case Testing:** Test cases are derived from user stories to validate workflows such as adding customers, creating accounts, depositing funds, and withdrawing money.
- **Equivalence Partitioning (EP):** Groups valid and invalid inputs (e.g., valid vs invalid amounts, alphabetic vs non-alphabetic names) to reduce redundant tests.
- **Page Object Model (POM):** Each page of the application has a dedicated class containing its locators and actions, keeping test logic clean and reusable.
- **State-Aware Testing:** Tests are designed to handle pre-existing application state since the app does not reset between runs. Reference values are captured after setup actions rather than assuming a clean state.

---

## 6.0 Test Entry Criteria

This section defines the conditions that must be met before automated testing can begin. It ensures testing starts in a stable, controlled, and fully prepared environment.

- Requirements are reviewed and approved
- Application build is deployed and accessible
- Automated test suite is configured and compiling successfully
- Test data constants are defined in `TestData.java`
- `config.properties` is configured with the correct URL, browser, and wait settings
- Docker image builds without errors
- CI/CD pipeline is connected to the repository

---

## 7.0 Test Exit Criteria

This section defines the conditions that must be satisfied before testing is considered complete. It ensures the application is stable and ready for release before sign-off.

- All 25 automated test cases pass in the CI/CD pipeline
- Allure report is generated and shows no critical failures
- No critical banking functionality is broken (login, deposit, withdrawal, account creation)
- All user stories and acceptance criteria are validated by automated tests
- Docker test run completes successfully in a clean container
- Stakeholders review and approve the Allure test report

---

## 8.0 Defect Management

This section explains how defects discovered during automated test runs are captured, classified, and resolved. It ensures issues are tracked systematically and do not impact release quality.

Defects discovered during automated test runs are logged with the failing test name, expected vs actual values, and stack trace. Defects are classified by severity and priority:

| Severity | Priority | Description |
|----------|----------|-------------|
| Critical | Blocker | Core banking functions broken (login, deposit, withdrawal) |
| High | Major | Key features failing, significant impact on functionality |
| Medium | Normal | Feature partially working, workaround exists |
| Low | Minor | UI issues, cosmetic defects, minor UX problems |

- Critical and high-severity defects are addressed immediately before the next sprint
- QA retests fixed defects by re-running the relevant automated test and verifying it passes
- Defect lifecycle: **Open → In Progress → Fixed → Verified → Closed**

---

## 9.0 Testing Schedule

This section outlines when each testing activity will take place. It aligns testing with the sprint development cycle to ensure continuous coverage and timely defect detection.

| Test Phase | Timeline | Coverage | Assigned To |
|-----------|----------|----------|-------------|
| Requirement Review & Test Design | Sprint 1 | All functional and non-functional requirements | PO, QA |
| Functional Testing | Each Sprint | Customer & Manager features | QA |
| Regression Testing | Each Sprint (CI/CD on every push) | Full regression to ensure fixes and updates don't break existing functionality | QA |
| Exploratory Testing | Each Sprint | All user requirements | QA |
| Accessibility Testing | Each Sprint | Usability standards | QA |
| Compatibility Testing | Each Sprint | Cross-browser & device support | QA, Developers |
| UAT | End of Sprint | Acceptance criteria | Customer / PO |

---

## 10.0 Risk and Mitigations

This section identifies potential risks that could affect the quality or timeline of testing. It defines mitigation actions to reduce the impact of each risk and prevent delays.

| Risk | Impact | Mitigation                                                                    |
|------|--------|-------------------------------------------------------------------------------|
| Unclear Requirements | Unclear or changing requirements may affect test accuracy | Hold regular stakeholder meetings and clarify all requirements before testing |
| Resource Constraints | Limited QA resources may delay test development | Request for more resources to help work faster                                |
| Environment Instability | Application downtime may block CI/CD pipeline runs | Monitor environments; configure pipeline to retry on transient failures       |
| Browser/Driver Compatibility | ChromeDriver version mismatches may break test execution | Use WebDriverManager for automatic driver resolution; pin versions in Docker  |

---

## 11.0 Environment Setup

This section specifies the platforms, infrastructure, and configurations used for testing. It lists the supported browsers, operating systems, devices, and automation tools to ensure the application is tested consistently across all target environments. This guarantees that defects found in testing are representative of real-world behavior.

### 11.1 Application Deployment

The XYZ Bank application is deployed on a dedicated staging server that mirrors production configuration (same build version, database structure, and APIs). This ensures defects found in testing are representative of real-world behavior and can be reliably reproduced.

```
https://www.globalsqa.com/angularJs-protractor/BankingProject/index.html#/
```

### 11.2 Test Database

A separate test database is configured to store customer accounts, balances, and transactions without impacting live customer data. The database is refreshed before each sprint to maintain clean and consistent test data.

### 11.3 Local Execution Environment

For running and debugging tests on a developer or QA machine:

| Component | Requirement |
|-----------|-------------|
| Operating System | Windows 10/11, macOS, Linux |
| Java | JDK 17 or higher |
| Build Tool | Maven 3.9+ |
| Browser | Google Chrome (latest) |
| ChromeDriver | Auto-managed by WebDriverManager |
| IDE | IntelliJ IDEA (recommended) |

Run tests locally:
```bash
mvn clean test
```

Generate and view the Allure report:
```bash
mvn allure:serve
```

### 11.4 Docker Execution Environment

Tests are containerized to ensure a consistent and reproducible execution environment regardless of the host machine. The Docker image includes all required dependencies pre-installed.

| Component | Detail |
|-----------|--------|
| Base Image | maven:3.9-eclipse-temurin-17 |
| Browser | Google Chrome (stable, headless) |
| Test Mode | Headless (`headless=true`) |
| Results Volume | `./allure-results:/app/target/allure-results` |

Run the full test suite in Docker:
```bash
docker-compose up --build
```

Stop and clean up containers:
```bash
docker-compose down
```

### 11.5 CI/CD Pipeline Environment

GitHub Actions triggers the automated test suite on every push and pull request to the main branch. The pipeline runs in a clean Linux environment on every execution.

| Component | Detail |
|-----------|--------|
| Platform | GitHub Actions (Ubuntu runner) |
| Trigger | Push or Pull Request to main branch |
| Browser | Headless Chrome |
| Reporting | Allure report published as build artifact |

Pipeline steps:
1. Check out the repository
2. Build the Docker image
3. Run all 25 tests in headless Chrome
4. Publish the Allure report as a build artifact

### 11.6 Allure Reporting Environment

Allure generates and serves the HTML test report after each test run.

| Component | Detail |
|-----------|--------|
| Local Report | `mvn allure:serve` — opens in browser automatically |
| Docker Report | Available at `http://localhost:5050` via `frankescobar/allure-docker-service` |
| CI/CD Report | Published as a downloadable artifact on GitHub Actions |
| History | Allure retains test history across runs for trend analysis |

### 11.7 Configuration File

All environment settings are centrally managed in `src/test/resources/config.properties`:

```properties
browser=chrome
headless=false
implicit.wait=10
explicit.wait=15
base.url=https://www.globalsqa.com/angularJs-protractor/BankingProject/index.html#/
```

Set `headless=true` for Docker and CI/CD pipeline execution.

### 11.8 Supported Browsers and Devices

| Category | Supported |
|----------|-----------|
| Browsers | Chrome, Firefox, Edge |
| Operating Systems | Windows 10/11, macOS, Linux |
| Devices | Desktop, Laptop |
| Screen Modes | Headed (local), Headless (Docker & CI/CD) |

---

## 12.0 Communication Plan

This section explains how the team will communicate progress, results, and issues throughout the testing cycle. It ensures timely updates, visibility of test outcomes, and quick resolution of failures.

- Daily stand-up meetings to review CI/CD pipeline status and any failing tests
- Sprint reviews include a walkthrough of the Allure report results
- Failing tests in the pipeline notify the team immediately via GitHub Actions alerts
- Allure reports are shared with stakeholders at the end of each sprint for review and sign-off