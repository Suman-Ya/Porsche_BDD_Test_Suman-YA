package com.sogeti.dia.common.utils;

import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.windows.WindowsDriver;
/**
 * Class to setup Driver instances
 *
 */
public class DriverManagerUtil {
	private static ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>();
	private static ThreadLocal<AndroidDriver<MobileElement>> androidDriver = new ThreadLocal<AndroidDriver<MobileElement>>();
	private static ThreadLocal<AppiumDriver<MobileElement>> appiumDriver = new ThreadLocal<AppiumDriver<MobileElement>>();
	private static ThreadLocal<IOSDriver<MobileElement>> iOSDriver = new ThreadLocal<IOSDriver<MobileElement>>();
	private static ThreadLocal<WindowsDriver> windowsDriver = new ThreadLocal<WindowsDriver>();
	private static AppiumDriverLocalService service;
	
	 
	 /**********************************************************************************************
     * Sets the Remote WebDriver instance for the running session.
     * 
     * @param driver {@link RemoteWebDriver} - The instance of the driver
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static void setWebDriver(RemoteWebDriver driver) {
        webDriver.set(driver);
    }
    
    /**********************************************************************************************
     * Gets the Remote WebDriver instance for the running session.
     *      
     * @return driver {@link RemoteWebDriver} - The instance of the driver
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static RemoteWebDriver getWebDriver() {
        return webDriver.get();
    }
    
    /**********************************************************************************************
     * Sets the Appium Driver instance for the running session.
     * 
     * @param driver {@link AppiumDriver} - The instance of Appium driver
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static void setAppiumDriver(AppiumDriver<MobileElement> driver) {
        appiumDriver.set(driver);
    }
    
    /**********************************************************************************************
     * Gets the Appium Driver instance for the running session.
     * 
     * @return driver {@link AppiumDriver} - The instance of Appium driver

     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static AppiumDriver<MobileElement> getAppiumDriver() {
        return appiumDriver.get();
    }
    
    /**********************************************************************************************
     * Sets the Android Driver instance for the running session.
     * 
     * @param driver {@link AndroidDriver} - The instance of Android driver
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static void setAndroidDriver(AndroidDriver<MobileElement> driver) {
        androidDriver.set(driver);
    }
    
    /**********************************************************************************************
     * Gets the Android Driver instance for the running session.
     * 
     * @return driver {@link AndroidDriver} - The instance of Android driver
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static AndroidDriver<MobileElement> getAndroidDriver() {
        return androidDriver.get();
    }
    
    /**********************************************************************************************
     * Sets the IOS Driver instance for the running session.
     * 
     * @param driver {@link IOSDriver} - The instance of IOS driver
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static void setIOSDriver(IOSDriver<MobileElement> driver) {
        iOSDriver.set(driver);
    }
    
    /**********************************************************************************************
     * Gets the IOS Driver instance for the running session.
     * 
     * @return driver {@link IOSDriver} - The instance of IOS driver
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
    public static IOSDriver<MobileElement> getIOSDriver() {
        return iOSDriver.get();
    }
    
    
	 /**********************************************************************************************
    * Sets the WindowsDriver instance for the running session.
    * 
    * @param driver {@link WindowsDriver} - The instance of the driver
    * @version 1.0 March 27, 2018
    ***********************************************************************************************/
   public static void setWindowsDriver(WindowsDriver driver) {
	   windowsDriver.set(driver);
   }
   
   /**********************************************************************************************
    * Gets the WindowsDriver instance for the running session.
    *      
    * @return driver {@link WindowsDriver} - The instance of the driver
    * @version 1.0 March 27, 2018
    ***********************************************************************************************/
   public static RemoteWebDriver getWindowsDriver() {
       return windowsDriver.get();
   }

    /**********************************************************************************************
	 * Sets the Driver instance for the running session.
	 * 
	 * @param capabilities {@link DesiredCapabilities} - The desired capabilities
	 * @version 1.0 March 27, 2018
	 ***********************************************************************************************/	
	public static void initiateDriver(DesiredCapabilities capabilities) { 		
		String url = null;
		
		switch(TestRun.getEnvironment().toUpperCase())
		{
		   case "LOCAL" :
		   {
				   if (TestRun.isMobile() || TestRun.isWindows())			
					   url = service.getUrl().toString(); 		
			
				   else if (TestRun.isDesktop())			
					   url = Config.GRID_URL;
				   				 
				   break;
		   }   
		}
		
		//Initiate the driver
        if (TestRun.isMobile() && TestRun.isAndroid()) {
        	androidDriver(url, capabilities);	               
        } 
        
        else if (TestRun.isMobile() && TestRun.isIos()) {
        	iOSDriver(url, capabilities);
        }
        
        else if (TestRun.isDesktop()) {
        	webDriver(url, capabilities);
        }
        
        else if (TestRun.isWindows()) {
        	windowsDriver(url, capabilities);
        }
	}
	
	/**********************************************************************************************
     * Set Android Driver instance for the running session.
     * 
     * @param url {@link String} - The url
     * @param capabilities {@link DesiredCapabilities} - The desired capabilities
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void androidDriver(String url, DesiredCapabilities capabilities) {			
		try {
				 appiumDriver.set(new AndroidDriver<MobileElement>(new URL(url), capabilities));
	             setAppiumDriver(appiumDriver.get());
	             androidDriver.set((AndroidDriver<MobileElement>) appiumDriver.get());
	             setAndroidDriver(androidDriver.get());
		 } catch (Exception e) {
			 LoggerUtil.logConsoleMessage("Android Session could not be created: " + e);
         }
		
		 if (appiumDriver == null) {
			 LoggerUtil.logConsoleMessage("The driver was not properly initiated.");
		 } 		 		
    }
	
	/**********************************************************************************************
     * Set iOS Driver instance for the running session.
     * 
     * @param url {@link String} - The url
     * @param capabilities {@link DesiredCapabilities} - The desired capabilities
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void iOSDriver(String url, DesiredCapabilities capabilities) {
		try {
				appiumDriver.set(new IOSDriver<MobileElement>(new URL(url), capabilities));
				setAppiumDriver(appiumDriver.get());
				iOSDriver.set((IOSDriver<MobileElement>) appiumDriver.get());
				setIOSDriver(iOSDriver.get());
		 } catch (Exception e) {
	            LoggerUtil.logConsoleMessage("iOS Session could not be created: " + e);
         }
		
		 if (appiumDriver == null) {
			 LoggerUtil.logConsoleMessage("The driver was not properly initiated.");
		 } 
    }


	/**********************************************************************************************
     * Set Web Driver instance for the running session.
     * 
     * @param url {@link String} - The url
     * @param capabilities {@link DesiredCapabilities} - The desired capabilities
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void webDriver(String url, DesiredCapabilities capabilities) {
		 try {
			 	setWebDriver(new RemoteWebDriver(new URL(url), capabilities));
	     } catch (Exception e) {
	         LoggerUtil.logConsoleMessage("Session could not be created: " + e);
	     }
	     
	     if (webDriver == null) {
	         LoggerUtil.logConsoleMessage("The driver was not properly initiated.");
	     }   
    }
	
	/**********************************************************************************************
     * Set Windows Driver instance for the running session.
     * 
     * @param url {@link String} - The url
     * @param capabilities {@link DesiredCapabilities} - The desired capabilities
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void windowsDriver(String url, DesiredCapabilities capabilities) {
		 try {
	     		setWindowsDriver(new WindowsDriver(new URL(url), capabilities));
	     } catch (Exception e) {
	         LoggerUtil.logConsoleMessage("Session could not be created: " + e);
	     }
	     
	     if (windowsDriver == null) {
	         LoggerUtil.logConsoleMessage("The driver was not properly initiated.");
	     }   
    }
	
	/**********************************************************************************************
     * Stops Appium Driver instance for the running session.
     * 
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void stopAppiumDriver() {    	
		if (TestRun.isMobile()) {
			try {
					if (TestRun.isNativeHybrid()) 
						getAppiumDriver().closeApp();
					try {					
							getAppiumDriver().close();
					} catch (Exception e) {}	
					
					getAppiumDriver().quit();	
			} catch (Exception e) {
				//TODO Auto-generated catch block
			}
		}
	}		

	/**********************************************************************************************
     * Stops Web Driver instance for the running session.
     * 
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void stopWebDriver() {
		if (TestRun.isDesktop()) {
			try {
				 	getWebDriver().close();
			} catch (Exception e) {}
				 
			try {
					getWebDriver().quit();
			} catch (Exception e) {}
				
		 LoggerUtil.logConsoleMessage("Closing browser.");
		}		
	}
	
	/**********************************************************************************************
     * Stops Windows Driver instance for the running session.
     * 
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void stopWindowsDriver() {
		if (TestRun.isWindows()) {
			try {
				 	getWindowsDriver().close();
			} catch (Exception e) {}
				 
			try {
					getWindowsDriver().quit();
			} catch (Exception e) {}
				
		 LoggerUtil.logConsoleMessage("Closing windows application.");
		}		
	}

	/**********************************************************************************************
	* Starts Appium server
	* 
	* @version 1.0 July 24, 2018
	***********************************************************************************************/
	public static void startAppiumServer() { 
		if (TestRun.isMobile() || TestRun.isWindows()) {
			if (TestRun.isLocal()) {		
				//Build the Appium service		
				AppiumServiceBuilder builder;
				builder = new AppiumServiceBuilder();
				builder.withIPAddress(TestRun.getIPAaddress());
				builder.usingAnyFreePort();	
				//builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
				builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
				
				//Start the server with the builder
				service = AppiumDriverLocalService.buildService(builder);
				service.start();
				if(service==null || !service.isRunning()) {
					throw new AppiumServerHasNotBeenStartedLocallyException("An Appium server node is not installed");
				}	
			}
		}	
	}
	
	/**********************************************************************************************
	* Stops Appium server
	* 
	* @version 1.0 July 24, 2018
	***********************************************************************************************/
	public static void stopAppiumServer() {  
		try {
				if (service != null)	
					service.stop();
		} catch (Exception e) {}
	}
	
	/**********************************************************************************************
     * Starts Selenium Grid
     * 
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void startSeleniumGrid() {
		try { 
				Runtime.getRuntime().exec("cmd /c start " + Config.SELENIUM_GRID_FILE_PATH); 
	 	} 
		catch (Exception e) {
			LoggerUtil.logErrorMessage("Could not start Selenium grid: " + e);				 
		}
	}
	
	/**********************************************************************************************
     * Stops Selenium Grid
     * 
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static void stopSeleniumGrid() {
		try { 
				Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
	 	} 
		catch (Exception e) {
			LoggerUtil.logErrorMessage("Could not start Selenium grid: " + e);				 
		}
	}
	
}