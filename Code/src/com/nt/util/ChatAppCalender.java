/**
 * @author Devesh Pandey
 * mailID:: dvspandey10@gmail.com
 */

package com.nt.util;

import java.util.Calendar;
import java.util.TimeZone;


public class ChatAppCalender {

	private String date;
	private String time;
	private String timeStamp;
	
	
	//Getters
	public String getDate() {
		TimeZone timeZone = TimeZone.getTimeZone("IST");
		Calendar c = Calendar.getInstance(timeZone); 
		date = c.get(Calendar.DATE)+"/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR);
		return date;
	}

	public String getTime() {
		TimeZone timeZone = TimeZone.getTimeZone("IST");
		Calendar c = Calendar.getInstance(timeZone); 
		time = c.get(Calendar.HOUR_OF_DAY)+ ":" +c.get(Calendar.MINUTE) +":"+c.get(Calendar.SECOND) + "[" + timeZone.getID()+"]";
		return time;
	}

	public String getTimeStamp() {
		TimeZone timeZone = TimeZone.getTimeZone("IST");
		Calendar c = Calendar.getInstance(timeZone); 
		timeStamp = c.get(Calendar.DATE)+"/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR)+ ("("+c.get(Calendar.HOUR_OF_DAY)+ ":" +c.get(Calendar.MINUTE) +":"+c.get(Calendar.SECOND) + "[" + timeZone.getID()+"]")+")";
		return timeStamp;
	}





/*	public static void main(String[] args) throws InterruptedException {
		
		ChatAppCalender cal = new ChatAppCalender();
		System.out.println(cal.getDate());
		Thread.sleep(1000);
		System.out.println(cal.getTime());
		Thread.sleep(2000);
		System.out.println(cal.getTimeStamp());
	}*/

}//class
