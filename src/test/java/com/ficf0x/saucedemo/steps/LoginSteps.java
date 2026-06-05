package com.ficf0x.saucedemo.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.ficf0x.saucedemo.hooks.DriverManager;
import com.ficf0x.saucedemo.pages.InventoryPage;
import com.ficf0x.saucedemo.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Glue for login.feature. Each method maps to one Gherkin step and delegates
 * the actual browser work to the Page Objects — the steps stay readable.
 */
public class LoginSteps {

    private final LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    private final InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());

    @Given("the shopper is on the login page")
    public void the_shopper_is_on_the_login_page() {
        loginPage.open();
    }

    @When("the shopper signs in with valid credentials")
    public void the_shopper_signs_in_with_valid_credentials() {
        loginPage.loginAs("standard_user", "secret_sauce");
    }

    @Then("the products page is displayed")
    public void the_products_page_is_displayed() {
        assertThat(inventoryPage.isLoaded()).isTrue();
    }

    @When("the locked-out shopper attempts to sign in")
    public void the_locked_out_shopper_attempts_to_sign_in() {
        loginPage.loginAs("locked_out_user", "secret_sauce");
    }

    @Then("an account-locked message is shown")
    public void an_account_locked_message_is_shown() {
        assertThat(loginPage.getErrorMessage()).containsIgnoringCase("locked out");
    }

    @When("the shopper signs in with an unknown username and password")
    public void the_shopper_signs_in_with_an_unknown_username_and_password() {
        loginPage.loginAs("ghost_user", "wrong_password");
    }

    @Then("an invalid-credentials message is shown")
    public void an_invalid_credentials_message_is_shown() {
        assertThat(loginPage.getErrorMessage())
                .containsIgnoringCase("Username and password do not match");
    }
}
