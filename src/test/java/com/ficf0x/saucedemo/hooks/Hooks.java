package com.ficf0x.saucedemo.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Hooks del ciclo de vida de Cucumber. Se ejecutan alrededor de cada escenario.
 *
 * Ante un fallo captura una pantalla —adjunta al reporte de Cucumber y guardada
 * en target/screenshots— porque lo primero que hace un QA con una prueba de UI
 * que falla es mirar qué mostró realmente el navegador.
 */
public class Hooks {

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (scenario.isFailed() && driver instanceof TakesScreenshot) {
            byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(png, "image/png", scenario.getName());
            saveToDisk(scenario, png);
        }
        DriverManager.quitDriver();
    }

    private void saveToDisk(Scenario scenario, byte[] png) {
        try {
            Path dir = Path.of("target", "screenshots");
            Files.createDirectories(dir);
            String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9-_]", "_");
            Files.write(dir.resolve(safeName + ".png"), png);
        } catch (IOException e) {
            System.err.println("Could not save screenshot: " + e.getMessage());
        }
    }
}
