package test.steps;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources", format = { "html:target/cucumber-html-report"})
public class RunCukesTest {

}
