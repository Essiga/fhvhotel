package at.fhv.hotelsoftware.bdd.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/",
        glue = {"at.fhv.hotelsoftware.bdd"},
        plugin = {"pretty", "html:build/cucumber/hotelsoftware.html"})

public class CucumberTestRunner {
}
