package com.ficf0x.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * La página de inicio de sesión (https://www.saucedemo.com/).
 * Todos los selectores de esta pantalla viven aquí y en ningún otro lado.
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
