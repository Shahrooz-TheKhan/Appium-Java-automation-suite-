# Objective

The primary objective of this test plan is to ensure the functionality, reliability, and performance of the test build for Ev.Energy app through end-to-end testing utilizing Appium, cucumber and Java. The tests cover the login flow, account creation process, and the "test drive the app" flows.

## Approach


### Technology Stack:

Testing Framework: Appium for mobile automation on Maven build with page object model setup 

Programming Language: Java

Additional Libraries: For data generation (names, emails, passwords), the TestDataGenerator class was employed.

Environment: Android and iOS emulators for cross-platform testing.

### Test Flows:

Login Flow: Verifying user authentication with both valid and invalid credentials.

Sign Up Flow: Testing the registration and set up process with randomized data.

Test Drive the App: Interacting with various functionalities of the app as a user would in a real-world scenario.

### Data Management:

Randomized login data for account creation stored in 'signupData.txt' for traceability and future reference.
Utilization of TestDataGenerator class for generating dynamic test data.

### Execution Strategy:

Automated tests executed sequentially to mimic user behavior.
Use of @BeforeEach setup method to ensure a fresh start for each test case.


# Test Execution Metrics

### Login Page Tests

Total Test Cases: 5

Executed: 5

Passed: 5

Pass Rate: 100%

![image](https://github.com/user-attachments/assets/3b468c77-6cf1-4e75-8a97-e368f68b1401)

### Signup Flow Tests

Total Test Cases: 6

Executed: 6

Passed: 6

Pass Rate: 100%

![image](https://github.com/user-attachments/assets/46518246-912d-4cfd-a1ca-d06273dd84f0)

### Test Drive the App Flow Tests

Total Test Cases: 11

Executed: 11

Passed: 11

Pass Rate: 100%


![image](https://github.com/user-attachments/assets/cc9f15e1-fd1b-4067-8b50-7a177d1b70d3)

## Coverage of Requirements

The implemented tests cover all essential aspects of the app as specified in the guidelines:

Login Flow: All scenarios including valid and invalid inputs are covered.

Account Creation: Comprehensive testing of the account creation process with randomized data.

Test Drive the App: All key features and functionalities within this flow are thoroughly tested.

Cross-platform Testing: Extend the framework to include iOS testing for complete coverage across platforms (Only created ios test case class in ios folder with setup code, focused efforts on android test execution) 

Reporting: Implement a detailed reporting mechanism for better insights into each test execution.

### Conclusion
The test plan and its execution ensure that the 'universal.apk' application meets the required standards of functionality and reliability. The testing framework is scalable and can be extended for future app functionalities and platforms.





