package com.ficf0x.saucedemo.runners;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Punto de entrada de la suite de pruebas.
 *
 * JUnit Platform descubre esta clase y le pasa el control al motor de Cucumber,
 * que carga y ejecuta cada archivo .feature de src/test/resources/features.
 * Maven Surefire ejecuta esta clase con `mvn test`.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class RunCucumberTest {
}
