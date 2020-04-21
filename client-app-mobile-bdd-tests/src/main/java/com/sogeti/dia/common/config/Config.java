package com.sogeti.dia.common.config;

/**
 * Class to declare constants
 *
 */
public class Config {
	//Desktop Grid details
	public static final String GRID_HUB_IP = "localhost";
	public static final String GRID_HUB_PORT = "4443";
	public static final String GRID_URL = "http://" + GRID_HUB_IP + ":" + GRID_HUB_PORT + "/wd/hub";
	
	//Browser Stack
	public static final String BROWSER_STACK_USER_NAME = ""; 
	public static final String BROWSER_STACK_ACCESS_KEY = ""; 
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_STACK_USER_NAME + ":" + BROWSER_STACK_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	//SauceLabs details
	public static final String SAUCELABS_USER_NAME = "";
	public static final String SAUCELABS_ACCESS_KEY = "";
	public static final String SAUCELABS_APPIUM_URL = "http://" + SAUCELABS_USER_NAME + ":" + SAUCELABS_ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";	
	public static final String SAUCELABS_SELENIUM_URL = "http://" + SAUCELABS_USER_NAME + ":" + SAUCELABS_ACCESS_KEY + "@ondemand.saucelabs.com/wd/hub";	
		
	//TestObject details - US data center
	public static final String TEST_OBJECT_URL = "http://us1.appium.testobject.com/wd/hub";	
	
	//Perfecto details
	public static final String PERFECTO_USER_NAME = ""; 
	public static final String PERFECTO_SECURITY_TOKEN = ""; 
	public static final String PERFECTO_HOST = "partners.perfectomobile.com";
	public static final String PERFECTO_URL = "https://" + PERFECTO_HOST + "/nexperience/perfectomobile/wd/hub";		

	//pCloudy
	public static final String PCLOUDY_USER_NAME = "";
	public static final String PCLOUDY_ACCESS_KEY = "";
	public static final String PCLOUDY_URL = "https://device.pcloudy.com/appiumcloud/wd/hub";	
	
	//Kobiton
	public static final String KOBITON_SERVER_URL = "";	
		
	//BitBar details
	public static final String TESTDROID_API_KEY = "";		
	public static final String BITBAR_URL = "http://appium.bitbar.com/wd/hub";
				
	//SeeTest
	public static final String SEETEST_ACCESS_KEY = "";
	public static final String SEETEST_URL = "https://cloud.seetest.io/wd/hub";		
					
	//HPE Mobile Center
	public static final String HPE_MC_URL = "";	
	public static final String HPE_MC_USERNAME = "";
	public static final String HPE_MC_PASSWORD = "";
	
	//Applitools API key
	public static final String APPLITOOLS_API_KEY = "";
		
	//Voice automation
	public static final String VOICE_RSS_API_KEY = "";
	public static final String VOICERSS_BASEURL = "https://api.voicerss.org/?";
	
	//Allure Screenshot capture
	public static final String SCREENSHOT_CAPTURE = "EveryStep"; //EveryStep or LastStep
	
	//RemoteWebdriver Config
	public static final int MAX_WAIT_TIME = 180;
	public static final int POLLING_TIME = 500;		
	public static final int XSMALL_PAUSE = 3;
	public static final int SMALL_PAUSE = 10;
	public static final int MEDIUM_PAUSE = 30;
	public static final int LARGE_PAUSE = 60;
	public static final int XLARGE_PAUSE = 120;
	
	//File Paths
	public static final String SELENIUM_GRID_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\grid\\SeleniumGrid.bat";
	public static final String LOCALIZATION_FOLDER_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\localization\\";

	//App Config
	public static final String ANDROID_APP_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\apps\\";
	public static final String IOS_REAL_DEVICE_APP_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\apps\\";
	public static final String IOS_SIMULATOR_APP_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\apps\\";
	
	//Web App
	public static final String APP_URL = "https://opensource-demo.orangehrmlive.com/";
	
	//DB Config	
	public static final String DB_HOST = "";
	public static final String DB_PORT = "";
	public static final String DB_SID = "";
	public static final String DB_USER_ID = "";
	public static final String DB_PASSWORD = "";
}