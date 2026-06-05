package com.ficf0x.saucedemo.hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Maneja el ciclo de vida del WebDriver.
 *
 * El driver se guarda en un ThreadLocal para que, si en algún momento las
 * pruebas corren en paralelo, cada hilo tenga su propio navegador aislado.
 * El Selenium Manager de Selenium 4 descarga el ChromeDriver correcto solo.
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
        // Headless por defecto para correr en CI y sin abrir ventanas.
        // Usa HEADLESS=false para ver el navegador manejándose solo.
        if (!"false".equalsIgnoreCase(System.getenv("HEADLESS"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // Sin implicit wait a propósito: mezclar esperas implícitas y explícitas
        // genera tiempos impredecibles. Usamos solo esperas explícitas en BasePage.
        return new ChromeDriver(options);
    }

    public static void quitDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }
}
