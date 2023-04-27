package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import actions.Actions;
import support.CustomisedException;

public class AmazonSearchResultsPage {

	@FindBy(xpath = "//span[@class='a-size-medium a-color-base a-text-normal']")
	private List<WebElement> listSearchResults;

	/**
	 * Clicks on the first search result
	 */
	public void clickOnTheFirstSearchResult() {
		Actions.click(listSearchResults.get(0));
	}

	/**
	 * Retrieves the name of first product and stores it in "strFirstProductName"
	 * 
	 * @throws CustomisedException
	 */
	public void getTheFirstSearchResult() throws CustomisedException {
		Actions.assertIsExists(listSearchResults.get(0));
		String strFirstProductName = Actions.getText(listSearchResults.get(0));
		System.setProperty("strFirstProductName", strFirstProductName);
	}

}