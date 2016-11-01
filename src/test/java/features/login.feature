Feature: Login to our emap testing site

  Scenario: Prepare Test Environment for User Login Test
  #Prepare our test environment
    Given I have a browser, path to its driver and login page url
#Data
    |Fields|Values|
    |browser|chrome|
    |pathToDriver|D:\\PersonalDrivers\\chromedriver.exe|
    |loginPageUrl| https://jdi-framework.github.io/tests/|
    Then  I have to open the login page of our test web site
#Test
  Scenario Outline: User login test
    Given I have the login web page open
    When  I test the login procedure providing user's name <userName> and password <userPassword>
    Then  I should be logged in if the credentials <correctOrNot> are correct and I shouldn't if they are not
#Data
  Examples:
      |correctOrNot|userName|userPassword|
      |true|epam|1234|
      |false|epam|76767|
      |false|7777|98008|
      |true|epam|1234|
      |false| | |
      |false| |1234|
#Ent of the test
  Scenario: Test is completed
    Given Test is completed