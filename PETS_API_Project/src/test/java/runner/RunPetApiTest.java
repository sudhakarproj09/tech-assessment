///**
// Author: Sudhakar Madamala
// History: New cucumber report addition
//*
package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/java/features",
        plugin = {"pretty", "html:target/cucumber-reports"},
        glue = {"steps"},
        monochrome = true
)

public class RunPetApiTest {

}
