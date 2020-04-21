package com.sogeti.dia.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Class for Date and Time methods
 *
 */
public class DateTimeUtil {
	/**********************************************************************************************
     * Gets the System data
     * 
     * @param format {@link String} - Date format
     * @return sysDate {@link String} - Date in specified format
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/	
	public static String getSystemDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		
		return dateFormat.format(date);
	}

	/**********************************************************************************************
     * Gets the current time stamp
     * 
     * @return timeStamp {@link String} - Timestamp
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static String getCurrentTimestamp() {
		String timeStamp = new Timestamp(System.currentTimeMillis()).toString();
		timeStamp = timeStamp.replaceAll("[ :.]", "_");
		return timeStamp;
	}
	
	/**********************************************************************************************
     * Gets the total execution time
     * 
     * @param startTime {@link String} - Start date
     * @param endTime {@link String} - End date
     * @return totalTime {@link String} - Total time
     * @version 1.0 March 27, 2018
     ***********************************************************************************************/
	public static String getTotalTime(Date startTime, Date endTime)
	{		
		if (endTime == null)	
			endTime = new Date();
		
		long diff = endTime.getTime() - startTime.getTime();				
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffSeconds = diff / 1000 % 60;
		
		return diffHours + ":" + diffMinutes + ":" + diffSeconds;
	}	
}