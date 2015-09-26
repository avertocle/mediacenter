package mc.utils;

import java.util.ArrayList;

public class Logger 
{
	private static boolean consoleMode = true;
	private static boolean frequentEventLoggingEnabled = false;
	private static boolean broadcastLoggingEnabled = false;
	private static boolean guiEventLoggingEnabled = false;
	private static boolean minimalExceptionLoggingEnabled = true;
	
	
	/***** General Logs ***********************/
	
	public static void log(String line)
	{
		System.out.println("[Log] : " + line);
	}
	
	public static void logMajorEvent(String s)
	{
		String message = "Major Event :: " + s.toUpperCase();
		System.out.println(message);
	}

	/***** Error Logs *************************/
	
	public static void logError(String line)
	{
		System.out.println("[Error] : " + line);
	}
	
	public static void logException(Exception e)
	{
		if (consoleMode)
		{	
			System.out.println("[EXCEPTION]--------------------------------------------------");
			e.printStackTrace();
			System.out.println("[EXCEPTION]--------------------------------------------------");
			return;
		}
	}
	
	public static void logExceptionMinimal(Exception e) 
	{
		if(consoleMode && minimalExceptionLoggingEnabled)
		{	logException(e);	return;	}
		
		if (consoleMode)
		{	System.out.println("[EXCEPTION] : " + e.getMessage());		return;	}
		
		StackTraceElement[] stackTrace = e.getStackTrace();
		for(StackTraceElement x : stackTrace)
		{
			String line = x.getFileName() + ", " +
						  x.getClassName() + ", " + 
						  x.getMethodName() + ", " + 
						  x.getLineNumber() + ", ";
			if (consoleMode)
				System.out.println("[Ex] : " + line);
		}
	}

	/***** Fast Logs **************************/
	
	public static void logFrequentEvent(String line)
	{
		if(frequentEventLoggingEnabled)
			System.out.println("[Log Frequent] : " + line);
	}
	
	public static void logGuiEvent(String line)
	{
		if(guiEventLoggingEnabled)
		System.out.println("[Log Gui Event] : " + line);
	}

	public static void logBroadcast(int param, int value_p1, int value_p2) 
	{
		if(!broadcastLoggingEnabled)
			return;
		
		String message = "Broadcast(" + param + ":" + value_p1 + ":"+ value_p2 + ")";
		System.out.println(message);
	}

	/***** Special Logs ***********************/
	
	public static void logArray(String info , String[] data)
	{
		String line = "";
		
		try
		{
			for(String x : data)
			{
				line += x;
				line += " ";
			}
		}
		catch(Exception e){}
		
		System.out.println("[Log] : " + info + ":: [" + line + "]");	
	}
	
	public static void logArray(String info , int[] data)
	{
		String line = "";
		
		try
		{
			for(int x : data)
			{
				line += x;
				line += " ";
			}
		}
		catch(Exception e){}
		
		System.out.println("[Log] : " + info + ":: [" + line + "]");	
	}
	
	public static void logIntList(String msg, ArrayList<int[]> data) 
	{
		String line = "msg";
		try
		{
			for(int[] x : data)
			{
				line += "{";
				for(int y : x)
				{
					line += String.valueOf(y);
					line += " : ";
				}		
				line += "} | ";
			}
		}
		catch(Exception e){}
		
		System.out.println("[Log] : [" + line + "]");	
		
	}

	public static void logSeperator() 
	{
		System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
		System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
	}

}
