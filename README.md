# Foodics_RestAssured
# Automation Framework using RestAssured

## Overview
This automation framework is built using RestAssured to perform API testing on the [ReqRes](https://reqres.in/) API. The framework is designed using **TestNG** for test execution and **Jackson** for JSON processing. It includes a modular structure with configuration management, reusable components, and test scripts.

## Project Structure
```
project-root/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── resources/
│   │   │   │   ├── GlobalData.properties
│   │   │   ├── restassured/
│   │   │   │   ├── Constants.java
│   │   │   │   ├── Payload.java
│   │   │   ├── utils/
│   │   │   │   ├── ConfigReader.java
│   ├── test/
│   │   ├── java/
│   │   │   ├── restassured/
│   │   │   │   ├── BaseTest.java
│   │   │   │   ├── Listerners.java
│   │   │   │   ├── TCs.java
│   │   │   │   ├── TestData.java
│── pom.xml
│── README.md
```

## Prerequisites
Ensure the following dependencies are installed:
- Java 8 or later
- Maven
- RestAssured
- TestNG
- Jackson

## Configuration
The framework uses a properties file (`GlobalData.properties`) for storing global configurations such as the base URL and timeout values.

### GlobalData.properties
```properties
baseURI = https://reqres.in/
timeout = 10000
```

## Components

### 1. Constants.java
Defines constants for API endpoints and headers.

### 2. Payload.java
A POJO class representing user data for API requests.

### 3. ConfigReader.java
Loads configurations from `GlobalData.properties`.

### 4. BaseTest.java
Sets up the base configuration for all test cases.

### 5. Listerners.java
Implements a TestNG listener to capture test failures.

### 6. TCs.java
Contains API test cases for creating, retrieving, updating, and handling errors for users.

### 7. TestData.java
Stores test data, including default user details and dynamically generated user IDs.

## Test Cases Implemented
- **Create User**: Validates successful user creation.
- **Create User with Empty Data**: Ensures correct error handling.
- **Retrieve User**: Validates user details retrieval.
- **Retrieve Non-Existing User**: Ensures 404 response for invalid users.
- **Update User**: Checks user update functionality.

## Running the Tests
Use Maven to execute the tests

## Conclusion
This framework provides a structured approach to API automation using RestAssured, making it scalable and easy to maintain.

