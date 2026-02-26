# TEST PLAN
## XYZ Bank Application — Test Automation Plan

**Prepared By:** Francis Roland Bissah, Quality Assurance Engineer

**Project:** XYZ Bank Application

---

## 1.0 Introduction

This test plan defines the overall testing approach, scope, resources, tools, and schedule for the XYZ Bank Application. It serves as the primary reference document for all testing activities carried out by the QA team throughout the project lifecycle.

The XYZ Bank Application is an Angular-based web banking system that supports two user roles — Bank Manager and Customer. The Bank Manager can add customers, open accounts, and manage the customer list. Customers can log in, deposit funds, withdraw funds, and view transaction history. The application is hosted at a publicly accessible staging URL and is used as the test target for this automation project.

Testing is conducted using Selenium WebDriver with JUnit 5, following the Page Object Model (POM) design pattern to separate page interactions from test logic. Allure is used for test reporting, Docker for containerized test execution, GitHub Actions for continuous integration, and Jira with the Xray plugin for test management and defect tracking.

The project follows an Agile, sprint-based development cycle with each sprint lasting two (2) weeks. Automated tests are integrated into every sprint and triggered on every code push, ensuring continuous quality assurance and early defect detection throughout the development process.

---

## 2.0 Test Objectives

The following objectives define the goals the QA team must achieve through this testing effort. Each objective addresses a specific quality concern for the XYZ Bank Application.

- **Verify functional correctness** — Ensure the application meets all specified functional requirements defined in the user stories. Every feature that was built must behave exactly as the business intended before it can be considered done.

- **Validate role-based access** — Confirm that bank manager and customer functionalities are correctly separated. A customer must not be able to perform manager actions and a manager must not be able to access customer-only features.

- **Ensure financial transaction accuracy** — Verify that deposits, withdrawals, and balance calculations are handled correctly at all times. Any incorrect balance update or failed transaction is a critical defect that must be caught immediately.

- **Enable continuous regression coverage** — Ensure that every code change is automatically tested to confirm no previously working functionality has been broken. This is achieved through automated test execution on every push via the CI/CD pipeline.

- **Support early defect detection** — Detect defects as early as possible in the development cycle where they are cheapest and fastest to fix. Automated tests running on every commit provide the earliest possible feedback to the development team.

- **Provide clear test visibility** — Generate professional test reports using Allure after every test run so that the team and stakeholders can understand the quality status of the application at a glance without needing technical knowledge.

- **Maintain test traceability** — Ensure every test case is linked to its corresponding user story in Jira via Xray so that requirement coverage can be measured and reported at any point during the project.

---

## 3.0 Scope of Testing

This section defines what will and what will not be tested within this project. Clearly defining scope prevents wasted effort, avoids ambiguity, and ensures that every team member understands their responsibilities.

### 3.1 In Scope

The following testing types are included and will be actively planned and executed during this project:

| Type | Definition | Responsibility |
|------|-----------|----------------|
| Review of Requirements | Reviewing and understanding all user stories and acceptance criteria before test cases are written, ensuring tests are aligned with what was actually requested | PO, Developers, QA |
| Unit Testing | Testing individual components or functions in isolation to verify each piece of code works correctly on its own before being combined with others | Developers |
| Integration Testing | Testing the interaction between combined modules to ensure they work correctly together, for example verifying that adding a customer through the manager page is reflected in the customer login dropdown | Developers |
| Functional Testing | Testing all application features against the defined requirements through automated Selenium tests covering all manager and customer workflows end to end | QA |
| Regression Testing | Automated tests triggered on every code push via GitHub Actions to confirm that new changes have not broken any previously working functionality | QA |
| Exploratory Testing | Active, unscripted exploration of the application by the QA engineer to find unexpected defects that automated scripts may not cover, relying on human intuition and experience | QA |
| Accessibility Testing | Verifying the application is usable by people with disabilities, including screen reader support, keyboard navigation, and sufficient colour contrast | Developers, QA |
| Compatibility Testing | Verifying the application works correctly across Chrome, Firefox, and Edge browsers, and across Windows, macOS, and Linux operating systems | Developers, QA |
| API Testing | Directly validating the application's APIs to verify they handle requests correctly, return expected data, and respond with appropriate error messages for invalid inputs | Developers, QA |
| User Acceptance Testing (UAT) | Testing performed by the Product Owner or end users at the end of each sprint to confirm the application meets their real-world needs and is ready for release | Customer/PO |

### 3.2 Out of Scope

The following testing types are explicitly excluded from this project. They are either handled by specialist teams, require dedicated tooling, or are not applicable to this application at this stage.

| Type | Reason for Exclusion                                                                                                                                                   |
|------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Hardware and Network Infrastructure Testing | Testing physical server configurations, cloud deployments, and network setups is the responsibility of DevOps engineers and falls outside the scope of this test plan. |
---

## 4.0 Testing Resources

This section identifies the people and tools required to carry out all testing activities. Having resources confirmed before testing begins ensures the team is prepared and no time is lost during execution.

### 4.1 Human Resources

| Role | Responsibilities |
|------|-----------------|
| QA Engineer | Design, build, and maintain the automated test suite. Write and manage test cases in Jira/Xray. Verify defect fixes. Generate and share Allure reports with stakeholders. |
| Development Team | Fix defects identified during automated test runs. Provide technical support when test infrastructure issues arise. Own unit and integration testing. |
| Product Owner | Clarify requirements when ambiguous. Review and approve test cases. Perform User Acceptance Testing at the end of each sprint. |

### 4.2 Test Tools

The following tools are used to design, execute, manage, and report on all testing activities. Each tool serves a specific purpose in the overall automation pipeline.

| Tool | Purpose |
|------|---------|
| Selenium WebDriver 4.x | Controls the Chrome browser programmatically — clicking buttons, filling forms, and reading results automatically just like a human user would |
| JUnit 5 | Organizes and executes test methods, handles assertions to compare expected vs actual results, and manages the test lifecycle including setup and teardown |
| Maven | Manages all project dependencies, compiles the code, and provides the `mvn test` command to run the full test suite from the terminal or CI/CD pipeline |
| Page Object Model (POM) | Organizes automation code by giving each page its own class containing its locators and actions, separating page structure from test logic for maintainability |
| Allure Reports | Generates detailed visual HTML reports after every test run showing pass/fail counts, execution summary, pass/fail counts by test class, step-by-step execution details with screenshots on failure, defect linkage, and historical trend data when run across multiple builds |
| Docker | Packages the entire test environment including Java, Maven, and Chrome into a portable container so tests run identically on any machine |
| GitHub Actions | Automatically triggers the full test suite on every push or pull request to the main branch, ensuring no code change goes untested |
| WebDriverManager | Automatically detects the installed Chrome version and downloads the matching ChromeDriver, eliminating manual driver version management |
| Jira with Xray Plugin | Used for test case management, test execution tracking, and defect logging. Xray links test cases directly to user stories and test executions to build results |
| Git + GitHub | Version control and collaboration for all test automation code |

---

## 5.0 Testing Design Techniques

Testing design techniques are used to create effective and efficient test cases that can identify defects and ensure the XYZ Bank application is performing as expected. For this project we adopted the following techniques:

- **Equivalence Partitioning** — Dividing input data into valid and invalid groups to reduce the number of redundant test cases. For example, valid alphabetic names vs names with numbers or special characters, and numeric postal codes vs alphabetic postal codes. One representative test per group is sufficient to cover the expected behavior.

- **Boundary Value Analysis** — Testing at the edges of input ranges where defects most commonly occur. For example, depositing zero amount, withdrawing the exact available balance, and withdrawing more than the available balance. These boundary conditions are tested specifically because they represent the most likely points of failure.

- **Error Guessing** — Using experience and knowledge of the application to predict where defects might occur and design tests specifically targeting those areas. For example, submitting empty form fields, entering special characters in name fields, and attempting to create a duplicate customer.

---

## 6.0 Test Entry Criteria

Test entry criteria define the conditions that must be met before testing can begin. These ensure the testing team has everything needed to start executing tests effectively.

- Test cases are designed for the features to be tested
- Test cases are reviewed and approved
- All relevant test data is available including customer names, postal codes, and deposit/withdrawal amounts
- Test environment is available and setup is ready (Windows 11, Chrome latest)
- Application is accessible via the test URL
- User stories are documented and understood
- Jira project with Xray plugin is configured and accessible
- Selenium project is set up with POM structure and dependencies configured in `pom.xml`
- Docker image builds successfully and tests can execute inside the container
- GitHub Actions workflow is configured and will trigger on push and pull request

---

## 7.0 Test Exit Criteria

For testing to be considered complete, the following conditions must be met. These ensure the product has reached an acceptable quality level before release.

- All test cases (manual and automated) have been executed
- All critical and high priority defects are fixed and retested
- No open critical or high severity defects remain
- All medium and low priority defects are logged, documented, and retested with 100% resolution. If any medium or low priority defects cannot be resolved within the current sprint, they are carried over as backlog items and prioritized for resolution in the following sprint
- RTM shows 100% requirement coverage for both user stories
- Automated test suite passes in the CI/CD pipeline with no critical failures
- Allure Report is generated, reviewed, and shared with stakeholders
- Test summary report is completed and reviewed
- Product Owner sign-off obtained

---

## 8.0 Defect Management

This defines how defects will be identified, documented, tracked, and resolved throughout the testing process. This ensures all issues are captured consistently and nothing is lost between discovery and fix.

- All defects will be logged and tracked using Jira with Xray plugin (Bug issue type)
- Defects will be categorized by severity: Critical, High, Medium, Low
- Defects will be prioritized: Critical, High, Medium, Low
- Each defect will include: Bug ID, Test Case ID, Summary, Description, Steps to Reproduce, Expected/Actual Results, Environment, Severity, Priority, Status
- Defects will be linked directly to failed test steps in Test Execution via Xray
- Defects found during automated test runs will be captured with screenshots (attached automatically via Allure) and linked to the corresponding test case
- Defects will be assigned to the Development Team for investigation and resolution
- Resolved defects will be retested by QA Engineer (manually and via automated regression) before closure

### 8.1 Defect Severity Classification

| Severity | Priority | Description | Example |
|----------|----------|-------------|---------|
| Critical | Blocker | Core banking functionality is completely broken and cannot be worked around | Login fails, deposit does not update balance, application crashes |
| High | Major | A key feature is failing with significant impact on functionality but a workaround may exist | Customer cannot be deleted, account creation fails for some currencies |
| Medium | Normal | A feature is partially working and a workaround exists | Transaction history shows incorrect order, reset button missing |
| Low | Minor | Cosmetic or minor UX issues with no impact on functionality | Incorrect label text, minor alignment issues |

### 8.2 Defect Lifecycle

**Open → In Progress → Fixed → Verified → Closed**

---

## 9.0 Testing Schedule

This section outlines when each testing activity will take place. Testing is aligned with the sprint development cycle to ensure continuous coverage and timely defect detection throughout the project.

**Note:** Each sprint last for two weeks

| Test Phase | Timeline                          | Coverage | Assigned To |
|-----------|-----------------------------------|----------|-------------|
| Requirement Review and Test Design | First Sprint                      | All functional and non-functional requirements | PO, QA |
| Functional Testing | Each Sprint                       | All customer and manager features | QA |
| Regression Testing | Each Sprint — CI/CD on every push | Full regression suite to ensure fixes and updates do not break existing functionality | QA |
| Exploratory Testing | Each Sprint                       | All user requirements and edge cases | QA |
| Accessibility Testing | Each Sprint                       | Usability and accessibility standards | QA, Developers |
| Compatibility Testing | Each Sprint                       | Cross-browser and device support | QA, Developers |
| UAT | End of Each Sprint                | All acceptance criteria defined in user stories | Customer/PO |

---

## 10.0 Risks and Mitigations

This section identifies potential risks that could affect the quality or timeline of testing, along with the mitigation actions in place to reduce their impact.

| Risk | Impact | Mitigation                                                                                                                                                                                                                                                       |
|------|--------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Unclear or Changing Requirements | Unclear requirements lead to incorrect test cases and wasted test effort | Hold regular requirement review sessions with the Product Owner to clarify all requirements before test cases are written.                                                                                                                                       |
| Resource Constraints | Limited QA availability may delay test development and execution | Request for more resources to help work faster                                                                                                                                                                                                |
| CI/CD Pipeline Failures | GitHub Actions workflow may fail due to dependency issues, Docker image build errors, or ChromeDriver version mismatches blocking automated test execution | Pin all dependency versions (Chrome, ChromeDriver, Selenium, Java) in the Dockerfile and pom.xml. Test the Docker image locally before pushing to CI. Configure GitHub Actions to notify the team via Slack on any pipeline failure.                             |
| Application Environment Instability | Staging environment downtime or data issues may block test execution and delay the sprint | Monitor the staging environment before each test run. Configure the CI/CD pipeline to retry on transient failures. Maintain a local fallback test configuration.                                                                                                 |

---

## 11.0 Environment Setup

This defines the hardware, software, and infrastructure configurations on which testing will be performed. It ensures tests are executed under controlled and documented conditions.

| Component | Details |
|-----------|---------|
| Operating System | Windows 11 |
| Browser | Google Chrome (latest) |
| Browser Driver | ChromeDriver (managed via WebDriverManager) |
| Programming Language | Java 17 |
| Build Tool | Maven |
| Test Framework | JUnit 5 |
| Automation Tool | Selenium WebDriver 4.x |
| Design Pattern | Page Object Model (POM) |
| Containerization | Docker (tests run inside a containerized environment) |
| CI/CD | GitHub Actions (triggered on push and pull request) |
| Test Reporting | Allure Reports |
| Test Management Tool | Jira with Xray Plugin |
| Version Control | Git + GitHub |
| Test URL | https://www.globalsqa.com/angularJs-protractor/BankingProject/index.html#/ |
| Device | Desktop, Laptop |

### 11.1 Local Execution Environment

For running and debugging tests on a developer or QA machine:

Run all tests locally:
```bash
mvn clean test
```

Generate and view the Allure report:
```bash
mvn allure:serve
```

### 11.2 Docker Execution Environment

Tests are containerized to ensure a consistent and reproducible execution environment regardless of the host machine. The Docker image includes all required dependencies pre-installed.

Run the full test suite in Docker:
```bash
docker-compose up --build
```

Stop and clean up containers:
```bash
docker-compose down
```

Allure report is available at `http://localhost:5050` after the test run completes.

### 11.3 CI/CD Pipeline Environment

GitHub Actions triggers the automated test suite on every push and pull request to the main branch. Pipeline steps executed on every run:

1. Check out the latest version of the repository
2. Build the Docker image with all dependencies
3. Run all automated tests in headless Chrome
4. Save Allure results and publish the HTML report as a build artifact
5. Notify the team of the result via email and Slack


---

## 12.0 Communication Plan

This defines how the testing team will share progress, report issues, and collaborate with stakeholders throughout the testing process.

- **Daily Standup** — 15-minute meeting held every morning via Microsoft Teams call or In-person if team members are available. QA team members share what they tested yesterday, what they are testing today, and any blockers. Scrum Master facilitates, Developers and QA Team attend.

- **Slack/Teams Chat** — Used as the primary channel for real-time communication throughout the day, quick defect clarifications with developers, sharing screenshots of bugs, and getting unblocked on questions. A dedicated **#xyz-bank-testing** channel is used to keep all discussions centralized.

- **Email** — Used for formal communication only: test plan sign-offs, phase completion notifications, and escalations. Sent to the Product Owner and project stakeholders. All emails include the subject prefix **[XYZ Bank Testing]** for easy tracking.

- **Status Reports** — A written summary shared via email at the end of each test phase covering total test cases executed, pass/fail counts, open defects by severity, and any risks. Prepared by the QA Lead using data exported from Jira/Xray and Allure Reports.

- **Sprint Review** — Held at the end of each sprint via Microsoft Teams or In-person if possible. QA Lead demos test results (including Allure Report walkthrough), walks through critical defects found, and gathers feedback from the full Scrum team and stakeholders.

- **GitHub Actions Notifications** — Automated pipeline notifications sent to the team Slack channel on build/test failures, enabling immediate investigation and resolution.