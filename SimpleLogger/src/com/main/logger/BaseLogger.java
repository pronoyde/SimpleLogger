package com.main.logger;

import com.main.logger.helper.LogLevelClass;

public class BaseLogger implements ILevel{

private Class clazz;
	
	public BaseLogger(Class clazz) {

		this.clazz=clazz;
		
	}
	public 
	String getLogLevel() {
		//if dedicated log level defined
		String noKey="";
		String classPackName=clazz.getPackage().getName();
		String level= LogLevelClass.packageLevel.getOrDefault(classPackName,noKey);
		if(!level.equals(noKey))
			return level;
		else
			{
			//If the log level is not defined then go to immediate parent and check if the parent package has any log level defined
			String packName=clazz.getPackage().getName();
			String blankString = "";
				while(!packName.equals(blankString))
				{
					 String parentLevel= LogLevelClass.packageLevel.getOrDefault(packName,noKey);
					
					if(!parentLevel.equals(noKey))
						return parentLevel;
					else
					{
						try {
							packName=packName.replace(packName.substring(packName.lastIndexOf('.')),"");
							
						}
						catch(StringIndexOutOfBoundsException se){
							return noKey;
						}
					}
				}
					
			}
		//Default log level
		return "Info";
	}
	
	@Override
	public 
	int getHierarchy() {
		
		String getLogLevel=getLogLevel();
		
		if(getLogLevel=="Error") 
			return ERROR_MODE;
		else if(getLogLevel.equals("Info"))
			return INFO_MODE;
		else if(getLogLevel.equals("Debug"))
			return DEBUG_MODE;
		//print nothing      
		return -1;
	}
}
