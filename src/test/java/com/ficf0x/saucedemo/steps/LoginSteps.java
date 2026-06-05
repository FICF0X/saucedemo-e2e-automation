package com.ficf0x.saucedemo.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.ficf0x.saucedemo.hooks.DriverManager;
import com.ficf0x.saucedemo.pages.InventoryPage;
import com.ficf0x.saucedemo.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Pegamento para login.feature. Cada método corresponde a un paso del Gherkin
 * y delega el trabajo real en el navegador a los Page Objects: los pasos quedan
 * legibles. El texto de cada anotación coincide con el del archivo .feature.
 */
public class LoginSteps {

    private final LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    private final InventoryPage inventoryPage = new InventoryPage(DriverManager.getDriver());

    @Given("el comprador está en la página de inicio de sesión")
    public void elCompradorEstaEnLaPaginaDeLogin() {
        loginPage.open();
    }

    @When("el comprador inicia sesión con credenciales válidas")
    public void elCompradorIniciaSesionConCredencialesValidas() {
        loginPage.loginAs("standard_user", "secret_sauce");
    }

    @Then("se muestra la página de productos")
    public void seMuestraLaPaginaDeProductos() {
        assertThat(inventoryPage.isLoaded()).isTrue();
    }

    @When("el comprador bloqueado intenta iniciar sesión")
    public void elCompradorBloqueadoIntentaIniciarSesion() {
        loginPage.loginAs("locked_out_user", "secret_sauce");
    }

    @Then("se muestra un mensaje de cuenta bloqueada")
    public void seMuestraUnMensajeDeCuentaBloqueada() {
        assertThat(loginPage.getErrorMessage()).containsIgnoringCase("locked out");
    }

    @When("el comprador inicia sesión con un usuario y contraseña desconocidos")
    public void elCompradorIniciaSesionConCredencialesDesconocidas() {
        loginPage.loginAs("ghost_user", "wrong_password");
    }

    @Then("se muestra un mensaje de credenciales inválidas")
    public void seMuestraUnMensajeDeCredencialesInvalidas() {
        assertThat(loginPage.getErrorMessage())
                .containsIgnoringCase("Username and password do not match");
    }
}
