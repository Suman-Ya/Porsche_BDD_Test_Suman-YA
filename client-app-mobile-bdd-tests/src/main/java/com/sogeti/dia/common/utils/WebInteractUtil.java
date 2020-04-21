package com.sogeti.dia.common.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sogeti.dia.common.config.Config;
import com.sogeti.dia.common.config.TestRun;


/**
 * Class having web web application specific reusable methods
 *
 */
public class WebInteractUtil {	    
	/**********************************************************************************************
	 * Waits for web element and clicks on it
	 * 
	 * @param url {@link String} - Url to launch
	 * @version 1.0 May 31, 2018
	 ***********************************************************************************************/
	public static void launchWebApp(String url) {
		if (TestRun.isMobile())
			DriverManagerUtil.getAppiumDriver().navigate().to(url);
		else if (TestRun.isDesktop()) {
			DriverManagerUtil.getWebDriver().navigate().to(url);		
	    	try {
	    			DriverManagerUtil.getWebDriver().manage().window().maximize();
	    	} catch(Exception e) {}
		}
	    	
		 LoggerUtil.logMessage("Launched application: " + url);
    }
	   
	/**********************************************************************************************
	 * Waits for web element and clicks on it
	 * 
	 * @param webElement {@link WebElement} - WebElement to click
	 * @return status {@link boolean} - true/false
	 * @version 1.0 May 31, 2018
	 ***********************************************************************************************/
    public static boolean click(WebElement webElement) {
    	boolean status = false;
    	
    	try {
    			waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);
    			scrollIntoView(webElement);
    			webElement.click();
    			status = true;
    			
		} catch (StaleElementReferenceException e1) {
			for (int i = 0; i <= 10; ++i) {
				try {
						waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);
		    			webElement.click();					
						break;
				} catch (Exception e) {
					continue;
				}	
			}
		} catch (Exception e2) {
			LoggerUtil.logErrorMessage("Unable to click webelement: " + webElement.toString());
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Clicks the identified web element by javascript.
     * 
     * @param webElement {@link WebElement} - WebElement to click
     * @return status {@link boolean} - true/false
     * @version 1.0 May 11, 2018
     ***********************************************************************************************/
     public static boolean clickByJS(WebElement webElement) {
    	 boolean status = false;     
    	 JavascriptExecutor js = null;
    	 
    	 try {
    		 	waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);
    		 
    		 	if (TestRun.isMobile())
    				js = (JavascriptExecutor) DriverManagerUtil.getAppiumDriver();
    		 	else if (TestRun.isDesktop())
    	    		js = (JavascriptExecutor) DriverManagerUtil.getWebDriver();    	    	
    		 	
     			js.executeScript("arguments[0].click();", webElement);
     			status = true;
     			
	     } catch (Exception e) {
				LoggerUtil.logErrorMessage("Unable to clickByJS webelement: " + webElement.toString());
	     }
		 
		 return status;
     }
        
    /**********************************************************************************************
     * Waits for web element and set text in it
     * 
     * @param webElement {@link WebElement} - WebElement to enter text
     * @param text {@link String} - Text to enter
     * @return status {@link boolean} - true/false
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean sendKeys(WebElement webElement, String text) {
    	boolean status = false;
      	
    	try {
    			waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);
				webElement.clear();
				webElement.sendKeys(text);
				status = true;
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to set text in webelement: " + webElement.toString());
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Sends text in element with javascript.
     * 
     * @param webElement {@link WebElement} - WebElement to click
     * @param value {@link String} - Value too be entered
     * @version 1.0 May 11, 2018
     ***********************************************************************************************/
    public static void sendKeysByJS(WebElement webElement, String value) {
    	JavascriptExecutor js = null;
   	
		if (TestRun.isMobile())
			js = (JavascriptExecutor) DriverManagerUtil.getAppiumDriver();
		else if (TestRun.isDesktop())
   		js = (JavascriptExecutor) DriverManagerUtil.getWebDriver();

    	js.executeScript("arguments[0].value='"+ value +"';", webElement);
    }
        
    /**********************************************************************************************
     * Waits for web element and selects value from drop down
     * 
     * @param webElement {@link WebElement} - WebElement to select value
     * @param value {@link String} - Value to select
     * @return status {@link boolean} - true/false
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean selectByValue(WebElement webElement, String value) {
    	boolean status = false;
      	
    	try {
    			waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);	
				Select listBox = new Select(webElement);
				listBox.selectByValue(value);			
				status = true;
				
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to select the value from listbox: " + webElement.toString());
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Waits for web element and selects value from drop down
     * 
     * @param webElement {@link WebElement} - WebElement to select value
     * @param value {@link String} - Value to select
     * @return status {@link boolean} - true/false
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean selectByVisibleText(WebElement webElement, String value) {
    	boolean status = false;
      	
    	try {
    			waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);	
				Select listBox = new Select(webElement);
				listBox.selectByVisibleText(value);			
				status = true;
				
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to select the value from listbox: " + webElement.toString());
		}
		
		return status;
	}
    /**********************************************************************************************
     * Waits for web element and selects value from drop down
     * 
     * @param webElement {@link WebElement} - WebElement to select value
     * @param index {@link int} - Value index to select
     * @return status {@link boolean} - true/false
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean selectByIndex(WebElement webElement, int index) {
    	boolean status = false;
		try {
				waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);		
				Select listBox = new Select(webElement);
				listBox.selectByIndex(index);
				status = true;
				
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to select the value from listbox: " + webElement.toString());
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Waits for web element and clears text in it
     * 
     * @param webElement {@link WebElement} - WebElement to clear text
     * @return status {@link boolean} - true/false
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean clear(WebElement webElement) {
    	boolean status = false;
		try {
				waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);
				webElement.clear();
				status = true;
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to clear text in webelement: " + webElement.toString());
		}
		
		return status;
	}     
    
     
    /**********************************************************************************************
     * Waits for web element visibility
     * 
     * @param webElement {@link WebElement} - WebElement to wait for visibility
     * @param timeOut {@link int} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean waitForElementToBeVisible(WebElement webElement, int timeOut) {
    	boolean status = false;
    	FluentWait<RemoteWebDriver> fluentWait;
    	
    	try {
				
    		if (TestRun.isMobile())
				fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver()); 
			else
				fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver())  
				
				.withTimeout(timeOut, TimeUnit.SECONDS)
		        .pollingEvery(Config.POLLING_TIME, TimeUnit.MILLISECONDS)
		        .ignoring(NoSuchElementException.class);
			
				fluentWait.until(ExpectedConditions.elementToBeClickable(webElement));	
				status = true;
		} catch (Exception e) {
			//LoggerUtil.logErrorMessage("Webelement not visible: " + webElement.toString());
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Waits for web element to be non visible
     * 
     * @param webElement {@link WebElement} - WebElement to wait for non visibility
     * @param timeOut {@link int} - The amount of time in milliseconds to pause
     * @return status {@link boolean} - true/false
     * @version 1.0 May 31, 2018
     ***********************************************************************************************/
    public static boolean waitForInvisibilityOfElement(WebElement webElement, int timeOut) {
    	boolean status = false;
		FluentWait<RemoteWebDriver> fluentWait;
		
    	try {	  	
    			if (TestRun.isMobile())
    				fluentWait = new FluentWait<>(DriverManagerUtil.getAppiumDriver()); 
    			else
    				fluentWait = new FluentWait<>(DriverManagerUtil.getWebDriver())  
    				
					.withTimeout(timeOut, TimeUnit.SECONDS)
    		        .pollingEvery(Config.POLLING_TIME, TimeUnit.MILLISECONDS)
    		        .ignoring(NoSuchElementException.class);
    			
    				fluentWait.until(ExpectedConditions.invisibilityOf(webElement));	
    				status = true;
							
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Webelement is present: " + webElement.toString());
		}
		
		return status;
	}
    
    /**********************************************************************************************
     * Verifies element is present
     * 
     * @param webElement {@link WebElement} - WebElement to wait for visibility
     * @param timeOut {@link Integer} - The amount of time in milliseconds to pause.
     * @return status {@link boolean} - true/false 
     * @version 1.0 May 30, 2018   
     ***********************************************************************************************/    
   	public static boolean isPresent(WebElement webElement, int timeOut) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(webElement, timeOut);   
	   	try {
	   			status = webElement.isDisplayed();
	   	} catch(Exception e) {
			LoggerUtil.logErrorMessage("Webelement is not present: " + webElement.toString());
	   	}
			  
	    return status;
   	} 
   	 
   	/**********************************************************************************************
     * Verifies element is enabled
     * 
     * @param webElement {@link WebElement} - WebElement to verify if enabled
     * @return status {@link boolean} - true/false
     * @version 1.0 May 30, 2018
     ***********************************************************************************************/    
   	public static boolean isEnabled(WebElement webElement) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);   
	   	try {
	   			status = webElement.isEnabled();				 		    
	   	} catch(Exception e) {
			LoggerUtil.logErrorMessage("Webelement is disabled: " + webElement.toString());
	   	}
		  
       return status;
   	} 
    
    /**********************************************************************************************
     * Determines if an element has a specific text value or not.
     * 
     * @param webElement {@link WebElement} - WebElement to verify text 
     * @param text {@link String} - Text to evaluate
     * @return status {@link boolean} - true/false
     * @version 1.0 May 08, 2018
     ***********************************************************************************************/
    public static boolean verifyText(WebElement webElement, String text) {
	   	boolean status = false;
	   	
	   	waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);
	   	try { 	   		
	   			status = webElement.getText().equalsIgnoreCase(text);  
        } catch (Exception e) {        	                
			LoggerUtil.logErrorMessage("Unable to verify text for webelement: " + webElement.toString());
        }
	   	
        return status;
    }
    
    /**********************************************************************************************
     * Fetches elements specific attribute value
     * 
     * @param webElement {@link WebElement} - WebElement to get attribute
     * @param attribute {@link String} - The specific attribute type to fetch value   
     * @return attributeValue {@link String} - The specific attribute value   
     * @version 1.0 May 30, 2018     
     ***********************************************************************************************/
     public static String getAttribute(WebElement webElement, String attribute) {
    	String attributeValue = "";   
    	
    	waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);
 	   	try {
 	   			attributeValue = webElement.getAttribute(attribute);
         } catch (Exception e) {
        	 LoggerUtil.logErrorMessage("Unable to get attribute for webelement: " + webElement.toString());
         }
 	   	
         return attributeValue;
     }
        
     /**********************************************************************************************
      * Determines if an element has a specific attribute value or not.
      * 
      * @param webElement {@link WebElement} - WebElement to verify attribute
      * @param attribute {@link String} - The specific attribute type to evaluate
      * @param attributeValue {@link String} - The value of the attribute to evaluate
      * @return status {@link boolean} - true/false 
      * @version 1.0 May 30, 2018
      ***********************************************************************************************/
     public static boolean verifyAttributeValue(WebElement webElement, String attribute, String attributeValue) {
      	boolean status = false;
      	
      	waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);
   	   	try { 	   		
   	   			status = webElement.getAttribute(attribute).equalsIgnoreCase(attributeValue);  
           } catch (Exception e) {        	                
        	   LoggerUtil.logErrorMessage("Unable to get attribute for webelement: " + webElement.toString());
           }
   	   	
           return status;
      }
       
     /**********************************************************************************************
      * Determines if an element has a specific attribute value or not.
      * 
      * @param webElement {@link WebElement} - WebElement to verify attribute
      * @param attribute {@link String} - The specific attribute type to evaluate
      * @param attributeValue {@link String} - The value of the attribute to evaluate
      * @return status {@link boolean} - true/false
      * @version 1.0 May 30, 2018
      ***********************************************************************************************/
     public static boolean verifyAttributeContains(WebElement webElement, String attribute, String attributeValue) {
    	 boolean status = false;   

    	 waitForElementToBeVisible(webElement, Config.MEDIUM_PAUSE);    	
    	 try {
		  		status = webElement.getAttribute(attribute).toUpperCase().contains(attributeValue.toUpperCase());  	               	   	 	
	   	 } catch (Exception e) {  	   		  
	   		 LoggerUtil.logErrorMessage("Unable to verify attribute contains for webelement: " + webElement.toString());
	   	 }
        return status;
     }
     
	 /**********************************************************************************************
	  * Waits for element to have specific attribute value.
	  * 
	  * @param webElement {@link WebElement} - WebElement to verify attribute
	  * @param attribute {@link String} - The specific attribute type to evaluate
	  * @param attributeValue {@link String} - The value of the attribute to evaluate
	  * @param timeOut {@link int} - The value of the timeout
	  * @return status {@link boolean} - true/false 
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean waitForAttributeContains(WebElement webElement, String attribute, String attributeValue, int timeOut) {
	    boolean status = false;   
	          
	    try {
	          WebDriverWait wait = new WebDriverWait(DriverManagerUtil.getWebDriver(), timeOut);
	          wait.until(ExpectedConditions.attributeContains(webElement, attribute, attributeValue));                  
	          status = true;
              
       } catch (Exception e) {                  
              LoggerUtil.logErrorMessage("Unable to verify attribute contains for webelement: " + webElement.toString());
       }
	    return status;
	} 
	 
	 /**********************************************************************************************
	  * Scroll to element
	  * 
	  * @param webElement {@link WebElement} - WebElement to verify attribute
	  * @return status {@link boolean} - true/false
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean scrollIntoView(WebElement webElement) {
		 boolean status = false;
	    	
		 try {
				if (TestRun.isMobile())
				 	((JavascriptExecutor) DriverManagerUtil.getAppiumDriver()).executeScript("arguments[0].scrollIntoView();", webElement);
				else if (TestRun.isDesktop())
				 	((JavascriptExecutor) DriverManagerUtil.getWebDriver()).executeScript("arguments[0].scrollIntoView();", webElement);
					
				status = true;
				
			 } catch (Exception e) {
				//LoggerUtil.logErrorMessage("Unable to scroll to webelement: " + webElement.toString());
			 }
			
		 return status;
	 }
		    
	 /**********************************************************************************************
	  * Switch to frame
	  * 
	  * @param idNameIndex {@link String} - Id, Name or Index of the frame
	  * @return status {@link boolean} - true/false
	  * @version 1.0 May 30, 2018
	  ***********************************************************************************************/
	 public static boolean switchToFrame(String idNameIndex) {
		 boolean status = false;
		 try {
			 	if (TestRun.isMobile())
			 		DriverManagerUtil.getAppiumDriver().switchTo().frame(idNameIndex);					 		            
				else if (TestRun.isDesktop())
					DriverManagerUtil.getWebDriver().switchTo().frame(idNameIndex);					 		            
			
	             status = true;
		 } catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to switch to frame: " + idNameIndex);
		 }

       return status;
	  }
	 
	 /**********************************************************************************************
	  * Pauses the test action.
	  * 
	  * @param waitTime {@link Integer} - The amount of time in milliseconds to pause.
	  * @version 1.0 March 30, 2018
	  ***********************************************************************************************/
	 public static void pause(Integer waitTime) {
	    try {
	        	Thread.sleep(waitTime);
	    } catch (Exception e) {
	    	//TODO Auto-generated method stub
	    }
	 }	
}