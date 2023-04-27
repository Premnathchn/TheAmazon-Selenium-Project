package support;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.core.exception.CucumberException;

/**
 * <h1>Class to define the different driver capabilities
 * <h1>
 * 
 * @author Premnath Ayyadurai
 * @since 20 April 2023
 *
 */
public class DriverSupplier {
	public static WebDriver driver;

	/**
	 * This method initialises the chromedriver
	 * 
	 * @return
	 */
	public WebDriver driverChrome() {

		System.setProperty("webdriver.chrome.driver", "Resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();

		options.setExperimentalOption("excludeSwitches", Arrays.asList("diable-popup-blocking"));
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		// options.addArguments("--headless");
		options.addArguments("disable-infobars");
		// options.addArguments("--incognito");
		options.addArguments("--disable-extensions");

		driver = new ChromeDriver(options);
		return driver;

	}

	/**
	 * This method initialises the IEDriver
	 * 
	 * @return
	 */
	public WebDriver driverIE() {

		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", "Resources//IEDriverServer.exe");
		driver = new InternetExplorerDriver(capabilities);
		return driver;

	}

	/**
	 * This method initialises firefox/ geckodriver
	 * 
	 * @return
	 */
	public WebDriver driverFirfox() {
		System.setProperty("webdriver.gecko.driver", "Resources//geckodriver.exe");
		DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
		firefoxCapabilities.setCapability("marionette", true);
		driver = new FirefoxDriver();

		return driver;

	}

	/**
	 * This method initialises remotedriver
	 * 
	 * @return
	 */
	public WebDriver remotedriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		RemoteWebDriver driver = new RemoteWebDriver(capabilities);

		return driver;

	}

	/**
	 * @return
	 */
	public WebDriver htmlUnit() {

		WebDriver driver = new HtmlUnitDriver();
		((HtmlUnitDriver) driver).setJavascriptEnabled(true);

		return driver;

	}

	/**
	 * This method launches the URL in the given browser
	 * 
	 * @param browser
	 * @param url
	 * @throws Exception
	 */
	public static void launchUrl(String browser, String url) throws Exception {
		DriverSupplier objDriver = new DriverSupplier();

		switch (browser.toUpperCase()) {

		case "HEADLESS":
		case "HTMLUNIT":
			driver = objDriver.htmlUnit();
			break;

		case "CHROME":
			driver = objDriver.driverChrome();
			break;

		case "FIREFOX":
			driver = objDriver.driverFirfox();
			break;

		case "INTERNET EXPLORER":
		case "IE":
			System.out.println("ÏE Driver");
			driver = objDriver.driverIE();
			break;

		default:
			throw new CucumberException("Given broswer name is not a valid one");
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url.trim());
	}

	/**
	 * This method closed the active browser window
	 * 
	 * @throws CustomisedException
	 */
	public static void closeBrowser() throws CustomisedException {
		try {
			driver.close();
		} catch (Exception e) {

		}
	}

	/**
	 * this method closes all the active driver window and quits the driver
	 */
	public static void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {

		}
	}

}
