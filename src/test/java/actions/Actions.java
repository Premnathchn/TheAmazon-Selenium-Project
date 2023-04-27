package actions;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import io.cucumber.core.exception.CucumberException;
import support.CustomisedException;
import support.DriverSupplier;

/**
 * <h1>List of reusable methods for the framework
 * <h1>
 * 
 * @author Premnath Ayyadurai
 * @since 20 April 2023
 */
public class Actions extends DriverSupplier {

	CustomisedException obj;

	private static Logger logger = Logger.getLogger(Actions.class);

//===============================================================================
//
// Click, SendKeys and getText()
//
//===============================================================================

	/**
	 * This method allows to enter value into a text box., i.e, SendKeys()
	 * 
	 * @param element
	 * @param valueToEnter
	 */
	public static void enterText(WebElement element, String valueToEnter) {
		logger.debug("enterText process started.");
		try {
			element.sendKeys(valueToEnter);
			logger.info("The value '" + valueToEnter + "' has been entered");
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		logger.debug("enterText process Completed.");
	}

	/**
	 * Common method for click() action
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {
		try {
			logger.debug("click on webelement process started.");
			element.click();
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		logger.debug("click on webelement process completed.");
	}

	/**
	 * Common method to click if the WebElement exists
	 * 
	 * @param element
	 */
	public static void clickIfExists(WebElement element) {
		logger.debug("clickIfExists element process started.");
		try {
			if (element.isDisplayed()) {
				click(element);
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		logger.debug("clickIfExists weblink process completed.");
	}

	/**
	 * This method allows to get the content of a WebElement
	 * 
	 * @param webElement
	 * @return
	 */
	public static String getText(WebElement webElement) {
		logger.debug("Get Text from WebElement Process Started...");
		String extractedTextFromElement;
		try {
			extractedTextFromElement = webElement.getText();
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		logger.debug("Get Text from WebElement Process completed.");
		return extractedTextFromElement;
	}

	/**
	 * This method allows to click on a WebElement using javascriptExecutor
	 * 
	 * @param element
	 * @throws CustomisedException
	 */
	public static void javaScriptClick(WebElement element) throws CustomisedException {
		try {
			CommonUtils.elementHighlighter(element);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);

		} catch (Exception e) {
			throw new CustomisedException("Exception on method- javaScriptClick: " + e.getMessage());
		}

	}

	/**
	 * This method allows to press enter key on a WebElement
	 * 
	 * @param element
	 * @throws CustomisedException
	 */
	public static void pressEnterKey(WebElement element) throws CustomisedException {
		try {
			element.sendKeys(Keys.RETURN);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- clickEnterKey: " + e.getMessage());
		}
	}

//===============================================================================
//
// Assertions
//
//===============================================================================

	/**
	 * This method returns yes/no after checking if an element exists in a webpage
	 * 
	 * @param webElement
	 * @return
	 */
	public static boolean isExists(WebElement webElement) {
		logger.debug("Is Web Element exists process started...");
		boolean isObjectExists = false;
		try {
			isObjectExists = webElement.isDisplayed();
		} catch (Exception e) {
			logger.error(e);
		}
		logger.debug("Is Web Element exists process completed.");
		return isObjectExists;
	}

	/**
	 * This method allows to check if an element exists in a web-page, and fails the
	 * step if not.
	 * 
	 * @param isDisplayed
	 */
	private static void assertDisplayed(boolean isDisplayed) {
		if (isDisplayed) {
			logger.info("Expected field is displayed in page.");
			CommonUtils.takeSnapShot("");
		} else {
			logger.error("Expected field is not displayed in page.");
			throw new CucumberException("Expected field is not displayed in page.");
		}
	}

	/**
	 * This method allows to check if an element exists in a page and returns nothing
	 * 
	 * @param webElement
	 */
	public static void assertIsExists(WebElement webElement) {
		logger.debug("Assert WebElement '" + webElement.hashCode() + "' exists process started...");
		boolean isFieldDisplayed = false;

		try {
			isFieldDisplayed = webElement.isDisplayed();
			CommonUtils.elementHighlighter(webElement);
		} catch (Exception e) {
			isFieldDisplayed = false;
		}
		assertDisplayed(isFieldDisplayed);
		logger.debug("Assert WebElement exists process Completed.");
	}

	/**
	 * This method allows to compare the text associated with a WebElement with a given string value
	 * 
	 * @param webElement
	 * @param expectedText
	 */
	public static void assertFieldTextEquals(WebElement webElement, String expectedText) {
		logger.debug("Asserting text process started...");
		String actualText = getText(webElement);

		assertTextEquals(expectedText, actualText.trim());
		logger.debug("Assert text process Completed.");
	}

	/**
	 * This method allows to compare two string text values
	 * 
	 * @param expectedText
	 * @param actualText
	 */
	public static void assertTextEquals(String expectedText, String actualText) {
		logger.debug("Asserting text process started...");
		try {
			Assert.assertEquals(expectedText, actualText);
			CommonUtils.takeSnapShot("");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.debug("Assert text process Completed.");
	}

}
