package com.main.logger.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PropertyResourceBundle;

import com.main.logger.BaseLogger;
import com.main.logger.ConsoleLogger;
import com.main.logger.FileLogger;
import com.main.logger.ILogger;
import com.main.logger.RollerFileLogger;
import com.main.logger.RollerZipperFileLogger;
import com.sun.javafx.css.StyleCache.Key;

///Test this class from a running server

public class LightLogger {
	/**
	 * 
	 * @param obj
	 * @param logMsg
	 * This method will write logs if the global variable logEnabled from LogLevelClass 
	 * is set to TRUE. Also it checks for parent Package name in the excluded list.
	 * Future implementation would be to implement same logic for INFO,DEBUG,ERROR,FATAL level 
	 * LogEnabled Messages and also to add new methods to implement the different type of logging.
	 * Allow Overridden methods to adjust different log files for different type of logging.
	 * Implement thread safety for concurrent use. Use ZIP, auto log roller and formatting in future releases.
	 */
	
	
	static public HashMap<String,Boolean> isEnabled = new HashMap<>();
	static protected FileWriter out;
	static protected BaseWriter baseWriter;
	public static  
	 ILogger getLogger(Class loggerName)
	{
		
		//Changes required to make this file path dynamic. Below implementation is to test POC
		FileInputStream fis;
		File file;
		int threshold;
		try {
			
			fis = new FileInputStream(new File("LightLogger.properties"));
			PropertyResourceBundle prb = new PropertyResourceBundle(fis);
			String packageName=loggerName.getPackage().getName();
			for(String key : prb.keySet()) {
				try {
					if(prb.getObject(packageName).equals("ConsoleLogger"))
						return new ConsoleLogger(loggerName);
					else {
						file = new File((String) prb.getObject(packageName.concat(".file")));
						out=new FileWriter(file,true);
						baseWriter=new BaseWriter(out);
						
						if(prb.getObject(packageName).equals("FileLogger")) {
							
							return new FileLogger(loggerName, file, out, baseWriter);
						}
						else if(prb.getObject(packageName).equals("RollerFileLogger")) {
						
							threshold=Integer.parseInt((String)prb.getObject(packageName.concat(".threshold")));
							
							return new RollerFileLogger(loggerName,file, out, baseWriter, threshold);
						}
						else if(prb.getObject(packageName).equals("RollerZipperFileLogger")) {
							
							threshold=Integer.parseInt((String)prb.getObject(packageName.concat(".threshold")));
							
							return new RollerZipperFileLogger(loggerName,file, out, baseWriter, threshold);
						}
					}
				}
				catch (Exception e) {
					// TODO: handle exception
					/**This is to handle any missing property situation. 
					At least it date will be written in console log and will not be lost*/
					e.printStackTrace();
					return new ConsoleLogger(loggerName);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//for now do nothing
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//for now do nothing
			//e.printStackTrace();
		}
		return null;
	}
}
