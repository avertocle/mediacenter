package mc.utils;

import java.util.Date;

public class MiscUtils {
	
	public static String calulateStringHash(String text){
		return text;
	}
	
	public static String getCurrentTimestamp()	{
		return (new Date().toString());
	}
	
	public static boolean isYear(String s){
		try{
			int i = Integer.parseInt(s);
			if(i>1900 && i< 2020){
				return true; 
			}
			return false;
		}
		catch (Exception ex){
			return false;
		}
	}
	
}
