package com.main.business.subbusiness;

import com.main.logger.ILogger;
import com.main.logger.helper.LightLogger;

public class SubBusinessClass {
	

	private static final ILogger LOGGER = LightLogger.getLogger(SubBusinessClass.class);
	
	public 
	void myMethod() {
		//Call logger
		LOGGER.printInfo("Info mode :From SubBusiness class....1");
		LOGGER.printDebug("Debug mode :From SubBusiness class....2");
	}
	
}
