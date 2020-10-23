package com.test.main;


import com.main.business.BusinessClass;
import com.main.business.subbusiness.SubBusinessClass;
import com.main.logger.helper.LogLevelClass;

public class LoggerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//LightLogger.isEnabled.put("com.main.business", true);
		
		LogLevelClass.packageLevel.put("com.main.business", "Debug");
		
		long start= System.nanoTime();
		BusinessClass businessClass = new BusinessClass();
		businessClass.myMethod();
		
		SubBusinessClass subBusinessClass = new SubBusinessClass();
		//infinite loop...
		int counter=0;
		while(counter<100000) {
			subBusinessClass.myMethod();
			subBusinessClass.myMethod();
		counter++;
		
		}
			
		
		//long end= System.nanoTime();
		//System.out.println(end-start + " nanosec");
		
	}

}
