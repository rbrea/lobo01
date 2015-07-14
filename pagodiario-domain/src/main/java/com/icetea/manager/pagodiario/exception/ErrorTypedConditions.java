package com.icetea.manager.pagodiario.exception;

import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.api.dto.exception.FormErrorDto;

public class ErrorTypedConditions {

	public static boolean checkArgument(boolean condition, String message, ErrorType errorType){
		if(!condition){
			throw new ErrorTypedException(message, errorType);
		}
		
		return true;
	}
	
	public static boolean checkArgument(boolean condition, ErrorType errorType){
		if(!condition){
			throw new ErrorTypedException(errorType);
		}
		
		return true;
	}

	public static boolean checkArgument(boolean condition, FormErrorDto formErrors){
		if(!condition){
			throw new ErrorTypedException(formErrors);
		}
		
		return true;
	}
	
	public static boolean checkArgument(boolean condition, String message, ErrorType errorType, 
			FormErrorDto formErrorDto){
		if(!condition){
			ErrorTypedException e = new ErrorTypedException(message, errorType);
			e.addFormError(formErrorDto);
			
			throw e;
		}
		
		return true;
	}
	
}
