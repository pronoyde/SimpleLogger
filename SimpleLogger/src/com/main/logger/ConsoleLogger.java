package com.main.logger;

import com.main.logger.helper.LogLevelClass;

public class ConsoleLogger extends BaseLogger implements ILogger{
	
	private Class clazz;
	
	public ConsoleLogger(Class clazz) {
		super(clazz);
		this.clazz=clazz;
		
	}
	
	//Each print method should allow DateFormat option to allow flexibility for different Date/Time-stamp format 
	//After all the logger is all about choice
	@Override
	public 
	synchronized void printError(final String logMsg)
	{
		 int getLogLevel=getHierarchy();
		 
		 if(getLogLevel>=ERROR_MODE)
			 System.out.println("["+date+":"+ERROR+ ":"+clazz.getSimpleName()+"]"+logMsg);
	}
	@Override
	public 
	synchronized void printInfo(final String logMsg)
	{
		 int getLogLevel=getHierarchy();
		 
		 if(getLogLevel>=INFO_MODE)
		 		System.out.println("["+date+":"+INFO+ ":"+clazz.getSimpleName()+"]"+logMsg);
	}
	@Override
	public 
	synchronized void printDebug(final String logMsg)
	{
		 int getLogLevel=getHierarchy();
		 //in console String-concat is faster that StringBuffer Append
		 /*StringBuffer sbf = new StringBuffer();
		 
		 sbf.append(date);
		 sbf.append("[");
		 sbf.append(DEBUG);
		 sbf.append("]");
		 sbf.append(clazz.getSimpleName());
		 sbf.append(":");
		 sbf.append(logMsg);
		 */
		 if(getLogLevel>=DEBUG_MODE)
		 		System.out.println("["+date+":"+DEBUG+ ":"+clazz.getSimpleName()+"]"+logMsg);
			 //System.out.println(bfr.toString());
	}
	
	
	
	
}
