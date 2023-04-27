package stepDefinition;

import org.openqa.selenium.support.PageFactory;

import actions.Actions;
import actions.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.AmazonHomePage;
import pageObject.AmazonSearchResultsPage;
import pageObject.CartPage;
import pageObject.ProductDetailsPage;
import support.CustomisedException;
import support.DriverSupplier;

public class TheSportsbetStepDef {

	AmazonHomePage amazonHomePage;
	AmazonSearchResultsPage amazonSearchResultsPage;
	CartPage cartPage;
	ProductDetailsPage productDetailsPage;

	@Given("^I launch the (.*) and navigate to Homepage$")
	public void i_launch_the_url_and_navigate_to_Homepage(String url) throws Exception {
		CommonUtils.setBrowserAndUrlDetails();
		DriverSupplier.launchUrl(System.getProperty("browser"), url);
	}

	@When("^I Search for the given keyword (.*)$")
	public void i_Search_for_the_given_keyword(String searchTerm) throws CustomisedException {
		amazonHomePage = PageFactory.initElements(Actions.driver, AmazonHomePage.class);
		
		amazonHomePage.verifyIfNavigatedToHomePage();
		amazonHomePage.enterValueInTextBox(searchTerm);
	}

	@When("Add the product in the first search result to the Cart")
	public void add_the_product_in_the_first_search_result_to_the_Cart() throws CustomisedException {
		amazonSearchResultsPage = PageFactory.initElements(Actions.driver, AmazonSearchResultsPage.class);
		productDetailsPage = PageFactory.initElements(Actions.driver, ProductDetailsPage.class);
		
		amazonSearchResultsPage.getTheFirstSearchResult();
		amazonSearchResultsPage.clickOnTheFirstSearchResult();
		productDetailsPage.clickOnAddToCartButton();
	}

	@When("Navigate to the Cart page")
	public void navigate_to_the_Cart_page() {
		productDetailsPage.clickOnGoToCartButton();
	}

	@Then("I should see the added product in the Cart page")
	public void i_should_see_the_added_product_in_the_Cart_page() throws CustomisedException {
		cartPage = PageFactory.initElements(Actions.driver, CartPage.class);
		
		cartPage.verifyIfNavigatedToCartPage();
		cartPage.verifyAddedItemAvailableInCart();
	}

}
