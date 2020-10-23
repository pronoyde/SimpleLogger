package com.main.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import com.main.logger.helper.BaseWriter;

public class RollerFileLogger extends FileLogger implements ILogger {

	int size=0;
	//in bytes (Set max file size 10MB:default)
	int maxSize=0;
	String destFileName;
	
	public RollerFileLogger(Class clazz, File file,  FileWriter out, BaseWriter baseWriter, int maxSize) {
		super(clazz, file, out, baseWriter);
		this.maxSize=maxSize;
		destFileName="";
		

	}

	@Override
	public 
	synchronized void printError(final String logMsg)
	{
		 int getLogLevel=getHierarchy();
		 try {
			 String toBeWritten =  "["+date+":"+INFO+ ":"+clazz.getSimpleName()+"]"+logMsg+"\n";
			 
			 if(file.length()>maxSize)
				 renameFile();		
			 
			 if(getLogLevel>=ERROR_MODE)
				baseWriter.write(toBeWritten);
			 	
			 	baseWriter.flush();
			 	size+=toBeWritten.length();
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
			 
			 String toBeWritten =  "["+date+":"+INFO+ ":"+clazz.getSimpleName()+"]"+logMsg+"\n";
			 
			 if(file.length()>maxSize)
				renameFile();
			
			 if(getLogLevel>=INFO_MODE)
				baseWriter.write(toBeWritten);
			 	
			 	baseWriter.flush();
			 	size+=toBeWritten.length();
			 	
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
			
			String toBeWritten =  "["+date+":"+INFO+ ":"+clazz.getSimpleName()+"]"+logMsg+"\n";
			
			if(file.length()>maxSize)
				renameFile();
			 
			if(getLogLevel>=DEBUG_MODE)
				baseWriter.write(toBeWritten);
			
				baseWriter.flush();
				size+=toBeWritten.length();
		 }
		 catch (IOException e) {
			// TODO: handle exception
		}
	}
	protected
	void renameFile() {
		
		 try {
			 
			 String oldFile=file.getAbsolutePath();
			 //renamedFile= new File(file.getAbsolutePath()+"-"+date.toString().replace(' ', '-'));
			 
			 Path source = Paths.get(oldFile);

			 try{

				//workaround to keep fileName unique
				 Date date=new Date();
			   // rename a file in the same directory use nio classes instead of legacy renameTo()
				   String destFileName=file.getAbsolutePath()+"-"+date.toString().replace(' ', '-')+Math.random();
				   Files.move(source, source.resolveSibling(destFileName));
				   file.delete();

			 } catch (IOException e) {
			   e.printStackTrace();
			 }
			 baseWriter.close();
			 out.close();
			 file=new File(oldFile);
			 out = new FileWriter(file,true);
			 baseWriter= new BaseWriter(out);
			 
			 
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
