@MobileVerification
Feature: Verify Mobile Scenarios

@MovieCatalogue
Scenario Outline: MovieCatalogue Test    

Given I am on the Movie Catalogue screen
When I tap on the "<movie>" in home screen
Then I verify Movie detail screen
Then I click on back button
And I verify favorites button
Then I tap on favorites button
Then I verify favorites movies list

Examples:
|movie|
|Ad Astra|