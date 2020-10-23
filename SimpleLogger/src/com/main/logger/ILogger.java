package com.main.logger;

import java.util.Date;

public interface ILogger extends ILevel{
	
	
	public Date date = new java.util.Date();
	
	public void printError(String logMsg);
	public void printInfo(String logMsg);
	public void printDebug(String logMsg);
}
