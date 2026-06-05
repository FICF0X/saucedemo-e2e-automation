package com.ficf0x.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The login page (https://www.saucedemo.com/).
 * All selectors for this screen live here and nowhere else.
 */
public class LoginPage extends BasePage {

    private static final String URL = "https://www.saucedemo.com/";

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public void loginAs(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    public String getErrorMessage() {
        return textOf(errorMessage);
    }
}
