## iDAP\n
	Intelligent Digital Automation Platform
	
## Overview:\n
	This is mobile automation framework using Appium is entirely driven by Selenium and test runner like TestNG. 
	The framework adapts the modular and data-driven concepts. 
	The entire automation suite can be stored in customers private Git/BitBucket and uses Maven build tool to build and run the tests on physical devices or emulators and browsers. 
	In the end, it create TestNg reports.

## Features:\n
	Supports BDD with Cucumber	
	Supports Parallel Execution
	Supports Build Automation with Maven
	Supports Screenshot Configuration
	Provides Generic Utilities
	Provide Customized TestNG Reports
	Provide Logs
	Easier, Faster and Efficient Analysis of Result	
	Javadoc Plugin

## Pre-requisites:\n
	1. Clone the project in Android Studio
	2. Generate the APK
	3. Launch Emulator, install the application and login in TMDB portal for fetching movie list
	4. For emulator configuration, refer doc src\main\resources\userGuide\Emulator Creation.doc
	
## Steps:\n
	Please refer below steps 
	1. src/test/resources/testngConfigFiles/mobile/local/androidNative.xml with desired capabilities - run as TestNG.suite
	2. RunCucumberFeatures_2 (Main method) to be added in androidNative.xml
	3. src/test/resources/features/MobileCatalogue.feature - contains all steps for test
	4. MovieCataloguePage (page objects) store all page objects
	5. MobileCatalogueStepDefinition (steps of tests) create steps for tests
	
## Implementation:\n
	Please refer below user guide for the implementation
		src/main/resources/userGuide/Movie Catalogue_User Guide.doc
		
## Main challenges and next steps:\n
    1. Scripts are simple in nature, integrating in framework took longer time.
    2. TestNG reports are getting generated. Allure / Extent reports can be implemented.
    3. More scenarios needs to be added.
    
## About:\n
	 This Appium/Selenium based framework is licensed under the terms of the Apache 2.0 License. Please refer LICENSE   file
	 
