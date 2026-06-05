package com.ficf0x.saucedemo.hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Owns the WebDriver lifecycle.
 *
 * The driver is held in a ThreadLocal so that, if tests ever run in parallel,
 * each thread gets its own isolated browser. Selenium 4's built-in Selenium
 * Manager downloads the matching ChromeDriver automatically — no manual setup.
 */
public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            DRIVER.set(createDriver());
        }
        return DRIVER.get();
    }

    private static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        // Headless by default so the suite runs on CI and without popping windows.
        // Set HEADLESS=false to watch the browser drive itself.
        if (!"false".equalsIgnoreCase(System.getenv("HEADLESS"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // No implicit wait on purpose: mixing implicit and explicit waits leads
        // to unpredictable timing. We rely solely on explicit waits in BasePage.
        return new ChromeDriver(options);
    }

    public static void quitDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }
}
