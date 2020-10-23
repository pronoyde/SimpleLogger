package com.main.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.main.logger.helper.BaseWriter;

public class FileLogger extends BaseLogger implements ILogger {

	protected Class clazz;
	protected File file;
	protected FileWriter out;
	protected BaseWriter baseWriter;
	
	
	
	public 
	FileLogger(Class clazz,File file, FileWriter out, BaseWriter baseWriter)  {
		super(clazz);
		this.clazz=clazz;
		this.file=file;
		this.out=out;
		this.baseWriter=baseWriter;
		
	}
	
	@Override
	public 
	synchronized void printError(final String logMsg)
	{
		 int getLogLevel=getHierarchy();
		 try {
			 if(getLogLevel>=ERROR_MODE)
				baseWriter.append("["+date+":"+ERROR+ ":"+clazz.getSimpleName()+"]"+logMsg+"\n");
			 	baseWriter.flush();
		 }
		 catch (IOException e) {
			// TODO: handle exception
		}
	}
	@Override
	public 
	synchronized void printInfo(final String logMsg)
	{
		 int getLogLevel=getHierarchy();
		 try {
			 if(getLogLevel>=INFO_MODE)
				baseWriter.append("["+date+":"+INFO+ ":"+clazz.getSimpleName()+"]"+logMsg+"\n");
			 	
			 	baseWriter.flush();
		 }
		 catch (IOException e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
	}
	@Override
	public 
	synchronized void printDebug(final String logMsg)
	{
		 int getLogLevel=getHierarchy();
		 try {
			if(getLogLevel>=DEBUG_MODE)
				baseWriter.append("["+date+":"+DEBUG+ ":"+clazz.getSimpleName()+"]"+logMsg+"\n");
				baseWriter.flush();
		 }
		 catch (IOException e) {
			// TODO: handle exception
		}
	}
	

}
