# RestAssured Test Suite for Automate API Requests and Verify Responses Task for PokeAPI by Jon Paulo Ojon
## Overview
This test suite includes automated API tests for PokeAPI

## Author
Jon Paulo Ojon

## Prerequisites
* Java: (v22 recommended), find installer on https://www.oracle.com/java/technologies/downloads/ site
* Maven: (optional)
* IDE: Optional but recommended (e.g., IntelliJ IDEA, Eclipse).

## Installation
1. Clone the repository and go to project directory
- git clone https://github.com/jp-ojon/api-test-auto-pokemon.git
- change directory to root folder api-test-auto-pokemon

## Test Cases
- Test Case 1: Test Get Request on Poke API
- Test Case 2: Test Get Request on Mock Poke API, modify the response to return status 404.

## Project Structure
- src/test/java: Contains the test code.
- pom.xml: Maven build configuration file.
- testng.xml: Configuration of tests

## Running Tests
Use the following commands in any terminal or cmd line to run tests
1. mvnw.cmd test    : run all tests for Windows, no Maven installation needed
2. ./mvnw test      : run all tests for Unix/Linux/Mac, no Maven installation needed
3. mvn test         : run all tests for any OS, Maven installation required

## Links to Documentation
- RestAssured: https://rest-assured.io/
- TestNG: https://testng.org/
- Maven: https://maven.apache.org/guides/index.html