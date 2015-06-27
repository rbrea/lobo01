package com.icetea.manager.pagodiario.utils;

public class BooleanUtils extends org.apache.commons.lang3.BooleanUtils {

	public static Boolean ynToBoolean(String input){
		return ynToBoolean(input, StringUtils.EMPTY);
	}
	
	public static Boolean ynToBoolean(String input, String errorMessage){
		if(StringUtils.isEmpty(input)){
			return false;
		}
		if(StringUtils.equalsIgnoreCase(StringUtils.trim(input), "y")){
			return true;
		} else if(StringUtils.equalsIgnoreCase(StringUtils.trim(input), "n")){
			return false;
		}
		
		return false;
	}
	
}
