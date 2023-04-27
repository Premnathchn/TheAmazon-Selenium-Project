package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import actions.Actions;

public class ProductDetailsPage {

	@FindBy(id = "add-to-cart-button")
	private WebElement btnAddToCart;

	@FindBy(id = "sw-gtc")
	private WebElement btnGoToCart;

	/**
	 * Clicks on "Add To Cart" button
	 */
	public void clickOnAddToCartButton() {
		Actions.click(btnAddToCart);
	}

	/**
	 * Clicks on "Go To Cart" button
	 */
	public void clickOnGoToCartButton() {
		Actions.click(btnGoToCart);
	}

}