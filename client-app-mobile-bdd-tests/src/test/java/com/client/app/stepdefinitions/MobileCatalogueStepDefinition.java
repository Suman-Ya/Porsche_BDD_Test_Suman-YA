package com.client.app.stepdefinitions;

import org.testng.Assert;
import com.client.app.common.utils.MovieCatalogueUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MobileCatalogueStepDefinition {

protected MovieCatalogueUtil movieCatalogueUtil;
	
	public MobileCatalogueStepDefinition() {
		movieCatalogueUtil = new MovieCatalogueUtil();
	}
		
	 @Given("^I am on the Movie Catalogue screen$")
     public void i_am_on_the_Movie_Catalogue_screen() throws Throwable {
		 boolean status = movieCatalogueUtil.isElementDisplayed("HOMEPAGE");
		 Assert.assertTrue(status, "HOMEPAGE" + " is not displayed.");    
     }
	
	@When("^I tap on the \"([^\"]*)\" in home screen$")
    public void i_tap_on_the_in_home_screen(String mobileElementTitle) throws Throwable {
        boolean status = movieCatalogueUtil.tapOnElement("HOMEPAGE");
        Assert.assertTrue(status, mobileElementTitle + " is not clicked to navigate to details screen.");    
    }
  	
  	@Then("^I verify Movie detail screen$")
    public void i_verify_Movie_detail_screen() throws Throwable {
        boolean status = movieCatalogueUtil.isElementDisplayed("DETAILSPAGE");
        Assert.assertTrue(status, "DETAILSPAGE" + " is not displayed."); 
    }
}

