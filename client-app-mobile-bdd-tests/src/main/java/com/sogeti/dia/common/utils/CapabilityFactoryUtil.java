package com.sogeti.dia.common.utils;

import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
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
			//capabilities.setCapability("noReset", false);
			//capabilities.setCapability("fullReset", true);	
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
 			//capabilities.setCapability("autoWebview", "true");
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
     * To set the Desired Capabilities for Browserstack execution
     * 
     * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
	public static DesiredCapabilities browserstackMobile() {
		DesiredCapabilities capabilities = new DesiredCapabilities();	
    	
    	if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {
			capabilities.setCapability("app", TestRun.getAndroidAPK());
			capabilities.setCapability("appPackage", TestRun.getAppPackage());		
	    }
		 
    	else if (TestRun.isNativeHybrid() && TestRun.isIos()) {
    		capabilities.setCapability("app", TestRun.getIOSIPA());	    			   
			capabilities.setCapability("bundleId", TestRun.getBundleId()); 	    				        			   
		}
		
		else if (TestRun.isWeb()) {
			capabilities.setCapability("browserName", TestRun.getBrowserName());
		}
		
		capabilities.setCapability("project", TestRun.getProject());   	    		
		capabilities.setCapability("build", TestRun.getBuild());   
		capabilities.setCapability("name", TestRun.getName());
		capabilities.setCapability("os", TestRun.getPlatformName());
		capabilities.setCapability("os_version", TestRun.getPlatformVersion());	    		 
		capabilities.setCapability("device", TestRun.getDeviceName());
		capabilities.setCapability("real_mobile", "true");
		capabilities.setCapability("browserstack.debug", "true");
		capabilities.setCapability("browserstack.networkLogs", "true");   	    		
		//capabilities.setCapability("browserstack.local", "true");   
		//capabilities.setCapability("browserstack.localIdentifier", "Test123"); 
		
		return capabilities;
    }
    
    /**********************************************************************************************
   	 * To set the Desired Capabilities for Saucelabs execution
   	 * 
   	 * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
   	 * @version 1.0 March 27, 2018
   	 ***********************************************************************************************/	
    public static DesiredCapabilities saucelabsMobile() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();	
       	
    	if (TestRun.isNativeHybrid() && TestRun.isAndroid())
    		capabilities.setCapability("app", TestRun.getAndroidAPK());        						   
    	else if (TestRun.isNativeHybrid() && TestRun.isIos())
	    	capabilities.setCapability("app", TestRun.getIOSAPP());
		else if (TestRun.isWeb())
			capabilities.setCapability("browserName", TestRun.getBrowserName());		
		
		capabilities.setCapability("name", TestRun.getName());
		capabilities.setCapability("appiumVersion", TestRun.getAppiumVersion());
		capabilities.setCapability("platformName", TestRun.getPlatformName()); 
		capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
		capabilities.setCapability("deviceName", TestRun.getDeviceName());
		capabilities.setCapability("deviceOrientation", TestRun.getDeviceOrientation());			    	
		capabilities.setCapability("automationName", TestRun.getAutomationName());
		
		return capabilities;		
     }
       
     /**********************************************************************************************
 	  * To set the Desired Capabilities for Testobject execution
 	  * 
 	  * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
 	  * @version 1.0 March 27, 2018
 	  ***********************************************************************************************/	
     public static DesiredCapabilities testobject() {    	 
    	 DesiredCapabilities capabilities = new DesiredCapabilities();	
    	 
    	 if (TestRun.isWeb())			    	
     		capabilities.setCapability("browserName", TestRun.getBrowserName());
 		
 		capabilities.setCapability("testobject_api_key", TestRun.getAPIKey());  	    		
 		capabilities.setCapability("testobject_suite_name", TestRun.getSuiteName());
 		capabilities.setCapability("testobject_test_name", TestRun.getTestName());
 		capabilities.setCapability("platformName", TestRun.getPlatformName());
 		capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
 		capabilities.setCapability("deviceName", TestRun.getDeviceName());	
 		//capabilities.setCapability("testobject_device", TestRun.getDeviceName());	
		
 		return capabilities;
     }
     
     /**********************************************************************************************
  	  * To set the Desired Capabilities for Kobiton execution
  	  * 
  	  * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
  	  * @version 1.0 March 27, 2018
  	  ***********************************************************************************************/	
     public static DesiredCapabilities kobiton() {
    	 DesiredCapabilities capabilities = new DesiredCapabilities();	
    	 
    	 if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {   			        	    		
    		 capabilities.setCapability("app", TestRun.getAndroidAPK());
    		 capabilities.setCapability("appPackage", TestRun.getAppPackage());
    		 capabilities.setCapability("appActivity", TestRun.getAppActivity());
    	 }
	
    	 else if (TestRun.isNativeHybrid() && TestRun.isIos()) {
			capabilities.setCapability("app", TestRun.getIOSIPA());
			capabilities.setCapability("bundleId", TestRun.getBundleId()); 	    				        			   
    	 }   			
			
  		else if (TestRun.isWeb())
  			capabilities.setCapability("browserName", TestRun.getBrowserName());
  		
  		capabilities.setCapability("sessionName", TestRun.getSessionName());
  		capabilities.setCapability("sessionDescription", TestRun.getSessionDesc());
  		capabilities.setCapability("platformName", TestRun.getPlatformName());
  		capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
  		capabilities.setCapability("deviceOrientation",  TestRun.getDeviceOrientation());
  		capabilities.setCapability("deviceGroup", "KOBITON");
  		capabilities.setCapability("deviceName", TestRun.getDeviceName());
		
  		return capabilities;			    	
     }
      
     /**********************************************************************************************
      * To set the Desired Capabilities for Bitbar execution
      * 
      * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities bitbar() {    
    	  DesiredCapabilities capabilities = new DesiredCapabilities();	
    	  
    	  if (TestRun.isNativeHybrid())
    		  capabilities.setCapability("testdroid_target", TestRun.getPlatformName());    		    	  
    	  
    	  else if (TestRun.isWeb()) {
    		  capabilities.setCapability("testdroid_target", TestRun.getBrowserName());
    		  capabilities.setCapability("browserName", TestRun.getBrowserName());
    	  }
    	  
    	  if (TestRun.isNativeHybrid() && TestRun.isAndroid())    			        	    		
			  capabilities.setCapability("bitbar_app", TestRun.getAndroidAPK());    				    
		  else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	    
			  capabilities.setCapability("bitbar_app", TestRun.getIOSIPA());
			  capabilities.setCapability("bundleId", TestRun.getBundleId());
		  }		
    	  
    	  if (TestRun.isIos()) {  
    		  capabilities.setCapability("automationName", TestRun.getAutomationName());	
    	  }
			
    	  capabilities.setCapability("testdroid_apiKey", Config.TESTDROID_API_KEY);  
    	  capabilities.setCapability("testdroid_project", TestRun.getProject()); 
    	  capabilities.setCapability("testdroid_testrun", TestRun.getTestRun());			
    	  capabilities.setCapability("platformName", TestRun.getPlatformName());
    	  capabilities.setCapability("testdroid_device", TestRun.getDeviceName());    	    						
    	  capabilities.setCapability("deviceName", TestRun.getDeviceName());		
    	  capabilities.setCapability("testdroid_findDevice", "true");
    	  capabilities.setCapability("testdroid_testTimeout", Config.XLARGE_PAUSE);
    	  capabilities.setCapability("newCommandTimeout", Config.XLARGE_PAUSE);

    	  return capabilities;
     }

     /**********************************************************************************************
      * To set the Desired Capabilities for Perfecto execution
      * 
      * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities perfectoMobile() {
    	  DesiredCapabilities capabilities = new DesiredCapabilities();	
    	  
    	  if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {    			        	    		
    		  capabilities.setCapability("app", TestRun.getAndroidAPK());
    		  capabilities.setCapability("appPackage", TestRun.getAppPackage());		
    		  capabilities.setCapability("automationName", "Appium"); 
    	  }
		
    	  else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	    
    		  capabilities.setCapability("app", TestRun.getIOSIPA());	    			   
    		  capabilities.setCapability("bundleId", TestRun.getBundleId()); 	    				        			   
    	  }
			
  		  else if (TestRun.isWeb())
  			  capabilities.setCapability("browserName", TestRun.getBrowserName());
  		
			//Common for Android & iOS
			capabilities.setCapability("securityToken", Config.PERFECTO_SECURITY_TOKEN);
			capabilities.setCapability("platformName", TestRun.getPlatformName());
			//capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());				
			capabilities.setCapability("manufacturer", TestRun.getManufacturer());    		
			//capabilities.setCapability("model", TestRun.getModel());	
			//capabilities.setCapability("deviceName", TestRun.getDeviceName());
			capabilities.setCapability("autoInstrument", true);
			
			return capabilities;	
     }
      
     /**********************************************************************************************
      * To set the Desired Capabilities for Pcloudy execution
      * 
      * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities pcloudy() {	
    	 DesiredCapabilities capabilities = new DesiredCapabilities();	

     	 if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {
     		  capabilities.setCapability("pCloudy_ApplicationName", TestRun.getAndroidAPK());
     		  capabilities.setCapability("appPackage", TestRun.getAppPackage());
     		  capabilities.setCapability("appActivity", TestRun.getAppActivity());  
   		 }
     	  
     	 else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	
     		  capabilities.setCapability("pCloudy_ApplicationName", TestRun.getIOSIPA());	
     		  capabilities.setCapability("bundleId", TestRun.getBundleId());
     		  capabilities.setCapability("usePrebuiltWDA", false);
     		  capabilities.setCapability("acceptAlerts", true);
		 }
		
		 if (TestRun.isWeb())
			  capabilities.setBrowserName(TestRun.getBrowserName());					 
		  
		 if (TestRun.isIos())  		    			
			 capabilities.setCapability("automationName", TestRun.getAutomationName());	
		  
		 capabilities.setCapability("pCloudy_Username", Config.PCLOUDY_USER_NAME);
		 capabilities.setCapability("pCloudy_ApiKey", Config.PCLOUDY_ACCESS_KEY);  			    		  		    		
		 capabilities.setCapability("pCloudy_DeviceManafacturer", TestRun.getManufacturer());
		 //capabilities.setCapability("pCloudy_DeviceFullName", TestRun.getDeviceName());	
		 //capabilities.setCapability("pCloudy_DeviceVersion", TestRun.getPlatformVersion());	
		 capabilities.setCapability("pCloudy_DurationInMinutes", 10);
		 capabilities.setCapability("newCommandTimeout", 600);
		 capabilities.setCapability("launchTimeout", 90000);
		 
		 return capabilities;	
     }
       
    
     /**********************************************************************************************
      * To set the Desired Capabilities for Seetest execution
      * 
      * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities seetestMobile() {	      
    	 DesiredCapabilities capabilities = new DesiredCapabilities();	
        	 
    	 if (TestRun.isAndroid())
    		 capabilities.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
    	 else if (TestRun.isIos())
    		 capabilities.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
	
		 if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {
    		capabilities.setCapability(MobileCapabilityType.APP, TestRun.getAndroidAPK());
			//capabilities.setCapability("appPackage", Config.APP_PCKAGE);
			//capabilities.setCapability("appActivity", Config.APP_ACTIVITY);
		 }
    	   
    	 else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	
			capabilities.setCapability(MobileCapabilityType.APP, TestRun.getIOSIPA());
			capabilities.setCapability("bundleId", TestRun.getBundleId()); 					
    	 }
        	
		 if (TestRun.isWeb() && TestRun.isAndroid())
        	capabilities.setBrowserName(MobileBrowserType.CHROMIUM);
		 else if (TestRun.isWeb() && TestRun.isIos())
        	capabilities.setBrowserName(MobileBrowserType.SAFARI);    			        	 
  
		 capabilities.setCapability("testName", TestRun.getTestName());
		 capabilities.setCapability("accessKey", Config.SEETEST_ACCESS_KEY);
	
		 return capabilities;  
     }
    
     /**********************************************************************************************
      * To set the Desired Capabilities for Microfocus MC execution
      * 
      * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities microfocusMC() {	
    	  DesiredCapabilities capabilities = new DesiredCapabilities();	

    	  if (TestRun.isNativeHybrid() && TestRun.isAndroid()) {	    		
    		  capabilities.setCapability("appPackage", TestRun.getAppPackage());
    		  capabilities.setCapability("appActivity", TestRun.getAppActivity());
    		  //capabilities.setCapability("noReset", false);
    		  //capabilities.setCapability("fullReset", true);	
    	  }
   	  
    	  else if (TestRun.isNativeHybrid() && TestRun.isIos()) {	    	    		
    		  //capabilities.setCapability("showXcodeLog", true);	
    		  //capabilities.setCapability("usePrebuiltWDA", true);
    		  //capabilities.setCapability("noReset", true);
    		  //capabilities.setCapability("useNewWDA", false);
    		  capabilities.setCapability("bundleId", TestRun.getBundleId()); 
    	  }				
		
    	  if (TestRun.isWeb())			
    		capabilities.setCapability("browserName", TestRun.getBrowserName());

		capabilities.setCapability("userName", Config.HPE_MC_USERNAME);  
		capabilities.setCapability("password", Config.HPE_MC_PASSWORD);						
		capabilities.setCapability("deviceName", TestRun.getDeviceName());		
		capabilities.setCapability("platformName", TestRun.getPlatformName());
		capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
		
		return capabilities;    	       
      }
      
   
    /**********************************************************************************************
     * To set the Desired Capabilities for local execution
     * 
     * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
    public static DesiredCapabilities localDesktop() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();	
    	
    	//capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);    	    	
     	//capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
     	if (TestRun.getBrowserName().equalsIgnoreCase("Chrome")) {    	  							   	    	     	 
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);							 
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			options.addArguments("--disable-extensions");
			options.addArguments("start-maximized");
			options.addArguments("--use-fake-ui-for-media-stream=1");
			
			capabilities.setBrowserName("chrome");
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);              
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);	           
		}
         
     	else if (TestRun.getBrowserName().equalsIgnoreCase("IE")) {   
            capabilities.setBrowserName("internet explorer");
         	capabilities.setCapability("requireWindowFocus", false);
         	capabilities.setCapability("javascriptEnabled", true);
         	capabilities.setCapability("ensureCleanSession", true);
           	          	
            capabilities.setCapability("nativeEvents", false);
           	capabilities.setCapability("unexpectedAlertBehaviour", "accept");
           	capabilities.setCapability("ignoreProtectedModeSettings", true);
           	capabilities.setCapability("disable-popup-blocking", true);
           	capabilities.setCapability("enablePersistentHover", true);
           	capabilities.setCapability("ignoreZoomSetting", true);
         	
     	}
     
     	else if (TestRun.getBrowserName().equalsIgnoreCase("Firefox")) {
         	capabilities.setBrowserName("firefox");
         	
         	String path = System.getProperty("user.dir")+"\\src\\test\\resources\\downloads";
         	FirefoxProfile profile = new FirefoxProfile();
         	profile.setAcceptUntrustedCertificates(true);
         	profile.setAssumeUntrustedCertificateIssuer(false);
         	profile.setPreference("browser.download.folderList", 2);
         	profile.setPreference("browser.download.dir", path);
         	profile.setPreference("browser.download.alertOnEXEOpen", false);
         	profile.setPreference("browser.helperApps.neverAsksaveToDisk", "application/pdf," +
         			"application/x-msexcel,application/excel,application/x-excel,application/excel," +
         			"application/x-excel,application/excel,application/vnd.ms-excel," +
         			"application/x-excel,application/x-msexcel, ");
         	profile.setPreference("browser.download.manager.showWhenStarting", false);
         	profile.setPreference("browser.download.manager.focusWhenStarting", false);
         	profile.setPreference("browser.helperApps.alwaysAsk.force", false);
         	profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
         	profile.setPreference("browser.download.manager.closeWhenDone", false);
	        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
	        profile.setPreference("browser.download.manager.useWindow", false);
	        profile.setPreference("browser.download.manager.showWhenStarting", false);
	        profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);      	
	        profile.setPreference("pdfjs.disabled", true);
	        profile.setPreference("plugin.scan.plid.all", false);
	        profile.setPreference("plugin.scan.Acrobat", "99.0");	
	        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
 	   }
		
     	return capabilities;    	     
    }
	
    /**********************************************************************************************
     * To set the Desired Capabilities for Browserstack execution
     * 
     * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
     public static DesiredCapabilities browserstackDesktop() {
    	DesiredCapabilities capabilities = new DesiredCapabilities();
    	
    	capabilities.setCapability("os", TestRun.getPlatformName());
		capabilities.setCapability("os_version", TestRun.getPlatformVersion());
		capabilities.setCapability("browser", TestRun.getBrowserName());
		capabilities.setCapability("browser_version", TestRun.getBrowserVersion());
		capabilities.setCapability("browserstack.local", "false");
		capabilities.setCapability("browserstack.selenium_version", "3.5.2");
		
		return capabilities;
     }
     
     /**********************************************************************************************
      * To set the Desired Capabilities for local execution
      * 
      * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
     public static DesiredCapabilities saucelabsDesktop() {         
    	 DesiredCapabilities capabilities = new DesiredCapabilities();
     	
    	 if (TestRun.getBrowserName().equalsIgnoreCase("Chrome"))   
    		capabilities = DesiredCapabilities.chrome();
		
		 else if (TestRun.getBrowserName().equalsIgnoreCase("Firefox"))  
    		capabilities = DesiredCapabilities.firefox();
			
		 else if (TestRun.getBrowserName().equalsIgnoreCase("IE"))
			capabilities = DesiredCapabilities.internetExplorer();
    			
		capabilities.setCapability("name", TestRun.getName());
		capabilities.setCapability("platform", TestRun.getPlatformName());
       	capabilities.setCapability("version", TestRun.getBrowserVersion());
		
       	return capabilities;
     }
     
     
     /**********************************************************************************************
      * To set the Desired Capabilities for Perfecto execution
      * 
      * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
      * @version 1.0 March 27, 2018
      ***********************************************************************************************/	
	 public static DesiredCapabilities perfectoDesktop() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		 
		capabilities.setCapability("securityToken", Config.PERFECTO_SECURITY_TOKEN);
		capabilities.setCapability("platformName", TestRun.getPlatformName());
		capabilities.setCapability("platformVersion", TestRun.getPlatformVersion());
		capabilities.setCapability("browserName", TestRun.getBrowserName());
		capabilities.setCapability("browserVersion", TestRun.getBrowserVersion());
		capabilities.setCapability("resolution", "1280x1024");
		capabilities.setCapability("location", "US East");
		
		return capabilities;
	 }		
	 
	/**********************************************************************************************
	 * To set the Desired Capabilities for Seetest execution
	 * 
	 * @return capabilities {@link DesiredCapabilities} -DesiredCapabilities object
	 * @version 1.0 March 27, 2018
	 ***********************************************************************************************/	
	public static DesiredCapabilities seetestDesktop() {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("accessKey", Config.SEETEST_ACCESS_KEY);
   		capabilities.setCapability("platformName", TestRun.getPlatformName());
   		
   		if (TestRun.getBrowserName().equalsIgnoreCase("Chrome"))   
   			capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
   		else if (TestRun.getBrowserName().equalsIgnoreCase("Firefox"))   
   			capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
   		else if (TestRun.getBrowserName().equalsIgnoreCase("IE"))   
   			capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IE);
   		else if (TestRun.getBrowserName().equalsIgnoreCase("Safari"))
   			capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
   		else if (TestRun.getBrowserName().equalsIgnoreCase("Edge"))   
   			capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.EDGE);
   		
   		capabilities.setCapability(CapabilityType.BROWSER_VERSION,TestRun.getBrowserVersion());
   		capabilities.setCapability("testName", TestRun.getName());
		
		return capabilities;	  
	}
                	                   
	/**********************************************************************************************
	 * To set the Desired Capabilities
	 * 
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
		    	   			    			    		    
	    		case "BROWSERSTACK" :
	    		{   		    	    	    		    		    					    		
	    			capabilities = browserstackMobile();
		    		break;
		    	} 	
	    		    	
	    		case "SAUCELABS" :
	    		{ 		 
	    			capabilities = saucelabsMobile();			    		
		    		break;
		    	}	
	    	
	    		case "TESTOBJECT" :
	    		{			    				    
	    			capabilities = testobject();
	    			 break;
		    	}
	    		    		    	
	    		case "PERFECTO":
	   		 	{
	   		 		capabilities = perfectoMobile();
					break;
		    	} 	
		    	
	   		 	case "PCLOUDY":
	   		 	{		   		 				    				
	   		 		capabilities = pcloudy();
	   		 		break;
	   		 	}
		        
	   			case "KOBITON":
	    		{	 			    				    	
	    			capabilities = kobiton();
	    			break;
	    		} 
	    			    			   		 	
	   			case "BITBAR":
	    		{							        	    					
	    			capabilities = bitbar();
					break;
		    	}
	    	
	   		 	case "SEETEST":
	   		 	{			    		 
	   		 		capabilities = seetestMobile();
		    		 break;
		    	}
		    	
	   		 	case "MICROFOCUSMC":
	   		 	{
	   		 		capabilities = microfocusMC();		
		    		break;
		    	}	
	   		 	
	   		 	default :
		    		capabilities = localMobile();
	    	}		    	
	    }
		
    	else if (TestRun.isDesktop()) {    	
			switch(TestRun.getEnvironment().toUpperCase())
	    	{
	    	   case "LOCAL" :
	    	   {    	    		   
	    		   	capabilities = localDesktop();
    	     	   	break;
	    		}
	
	    	   	case "BROWSERSTACK" :
	    	   	{
	    	   		capabilities = browserstackDesktop();
	    			break;
	    		}
	    	   	
	    	    case "SAUCELABS" :
	    	    {   
	    	    	capabilities = saucelabsDesktop(); 			    		
		    		break;
	    	    }
	    	    
	    	    case "PERFECTO" :
	    	   	{
	    	   		capabilities = perfectoDesktop();
	    			break;
	    	   	}
 		
	    	   	case "SEETEST" :
	    	   	{
	    	   		capabilities = seetestDesktop();
	    			break;
	    	   	} 
	    	   	
	    	   	default:
	    		   	capabilities = localDesktop();
	    	} 		    	    	    
		}
    		
    	else if (TestRun.isWindows())
		{
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");	    			
			capabilities.setCapability("app", TestRun.getAppPath());
		}
		
		DriverManagerUtil.initiateDriver(capabilities);
    }  
}