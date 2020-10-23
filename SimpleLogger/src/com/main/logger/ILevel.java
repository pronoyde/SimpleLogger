package com.main.logger;

public interface ILevel {

	public int ERROR_MODE=0;
	public int INFO_MODE=1;
	public int DEBUG_MODE=2;
	
	public String ERROR="ERROR";
	public String INFO="INFO";
	public String DEBUG="DEBUG";
	public int getHierarchy();
	
}
