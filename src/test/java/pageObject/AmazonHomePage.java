package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import actions.Actions;
import support.CustomisedException;

public class AmazonHomePage{

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	private WebElement txtSearchBox;
	
	/**
	 * Verifies if navigated to Amazon home-page
	 * 
	 * @throws CustomisedException
	 */
	public void verifyIfNavigatedToHomePage() throws CustomisedException {
		Actions.assertIsExists(txtSearchBox);
	}
	
	/**
	 * This method enters the given text in the searchBox in Amazon home-page
	 * 
	 * @param textToEnter
	 * @throws CustomisedException
	 */
	public void enterValueInTextBox(String textToEnter) throws CustomisedException {
		Actions.isExists(txtSearchBox);
		Actions.enterText(txtSearchBox, textToEnter);
		Actions.pressEnterKey(txtSearchBox);
	}
	

}