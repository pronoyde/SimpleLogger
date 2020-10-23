package com.main.business;


import com.main.logger.ILogger;
import com.main.logger.helper.LightLogger;

public class BusinessClass {

	private static final ILogger LOGGER = LightLogger.getLogger(BusinessClass.class);

	public 
	void myMethod() {
		//Call logger
		LOGGER.printInfo("Info mode : From business class....1");
		
		LOGGER.printDebug("Debug mode : From business class....2");
	}
}
