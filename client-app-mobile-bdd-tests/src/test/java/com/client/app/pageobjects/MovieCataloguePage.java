package com.client.app.pageobjects;

import org.openqa.selenium.support.PageFactory;
import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.DriverManagerUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MovieCataloguePage {
    //Home screen
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"image poster\"])[2]")
    public MobileElement Homepage;

    //Details page
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"image poster\"]")
    public MobileElement Detailspage;
    
    //Favorites button
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Favorite\"]")
    public MobileElement Favoritesbtn;
  
    //FavMovie Tab
    @AndroidFindBy(xpath = "//androidx.appcompat.app.ActionBar.Tab[@content-desc=\"MOVIES\"]")
    public MobileElement FavMovieTab;
  
    //Back button from details screen
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    public MobileElement Backbtn;
    
    public MovieCataloguePage() {  
	
		if (TestRun.isMobile())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getAppiumDriver()), this);
		else if (TestRun.isDesktop())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getWebDriver()), this);
	}

}
