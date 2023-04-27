package pageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import actions.Actions;
import actions.CommonUtils;
import support.CustomisedException;

public class CartPage {

	@FindBy(id = "sc-cart-column")
	private WebElement imgCartColumn;

	@FindBy(xpath = "//span[@class='a-truncate-cut']")
	private List<WebElement> listItemsInCart;

	private static Logger logger = Logger.getLogger(Actions.class);

	/**
	 * Verifies if navigated to Cart Page by checking if the Active cart section is
	 * visible
	 */
	public void verifyIfNavigatedToCartPage() {
		Actions.assertIsExists(imgCartColumn);
	}

	/**
	 * Verifies if added item present in Cart
	 * 
	 * @throws CustomisedException
	 */
	public void verifyAddedItemAvailableInCart() throws CustomisedException {
		String addedProductName = System.getProperty("strFirstProductName");

		boolean isPresent = CommonUtils.IsStringPresentInAList(listItemsInCart, addedProductName);

		if (isPresent) {
			logger.debug("Added product is present in the Cart");
		} else {
			throw new CustomisedException("Added product not present in the Cart: " + addedProductName);
		}
	}

}