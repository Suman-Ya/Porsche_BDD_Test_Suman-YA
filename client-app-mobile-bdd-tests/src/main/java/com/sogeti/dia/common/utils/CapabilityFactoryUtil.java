package com.sogeti.dia.common.utils;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;

import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * Class to set desired capabilities
 *
 */
public class CapabilityFactoryUtil {		
	/**********************************************************************************************
	 * To set the Desired Capabilities for local execution
	 * 
	 * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
	 * @version 1.0 March 27, 2018
	 ***********************************************************************************************/	
	public static DesiredCapabilities localMobile() {
		DesiredCapabilities capabilities = new DesiredCapabilities();	
    	
    	if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {
		   if (!TestRun.isLaunchExistingApp())
    			capabilities.setCapability("app", Config.ANDROID_APP_PATH + TestRun.getAndroidAPK());
    		
			capabilities.setCapability("appPackage", TestRun.getAppPackage());
			capabilities.setCapability("appActivity", TestRun.getAppActivity());
    	}
    	
    	else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	    	    		
			capabilities.setCapability("showXcodeLog", true);	
			capabilities.setCapability("usePrebuiltWDA", true);
			capabilities.setCapability("noReset", true); //It will clear app data
			capabilities.setCapability("useNewWDA", false); // If true , forces uninstall of any existing WebDriverAgent app on device.
			capabilities.setCapability("bundleId", TestRun.getBundleId()); 
			
			if(TestRun.getIOSDeviceType().equalsIgnoreCase("Real") && !TestRun.isLaunchExistingApp())
				capabilities.setCapability("app", Config.IOS_REAL_DEVICE_APP_PATH + TestRun.getIOSIPA());		    			   				    								
			
			else if (TestRun.getIOSDeviceType().equalsIgnoreCase("Simulator")) {
				if (!TestRun.isLaunchExistingApp())
					capabilities.setCapability("app", Config.IOS_SIMULATOR_APP_PATH + TestRun.getIOSAPP());
				capabilities.setCapability("language", TestRun.getAppLanguage());
				capabilities.setCapability("locale", TestRun.getLocale());
			}
 		}
 		
 		else if (TestRun.isWeb()) {    			
 			if (TestRun.isIos()) {	
    			capabilities.setCapability("safariAllowPopups", true);
    			capabilities.setCapability("startIWDP", "true");
				capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, TestRun.getWDAPort());		    			
				capabilities.setCapability("webkitDebugProxyPort", TestRun.getWebkitDebugProxyPort());
 			}
 			capabilities.setCapability("browserName", TestRun.getBrowserName());
 		}
	    	
		//Common for Android & iOS
 		capabilities.setCapability("udid", TestRun.getUdid());
	    capabilities.setCapability("platformName", TestRun.getPlatformName());		
	    capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
 		capabilities.setCapability("deviceName", TestRun.getDeviceName());		
	    capabilities.setCapability("automationName", TestRun.getAutomationName());
		
	    return capabilities;
    }
    	                  
	/**********************************************************************************************
	 * To set the Desired Capabilities
	 * 
	 * @version 1.0 March 27, 2018
	 ***********************************************************************************************/	
    public static void initiateDriver() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();	
	
    	if (TestRun.isMobile()) {
	    	switch(TestRun.getEnvironment().toUpperCase())
	    	{
	    		case "LOCAL" :
		    	{
		    		capabilities = localMobile();
			    	break;
		    	}
	   		 	
	   		 	default :
		    		capabilities = localMobile();
	    	}		    	
	    }
		
		DriverManagerUtil.initiateDriver(capabilities);
    }  
}