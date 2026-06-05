package com.ficf0x.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * La página de productos (inventario) que se muestra tras un login exitoso.
 */
public class InventoryPage extends BasePage {

    private final By pageTitle = By.cssSelector(".title");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("/inventory.html")
                && "Products".equals(textOf(pageTitle));
    }
}
