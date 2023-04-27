@TheSportsbet
Feature: Feature for Amazon Cart functionalities

  @CartNavigation
  Scenario Outline: Verify able to navigate to the Cart section of Amazon.com
    Given I launch the <URL> and navigate to Homepage
	When I Search for the given keyword <Searchterm>
	And Add the product in the first search result to the Cart
	And Navigate to the Cart page
	Then I should see the added product in the Cart page

    Examples:
      | 		URL			| 	Searchterm		|
      | https://amazon.com	| iPhone 14 Pro		|

