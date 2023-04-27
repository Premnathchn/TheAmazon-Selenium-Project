package runners;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import support.CustomisedException;
import support.DriverSupplier;
import support.Hooks;

@RunWith(Cucumber.class)
@CucumberOptions(features = "D:\\Work\\Interviews\\Yolo\\Selenium Automation Framework\\src\\test\\java\\features", plugin = {
		"pretty","json:target/cucumber.json",}, glue = {"stepDefinition", "support"}, tags = " @CartNavigation")

public class TestRunner extends AbstractTestNGCucumberTests{

	@AfterClass
	public static void postTest() throws IOException, CustomisedException {
		Hooks obj = new Hooks();
		obj.reportConfiguration();

		DriverSupplier.closeBrowser();
		Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
	}

}
