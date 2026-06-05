package com.ficf0x.saucedemo.runners;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Entry point for the test suite.
 *
 * JUnit Platform discovers this class, hands control to the Cucumber engine,
 * which loads every .feature file under src/test/resources/features and runs it.
 * Maven Surefire runs this class on `mvn test`.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class RunCucumberTest {
}
