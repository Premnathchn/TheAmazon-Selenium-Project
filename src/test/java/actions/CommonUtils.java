package actions;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import support.CustomisedException;
import support.FileHandling;

/**
 * <h1>Reusable keywords to handle 'Web objects'.
 * <h1>
 * 
 * @author Premnath Ayyadurai
 * @since 20 April 2023
 *
 */
public class CommonUtils extends Actions {

	private static Logger logger = Logger.getLogger(Actions.class);

	/**
	 * This method return the count of matching instances from the given list of
	 * WebElement
	 * 
	 * @param listWebElement
	 * @param strExpectedValue
	 * @param count
	 * @return
	 * @throws CustomisedException
	 */
	public static int getCountOfMatchingElements(List<WebElement> listWebElement, String strExpectedValue)
			throws CustomisedException {

		int count = 0;
		try {
			for (WebElement element : listWebElement) {

				if (element.getText().contains(strExpectedValue)) {
					count++;
					if (count > 2) {
						break;
					} else {
						continue;
					}
				} else {
					continue;
				}
			}
			logger.debug("Matching instances: " + count);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- getCountOfMatchingElements:: " + e.getMessage());
		}

	}

	/**
	 * This method allows to wait for a given time until the element is visible
	 * 
	 * @param waitTime
	 * @param element
	 * @throws CustomisedException
	 */
	public static void webWait(int waitTime, WebElement element) throws CustomisedException {
		try {

			Wait<WebDriver> wait = new FluentWait<WebDriver>(Actions.driver).withTimeout(waitTime, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS).ignoring(NullPointerException.class);

			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- webWait" + e.getMessage());
		}
	}

	/**
	 * This method allows the driver to wait until the element is clickable
	 * 
	 * @param waitTime
	 * @param element
	 * @throws Exception
	 */
	public static void webWaitUntilClickable(int waitTime, WebElement element) throws Exception {
		try {

			Wait<WebDriver> wait = new FluentWait<WebDriver>(Actions.driver).withTimeout(waitTime, TimeUnit.SECONDS)
					.pollingEvery(1, TimeUnit.SECONDS).ignoring(NullPointerException.class);

			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method-webWaitUntilClickable: " + e.getMessage());
		}
	}

	/**
	 * This method allows to take the screenshot of the page and attach it to the
	 * report
	 * 
	 * @param pageName
	 * @return
	 */
	public static String takeSnapShot(String pageName) {
		String path = null;
		try {
			Date dNow = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("Eyyyy.MM.dd'_'hh:mm:ssa");
			String timeStamp = ft.format(dNow).replaceAll(":", "_");

			String screenshotsDir = "test-output//ScreenShots//";

			String filename = pageName + "_" + timeStamp + ".png";
			Path screenshotPath = Paths.get(screenshotsDir, filename);

			TakesScreenshot scrShot = ((TakesScreenshot) driver);

			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			// Copy file at destination
			FileUtils.copyFile(SrcFile, screenshotPath.toFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;

	}

	/**
	 * To highlight the elements in red colour
	 * 
	 * @param element
	 * @throws CustomisedException
	 */
	public static void elementHighlighter(WebElement element) throws CustomisedException {
		try {
			// draw a border around the found element
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- elementHighlighter: " + e.getMessage());
		}
	}

	/**
	 * This method opens the URL in a new window
	 * 
	 * @param url
	 * @throws Exception
	 */
	public static void openURLInSameTab(String url) throws Exception {

		try {
			driver.get(url);
			logger.debug(url + " opened '" + url + "' in the same tab");
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- openURLInNewTab: " + e.getMessage());
		}

	}

	/**
	 * This method allows to switch to a window based on index
	 * 
	 * @param index
	 * @throws Exception
	 */
	public static void switchToWindowBasedOnIndex(int index) throws Exception {
		try {
			driver.switchTo().window((String) driver.getWindowHandles().toArray()[index]);
			logger.debug("switched to window with index: " + index);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- switchToWindowBasedOnIndex: " + e.getMessage());
		}
	}

	/**
	 * To switch to iframe based on WebElement
	 * 
	 * @param iframeElement
	 * @throws CustomisedException
	 */
	public static void switchToIframe(WebElement iframeElement) throws CustomisedException {
		try {
			Actions.driver.switchTo().frame(iframeElement);
			logger.debug("Switched to iframe with webelement: " + iframeElement);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- switchToIframe: " + e.getMessage());
		}
	}

	/**
	 * To switch to iframe based on string values of ID/Name of iframe
	 * 
	 * @param str
	 * @throws CustomisedException
	 */
	public static void switchToIframe(String str) throws CustomisedException {
		try {
			Actions.driver.switchTo().frame(str);
			logger.debug("Switched to iframe with ID/Name: " + str);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- switchToIframe: " + e.getMessage());
		}
	}

	/**
	 * To switch to iframe with given index
	 * 
	 * @param index
	 * @throws CustomisedException
	 */
	public static void switchToIframe(int index) throws CustomisedException {
		try {
			driver.switchTo().frame(index);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- switchToIframe: " + e.getMessage());
		}
	}

	/**
	 * To get and set the browser and URL details to a system variable
	 * 
	 * @throws Exception
	 */
	public static void setBrowserAndUrlDetails() throws Exception {

		try {
			String browser = System.getProperty("browser");
			String url = System.getProperty("url");

			if (browser == null) {
				browser = FileHandling.getProperty("browser");
				System.setProperty("browser", browser);
			}

			if (url == null) {
				url = FileHandling.getProperty("url");
				System.setProperty("url", url);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- setBrowserAndUrlDetails: " + e.getMessage());
		}
	}

	/**
	 * This method return the count of matching instances from the given list of
	 * WebElement
	 * 
	 * @param listWebElement
	 * @param strExpectedValue
	 * @param count
	 * @return
	 * @throws CustomisedException
	 */
	public static boolean IsStringPresentInAList(List<WebElement> listWebElement, String strExpectedValue)
			throws CustomisedException {

		boolean isPresent = false;
		try {
			for (WebElement element : listWebElement) {
				
				String firstProductInCart = element.getText();

				if (firstProductInCart.equalsIgnoreCase(strExpectedValue)) {
					isPresent = true;
					break;
				}
			}
			return isPresent;

		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomisedException("Exception in method- getCountOfMatchingElements:: " + e.getMessage());
		}

	}
}
