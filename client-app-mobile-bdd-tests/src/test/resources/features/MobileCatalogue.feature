@MobileVerification
Feature: Verify Mobile Scenarios

 

@MovieCatalogue
Scenario Outline: MovieCatalogue Test    

 

Given I am on the Movie Catalogue screen
When I tap on the "<movie>" in home screen
Then I verify Movie detail screen

 

Examples:
|movie|
|Ad Astra|