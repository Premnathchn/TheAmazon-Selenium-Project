
# Selenium Java Framework

Begin with installing the dependencies below, and continue with the Getting Started page.

### Dependencies
There are several prerequisite dependencies you should install on your machine prior to starting to work with this framework:

* [Java JDK](https://www.oracle.com/be/java/technologies/javase/jdk11-archive-downloads.html)

* An IDE to write your tests on - [Eclipse](https://www.eclipse.org/downloads/packages/release/2023-03/r/eclipse-ide-java-developersr) or [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)

* [Maven](https://maven.apache.org/) (Optional - Needed only for command line executions as IDEs have Maven in-built.)

Eclipse users should also install:

1. Eclipse has in-built Maven plugin 
    - Optional - [Maven Plugin](http://marketplace.eclipse.org/content/m2e-connector-maven-dependency-plugin)

3. Any Cucumber Plugin
	- For Eclipse users- Cucumber eclipse plugin from Help-> Marketplace

#### Optional Installations
* For source control management, you can install [git](https://git-scm.com/downloads).


## Downloading the framework:

Download or clone the Project [repository](https://github.com/Premnathchn/TheAmazon-Selenium-Project.git).

After downloading and unzipping the project to your computer, open it from your IDE by choosing the folder containing the pom.xml file.

Project directory structure is documented in the end of this page.

**********************
# Getting Started

This procedure leads you through the various framework aspects:

* Framework features
* Execution of the Scenario file
* Test writing guidelines
* Object Repository creation guidelines
* Viewing test execution results

##  Framework features:

	- Used Selenium tool with java to implement the test cases
	
	- Possibility to run the testcases either as JUnit test or using Maven commands in command line
	
	- Used Page Object Model(POM), to store and handle the element locators
	
	- Contructed the testcase using BDD and Gherkin statements
	
	- Scenario outline has been used to improve the re-usability of the Gherkin statements
	
	- All the reusuable methods are available under _src/test/java/actions/CommonUtils.java file
	
	- All the action classes are available under _src/test/java/actions/Actions.java. Eg: click(), SendKeys(), getText() etc.,
	
	- Customised Exception is used to reflect the error messages in the reports in a better way
	
	- Test-data hasn't been hardcoded. Its stored in a properties file for better maintainability. under top _resources/config.properties file
	
	- Handled the foreseen exceptions using try/catch block
	
	- Provide the logs in the step implementation methods whenever required. Eg: logger.debug()
	
	- Reports of previous executions are available with timestamp under top _reports folder
	
	

##  Execution of the Scenario file:

The scenario is located under the _src/test/java/featureFiles folder.

	- Configure your configuration/ browser details in config.properties_ file (under the top _Resources/_ folder).
	
	- Update the feature file path in TestRunner file.
		- @CucumberOptions(features = "<Path to feature file To be updated>"
		- Eg: @CucumberOptions(features = "D:\\Selenium-Automation-Framework\\src\\test\\java\\features",
	
	- Run your test via the right-click on the TestRunner.java file and Run As --> JUnit Test (under _src/test/java/runners/TestRunner.java)
		- Make sure to update the tagName under @CucumberOptions section of this file before execution.
		
	- We also can Run in command line using Maven using the below commands.
		- mvn test -Dcucumber.options="--tags @CartNavigation"
		- We can also pass parameters during run time, using the below command.
			- mvn test -Dbrowser="chrome" -Durl="https://www.amazon.com" -Dcucumber.options="--tags @CartNavigation"


##  Test writing guidelines:

	- Begin with @featuretagname, Feature: name of feature, @scenariotagname (can be the same as the feature's tag).
	
	- Write your scenario using [Given/When/Then/And](https://github.com/cucumber/cucumber/wiki/Given-When-Then) BDD statements.
	
	- Write your first scenario for the app's initial starting point, and later create scenarios for other cases; name them differently to enable easy identification in execution report, and name their tags differently if you want to run them separately.
	
	- Add steps for taking screenshots to allow close examination of test results later on.
	
	- Add steps for waiting a few seconds upon app's page loading.



## Object Repository creation guidelines:

Store your page objects under _src/test/java/pageObject

Follow below guidelines in naming the page object names.
	Eg: btnYesIamHappy, LblFirstHeadlineText, txtSearch etc.,

	- For Label 	=> Start with "lbl"
	- For Button 	=> Start with "btn"
	- For Links	=> Start with "lnk"
	- For EditBox	=> Start with "txt"
	- For List	=> Start with "list"



## Viewing test execution results:

All the previous executions are recorded with time-stamp, and stored under the top _Reports folder.

	- Open any of the .html file with preferred browser to view the results.
	- Screenshots are attached to be each steps of the test-cases.
	- In  case of failures, both logs and screenshots are attached to the report.


**********************

## Project Directory Structure
```
.
│   pom.xml                                              # Maven pom file for build and dependencies  
│   README.md                                            # The current readme file  
│
│   Reports                                              # Reports for all the previous executions
│  
├───Resources                                            # Default Resources dir
│       config.properties                                # set credentials and other project properties
│       chromedriver.exe                                 # chromedriver.exe file according to the compatibility with the local chrome browser
│       geckodriver.exe                                  # geckodriver.exe file according to the compatibility with the local firefox browser
│       IEDriverSErver.exe                               # IEDriverSErver.exe file according to the compatibility with the local IE browser	  
│  
└───src												   		
    └───test  
        └───java                                          
            └───actions                                  # All Common methods and utility base action classes
        	│	    Actions.java                         # All base action classes. Eg: Click, sendKeys, getText etc.
        	│	    CommonUtils.java                     # All common utility methods
        	│
        	├───features                                 # All features files
        	│	    TheSportsbet.feature                 # Feature file for the test-cases for the exercise on Guardian Website.
        	│
        	├───pageObjects                              # All framework page objects
        	│	    AmazonHomePage.java                  # Page objects for Google home page
        	│	    AmazonSearchResultsPage.java         # Page objects for Google search results page
        	│	    CartPage.java                        # Page Objects for Guardian home page
        	│	    ProductDetailsPage.java              # Page Objects for Guardian News Page
        	│		
        	├───runners                                  
        	│	    TestRunner.java                      # TestRunner file to execute the test-cases
        	│		
        	├───stepDefinition                           # All step definition java files
        	│	    TheSportsbetStepDef.java             # Step definition file for 'TheGuardian.feature' file
        	│				
        	└───support                                  # All support classes for the framework
        			CustomisedException.java             # Customised exception java file
        			DriverSupplier.java                  # Driver supplier file where the different drivers are initialised or handled.
        			FileHandling.java                    # File for retrieving the test-data from property file
        			Hooks.java                           # Contains @before and @after method classes
        	 





@Premnath.ayyadurai
``` 