# AutomationExercise UI Tests

## Overview
This project contains automated UI tests for AutomationExercise website.

Framework uses:
- Java 17
- Selenium 4
- TestNG
- Maven
- Page Object Model
- Allure Reports
- Jackson (JSON test data)

---

## Project Structure
- pages → Page Objects
- tests → Test classes
- core → driver + utils
- dto → test data models
- resources → config + json test data

---

## How to Run Tests

Run all tests:  
mvn clean test

Run suite:
mvn clean test "-DsuiteXmlFile=testing.xml"

---

## Generate Allure Report

mvn allure:report  
mvn allure:serve

---

## Features Implemented
- Page Object Model
- Reusable test steps
- External test data (JSON + properties)
- Dynamic test data generation
- Clean test design
- Explicit waits

---

## Author
Aida