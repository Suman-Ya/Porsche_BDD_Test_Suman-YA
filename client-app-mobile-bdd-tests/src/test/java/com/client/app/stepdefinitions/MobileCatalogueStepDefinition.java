package com.client.app.stepdefinitions;

import org.testng.Assert;
import com.client.app.common.utils.MovieCatalogueUtil;

import cucumber.api.java.en.And;
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
  	
  	@Then("^I click on back button$")
    public void i_click_on_back_button() throws Throwable {
        boolean status = movieCatalogueUtil.tapOnElement("BACKBTN");
        Assert.assertTrue(status, "BACKBTN" + " is not displayed."); 
    }
  	
  	@And("^I verify favorites button$")
    public void i_verify_favorites_button() throws Throwable {
        boolean status = movieCatalogueUtil.isElementDisplayed("FAV");
        Assert.assertTrue(status, "FAV" + " is not displayed.");    
    }
  	
  	@Then("^I tap on favorites button$")
    public void i_tap_on_favorites_button() throws Throwable {
        boolean status = movieCatalogueUtil.tapOnElement("FAVORITES");
        Assert.assertTrue(status, "FAVORITES" + " is not clicked to navigate to favorites screen.");    
    }
  	
  	@Then("^I verify favorites movies list$")
    public void i_verify_favorite_movies_list() throws Throwable {
        boolean status = movieCatalogueUtil.isElementDisplayed("FAVMOVIETAB");
        Assert.assertTrue(status, "FAVMOVIETAB" + " is not displayed."); 
    }
}

