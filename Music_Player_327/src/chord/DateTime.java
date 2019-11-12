/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package chord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author AppleSauce
 */
public class DateTime {
	static String formattedDateTime;
	
	public DateTime() {
		
	}
	
	/**
	 * Retrieves the current date
	 * @return
	 */
	public static String retrieveCurrentDate() {
		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a");
		formattedDateTime = dateFormat.format(currentDate);
		return formattedDateTime;
	}
}