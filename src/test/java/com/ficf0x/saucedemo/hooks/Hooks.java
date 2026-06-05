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
 * Cucumber lifecycle hooks. Runs around every scenario.
 *
 * On failure it captures a screenshot — attached to the Cucumber report and
 * saved under target/screenshots — because the first thing a QA engineer does
 * with a failing UI test is look at what the browser actually showed.
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
