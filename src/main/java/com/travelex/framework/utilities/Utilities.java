package com.travelex.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.text.ParseException;

import org.testng.Reporter;


public class Utilities {
	
	public static String strAbsolutepath = new File("").getAbsolutePath();
	public static String strDataPath = strAbsolutepath + "/data/";
	private static String strScriptPath = strAbsolutepath + "/com/travelex/scripts/";
	public static String strResultPath = strAbsolutepath + "/Screenshots/";
    public static String strCrendialsFile = strAbsolutepath + "/build.properties";
    public static String strBuilFile = strAbsolutepath + "/build.properties";

	
//	public int getTotalPagesScanned(){
//		return totalPagesScanned;
//	}
//	
//	public void incrementPagesScanned(){
//		totalPagesScanned++;
//	}
   
	public String getAbsolutePath(){
		return strAbsolutepath;
	}
	
	public String getDataPath(){
		return strDataPath;
	}
	
	public String getScriptPath(){
		return strScriptPath;
	}
	
	public String getResultPath(){
		return strResultPath;
	}
	
	private static Properties prop;
	static String elt=null;
	static Map <String, String> configValues = new HashMap<String, String>();

	public static String getBuildproperties(String key) {
	
		Enumeration<Object> keys=null;
		//Map <String, String> configValues = new HashMap();
		String value=null;
		  if (null == prop)
		  {
			
			prop = new Properties();
			File propFile=null;
								
			 try {
			 propFile = new File("build.properties");
			
			
			 prop.load(new FileInputStream(propFile));
			
		      } catch (IOException e) {
				e.printStackTrace();}

			
			  keys = prop.keys();
			
				System.out.println("Configured properties:");
				//Reporter.log("Configured properties:");
				while (keys.hasMoreElements()) 
				{
					   elt = (String) keys.nextElement();
					   configValues.put(elt,prop.getProperty(elt));
					System.out.println(String.format(
							"\t\tProperty: %s, value: '%s'", elt, prop.getProperty(elt) ));
				

			   }
            }
		    else
				{
					Reporter.log("Key not found in Properties file");
					System.out.println("Key not found in Properties file");              
				}
		 			
		return value;
		
	}
	public static String getConfigValues(String key){
		if(prop==null)
			{getBuildproperties(key);	}
	   return configValues.get(key);
	}

	


	
	public Double stringMultiply(String input1,String input2) {
		Double product = null;
		if(input1.contains(",")){
			input1=input1.replace(",", "");
		}
		if(input2.contains(",")){
			input2=input2.replace(",", "");
		}
		try {
			Double dbl1=Double.parseDouble(input1);
			Double dbl2=Double.parseDouble(input2);
			product=dbl1*dbl2;
		} catch (Exception e) {

			e.printStackTrace();
		}

 		return product;
	}

	

	
	public String getLoginCredentials(String userSet){
		Properties userProp=new Properties();
		String loginCredentials=null;
		try {
			File propFile = new File(strBuilFile);
			userProp.load(new FileInputStream(propFile));
			loginCredentials=userProp.getProperty(userSet);
			userProp.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 	loginCredentials;
	}
	

	
	public static String now() {
		String DATE_FORMAT_NOW = "yyyy-MM-dd-hh.mm.ss";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
	
	public String addDays(String fromDate, String dayDiff) {

		DateFormat dateFormatdisplay = new SimpleDateFormat("MMMM d, yyyy"); 
		Date d = null;
		int days=0;
		try {
			days=Integer.parseInt(dayDiff);
			d = dateFormatdisplay.parse(fromDate);
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		List<String> strDates = Arrays.asList("April 6, 2015",
				"April 27, 2015", "May 4, 2015", "May 5, 2015", "May 14, 2015",
				"May 25, 2015", "December 24, 2015", "December 25, 2015",
				"December 31, 2015");
		List<Date> dates = new ArrayList<Date>();
		for (int i = 0; i < strDates.size(); i++) {
			try {

				dates.add((Date) dateFormatdisplay.parse(strDates.get(i)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, 1);
		d.setTime(c.getTime().getTime());

		dateFormatdisplay.format(d);

		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int bdayCounter = 0;

		while (bdayCounter <= days) {
			if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY
					&& !dates.contains(d)) {
				bdayCounter = bdayCounter + 1;
				if (bdayCounter == days) {
					break;
				}
				c.setTime(d);
				c.add(Calendar.DATE, 1);
				d.setTime(c.getTime().getTime());
				dateFormatdisplay.format(d);
				dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
				// bdayCounter=bdayCounter+1;

			} else {
				c.setTime(d);
				c.add(Calendar.DATE, 1);

				d.setTime(c.getTime().getTime());
				dateFormatdisplay.format(d);
				dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			}

		}
		if(days==0){
	
			}
		String day = "" + dateFormatdisplay.format(d);
		if(days==0){
		day = "" + dateFormatdisplay.format(fromDate);
		}
		return day;
	}
	
}

