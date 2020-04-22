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
	
}