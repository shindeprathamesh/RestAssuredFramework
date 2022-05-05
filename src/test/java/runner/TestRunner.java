package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
				features= "src/test/java/features",
				glue = "stepdefinitions",
				plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","html:target/cucumber-html-report.html", "json:target/cucumber.json"},
				monochrome = true
				)

public class TestRunner {
	
	

}
