package com.client.app.common.utils;

import com.client.app.pageobjects.MovieCataloguePage;
import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.utils.DriverManagerUtil;
import com.sogeti.dia.common.utils.LoggerUtil;
import com.sogeti.dia.common.utils.MobileInteractUtil;
import com.sogeti.dia.common.utils.WebInteractUtil;

public class MovieCatalogueUtil {
    protected MovieCataloguePage movieCataloguePage;

    public MovieCatalogueUtil() {
    	movieCataloguePage = new MovieCataloguePage();
    }
    
    public boolean isElementDisplayed(String mobileElementTitle) {
		boolean status = false;
		switch (mobileElementTitle.toUpperCase()) {
			case "HOMEPAGE":  {
					status = MobileInteractUtil.isPresent(movieCataloguePage.Homepage, Config.MEDIUM_PAUSE);
					break;
			} 
			case "DETAILSPAGE": {
				status = MobileInteractUtil.isPresent(movieCataloguePage.Detailspage, Config.MEDIUM_PAUSE);						
				break;
			}
}
		if (status)
			LoggerUtil.logMessage(mobileElementTitle + " is displayed.");			
		else
			LoggerUtil.logMessage(mobileElementTitle + " is not displayed.");
		return status;
    }
		
    
	public boolean tapOnElement(String mobileElement) {
		boolean status = false;		
		switch(mobileElement.toUpperCase()) {
			case "HOMEPAGE": {
					status = MobileInteractUtil.click(movieCataloguePage.Homepage);						
					break;
				}
		}
			if(status) {
				LoggerUtil.logMessage("Clicked on " + mobileElement + " on Home screen.");			
			}
			else
				LoggerUtil.logMessage("Could not click on " + mobileElement + " on Home screen.");
		
			return status;
	}
}
