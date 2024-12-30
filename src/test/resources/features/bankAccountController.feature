@bankAccount @api
Feature: Test Bank Account
  As a user
  I want to test the bank account API
  So that I can verify the creation, retrieval, and deletion of bank accounts

  Scenario Outline: Create and manage multiple Bank Accounts
    Given the API endpoint "/api/myaccount/bankaccount"
    When I create a bank account with "<bank_account_name>", "<description>", "<type_of_pay>", and <balance>
    And I retrieve the created bank account by its ID
    Then I verify the response status code is 200
    And I delete the same bank account by its ID
    Then I confirm the bank account is removed

    Examples:
      | bank_account_name | description    | type_of_pay | balance |
      | Bank              | Financial LLC  | CASH        | 10000   |
      | Bank              | Financial LLC  | BANK        | 1200    |
      | Bank              | Financial LLC  | CASH        | 900     |
      | Bank              | Financial LLC  | BANK        | 1300    |
      | Bank              | Financial LLC  | CASH        | 1000    |
      | Bank              | Financial LLC  | BANK        | 2000    |
