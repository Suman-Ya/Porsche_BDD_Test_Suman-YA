package com.client.app.testrunner;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.AllureManagerUtil;
import com.sogeti.dia.common.utils.CapabilityFactoryUtil;
import com.sogeti.dia.common.utils.DriverManagerUtil;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(      
        features = {"src/test/resources/features/MobileCatalogue.feature"},
        glue = {"com.client.app.stepdefinitions"},             
        monochrome = true,
        dryRun = false,
        tags = {"@MovieCatalogue"}
) 
public class RunCucumberFeatures_2 {
    private TestNGCucumberRunner testNGCucumberRunner;
	
    @BeforeSuite
    public void suiteSetup(ITestContext context) { 
    		  	if (context.getCurrentXmlTest().getParameter("interfaceType").equalsIgnoreCase("Mobile") && 
				context.getCurrentXmlTest().getParameter("environment").equalsIgnoreCase("Local"))
    	  		DriverManagerUtil.startSeleniumGrid();
	}
    
    @BeforeClass(alwaysRun = true)
    public void classSetUp() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeTest
	public void testSetUp(ITestContext context) {
		TestRun.setInterfaceType(context.getCurrentXmlTest().getParameter("interfaceType"));
		TestRun.setEnvironment(context.getCurrentXmlTest().getParameter("environment"));	
		TestRun.setAppType(context.getCurrentXmlTest().getParameter("appType"));
    	TestRun.setLanguage(context.getCurrentXmlTest().getParameter("language"));
			
		//Launching existing app
		TestRun.setLaunchExistingApp(context.getCurrentXmlTest().getParameter("launchExistingApp"));
			
		//Common in Android & iOS
		TestRun.setDeviceName(context.getCurrentXmlTest().getParameter("deviceName"));		
		TestRun.setPlatformName(context.getCurrentXmlTest().getParameter("platformName"));		
		TestRun.setPlatformVersion(context.getCurrentXmlTest().getParameter("platformVersion"));
		TestRun.setAutomationName(context.getCurrentXmlTest().getParameter("automationName"));

		//TestObject
		TestRun.setSuiteName(context.getCurrentXmlTest().getParameter("suiteName"));
		TestRun.setTestName(context.getCurrentXmlTest().getParameter("testName"));	
					
		//Native & Hybrid Application details
		TestRun.setAndroidAPK(context.getCurrentXmlTest().getParameter("androidAPK"));		
		TestRun.setIOSIPA( context.getCurrentXmlTest().getParameter("iOSIPA"));		
		TestRun.setIOSAPP(context.getCurrentXmlTest().getParameter("iOSAPP"));
		TestRun.setAppPackage(context.getCurrentXmlTest().getParameter("appPackage"));		
		TestRun.setAppActivity(context.getCurrentXmlTest().getParameter("appActivity"));		
		TestRun.setBundleId(context.getCurrentXmlTest().getParameter("bundleId"));
		
		/*//Parallel Execution
		TestRun.setIPAddress(context.getCurrentXmlTest().getParameter("ipAddress"));	
		
		//Windows app path
		TestRun.setAppPath(context.getCurrentXmlTest().getParameter("appPath"));	*/
			
		//Start Appium Server
		DriverManagerUtil.startAppiumServer();	
	}	
	
    @Test(groups = "cucumber", description = "Run Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) throws InterruptedException {    
    	CapabilityFactoryUtil.initiateDriver();      	
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }
    
    @AfterMethod
    public void scenarioTearDown(){
		if ((TestRun.isDesktop() || TestRun.isMobile()) && (Config.SCREENSHOT_CAPTURE.equalsIgnoreCase("LastStep")))
			AllureManagerUtil.attachScreenshot(); 
	   
		DriverManagerUtil.stopAppiumDriver();
		DriverManagerUtil.stopWebDriver();
		DriverManagerUtil.stopWindowsDriver();
    }	

    @AfterClass(alwaysRun = true)
    public void classTearDown() throws Exception {
        testNGCucumberRunner.finish();
    }
    
    @AfterSuite
    public void suiteTearDown() {  
		DriverManagerUtil.stopAppiumServer();
		DriverManagerUtil.stopSeleniumGrid();
	}
}