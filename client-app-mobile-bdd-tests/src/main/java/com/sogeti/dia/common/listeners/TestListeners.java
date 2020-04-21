package com.sogeti.dia.common.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.sogeti.dia.common.config.TestRun;
import com.sogeti.dia.common.utils.LoggerUtil;
/**
 * Suite listener to customize the testNG result
 * @author savtambe
 *
 */
public class TestListeners implements IRetryAnalyzer, ITestListener {
	int counter = 0;
	int retryLimit = 1;
	
	@Override
    public void onStart(final ITestContext context) {
		//TODO Auto-generated method stub
    }

    @Override
    public void onTestStart(final ITestResult result) {
    	LoggerUtil.logConsoleMessage("========TEST START========");
    	 
    	if (TestRun.isMobile()) 
    		LoggerUtil.logConsoleMessage("Execution started on: " + TestRun.getEnvironment() + "- " + TestRun.getPlatformName() + "- " + TestRun.getDeviceName());
    	
    	else if (TestRun.isDesktop()) {
    		if (TestRun.getPlatformName() == null)
        		LoggerUtil.logConsoleMessage("Execution started on: " + TestRun.getEnvironment() + " on - " + TestRun.getBrowserName());
        	else
        		LoggerUtil.logConsoleMessage("Execution started on: " + TestRun.getEnvironment() + " on - " + TestRun.getPlatformName() + "- " + TestRun.getBrowserName());    	
    	}
    	
    	else if (TestRun.isWindows()) 
			LoggerUtil.logConsoleMessage("Execution started on: " + TestRun.getEnvironment());
    	
    	else if (TestRun.isApi()) 
			LoggerUtil.logConsoleMessage("API Test Execution started");
    }

    @Override
    public void onFinish(final ITestContext context) {        	
    	LoggerUtil.logConsoleMessage("========TEST FINISH========");
    }

    @Override
    public void onTestSuccess(final ITestResult result) {
        teardownTest(result);
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
    	teardownTest(result);
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        teardownTest(result);
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        teardownTest(result);
    }

    private void teardownTest(ITestResult result) {    		
    	String status = result.isSuccess() ? "SUCCESS" : "FAILURE";
        LoggerUtil.logConsoleMessage("======" + status + "======");
        LoggerUtil.logConsoleMessage("Test: " + result.getInstanceName() + "." + result.getName());			
    }

	@Override
	public boolean retry(ITestResult result) {
		if(counter < retryLimit)
		{
			counter++;
			return true;
		}		
		return false;		
	}
}