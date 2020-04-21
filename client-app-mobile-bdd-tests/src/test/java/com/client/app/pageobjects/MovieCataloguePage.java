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
    
    public MovieCataloguePage() {  
	
		if (TestRun.isMobile())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getAppiumDriver()), this);
		else if (TestRun.isDesktop())
			PageFactory.initElements(new AppiumFieldDecorator(DriverManagerUtil.getWebDriver()), this);
	}

}
