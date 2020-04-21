package com.sogeti.dia.common.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
/**
 * Suite listener
 *
 */
public class SuiteListeners implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {    
		/*Database connection
		DBConnectionUtil.oracleDBConnector();*/
    }
    
    @Override
    public void onFinish(ISuite suite) {
    	//DBConnectionUtil.closeDB();	
    }
}