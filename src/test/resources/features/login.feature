Feature: User login
  As a shopper
  I want to sign in to the store
  So that I can browse and purchase products

  Scenario: A registered shopper signs in successfully
    Given the shopper is on the login page
    When the shopper signs in with valid credentials
    Then the products page is displayed

  Scenario: A locked-out shopper is blocked from signing in
    Given the shopper is on the login page
    When the locked-out shopper attempts to sign in
    Then an account-locked message is shown

  Scenario: Sign in is rejected with unknown credentials
    Given the shopper is on the login page
    When the shopper signs in with an unknown username and password
    Then an invalid-credentials message is shown
