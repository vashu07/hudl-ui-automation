# Hudle UI Test Automation

## 📌 Overview
This project contains automated UI tests built using:
- [Playwright for Java](https://playwright.dev/java/docs/intro)
- [TestNG](https://testng.org/)
- [Maven](https://maven.apache.org/)

The framework follows the **Page Object Model (POM)** design pattern for better maintainability and scalability.

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

## ⚙️ Prerequisites
- Java 21 installed
- Maven 3.8+ installed
- Playwright dependency
- testNg dependency

---

## 📦 Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/your-repo-name.git

## 📦 Run Configuration
1. To skip tests
    ```bash
    mvn clean install -Dmaven.test.skip=true  
    ``` 
2. To run locally using testNg file
   ```bash
    mvn clean install -U -DsuiteXmlFile=testng.xml -Dgrid=dev
   ``` 
   
3. Allure Reporting
    ```bash
    allure generate allure-results --clean -o allure-report
    allure open allure-report
    ```




