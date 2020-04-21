package com.sogeti.dia.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sogeti.dia.common.config.Config;
/**
 * 
 *
 */
public class DBConnectionUtil{	
	private static Connection con;
	private static int index=1;
	
	/**********************************************************************************************
	 * Oracle DB connection
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static void oracleDBConnector() {				
		String dbUrl = "jdbc:oracle:thin:@//" + Config.DB_HOST + ":" + Config.DB_PORT +  "/" + Config.DB_SID;        
		//Database Username		
		String username = Config.DB_USER_ID;	        
		//Database Password		
		String password = Config.DB_PASSWORD;	

		try {
				//Load mysql jdbc driver	
				Class.forName("oracle.jdbc.driver.OracleDriver");  	
				//Create Connection to DB		
		    	con = DriverManager.getConnection(dbUrl, username, password);	  
		  		
		} catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to conenct to Oracle DB: " + e);
		}	
		
		LoggerUtil.logErrorMessage("Conenction to Oracle DB open.");
	}
	
	/**********************************************************************************************
	 * Close Oracle DB connection
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static void closeDB() {
		//closing DB Connection		
		try {
				con.close();
				LoggerUtil.logErrorMessage("Conenction to Oracle DB closed.");
		} catch (Exception e) {
			LoggerUtil.logErrorMessage("Unable to close conenction for Oracle DB: " + e);		
		}	
	}
	

	/**********************************************************************************************
	 * Sample method to get Id from DB, create methods as per the requirement
	 * @param query {@link String} - Query
	 * @return id {@link String} - Id (any value to be returned)
	 * @version 1.0 May 21, 2018
	 ***********************************************************************************************/
	public static synchronized String getID(String query) {
		Statement stmt = null;
		ResultSet resultSet = null;
		String id = null;	
		int counter = 1;	
    	
		try {
				//Create Statement Object		
		    	stmt = con.createStatement();		    	
		    	//Execute the SQL Query. Store results in ResultSet		
		 		resultSet = stmt.executeQuery(query);									 
		 		//While Loop to iterate through data
				while (resultSet.next())
				{
		    		id = resultSet.getString(1);	
		    		if (index==counter)
		    			break;
		    		
		    		counter = counter + 1;
				}	
				
		} catch (Exception e) {
			 LoggerUtil.logErrorMessage("Unable to get the Id from Oracle DB: " + e );
			
		} finally {
			try {
					stmt.close();
					resultSet.close();
			} catch (Exception e) {
				LoggerUtil.logErrorMessage("Unable to close recordset Oracle DB: " + e );
			}
		}
					
		index = index + 1;
		return id;
	}
}