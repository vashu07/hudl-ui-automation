# Hudle UI Test Automation

## 📌 Overview
This project contains automated UI tests built using:
- [Playwright for Java](https://playwright.dev/java/docs/intro)
- [TestNG](https://testng.org/)
- [Maven](https://maven.apache.org/)

The framework follows the **Page Object Model (POM)** design pattern for better maintainability and scalability.

---
## ⚙️ Prerequisites
- Java 21 installed
- Maven 3.11.0 installed
- Playwright dependency
- testNg dependency
---

## 📂 Project Structure
```
+hudl-ui-automation/
+├── pom.xml                         # Maven Project Object Model
+├── README.md                       # Project documentation
+└── src
+    └── test
+        ├── java
+        │   └── com.hudl
+        │       ├── builders        # Builders for creating complex objects (e.g., test data)
+        │       ├── constants       # Application-wide constants (e.g., error messages)
+        │       ├── pages           # Page Object classes (locators and actions)
+        │       ├── tests           # TestNG test classes
+        │       ├── utils           # Utility classes (e.g., RetryAnalyzer)
+        │       └── wtos            # Data Transfer Objects for configuration
+        └── resources
+            ├── cfg/                # Environment-specific JSON configuration files
+            └── testng.xml          # TestNG suite definition
 
```

---

## 📦 Setup
1. Clone the repository:
   ```bash
   git clone git@github.com:vashu07/hudle-ui-automation.git

## 📦 Run Configuration
1. To clean install and skip tests
    ```bash
    mvn clean install -Dmaven.test.skip=true  
    ``` 
   
2. To run locally using testNg file
   ```bash
     mvn clean install -U -DsuiteXmlFile=src/test/resources/testng.xml -Dgrid=dev
   ``` 
   
3. To select profile from pom.xml and execute tests
   ```bash
   To trigger smoke test suite 
   mvn clean test -Psmoke -Dgrid=dev
   
   To trigger regression test suite  
   mvn test -Dgrid=dev
   ``` 
   
4. Allure Reporting
    ```bash
   To generate html page from json file
      allure generate allure-results --clean -o allure-report
   
   To open the report in browser
       allure open allure-report
    ```




