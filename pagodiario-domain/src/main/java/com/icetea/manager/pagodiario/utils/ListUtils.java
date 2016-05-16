package com.icetea.manager.pagodiario.utils;

import java.util.List;

public class ListUtils extends org.apache.commons.collections.ListUtils {

	public static boolean isEmpty(List<?> input){
		return input == null || input.isEmpty();
	}
	
	public static boolean isNotEmpty(List<?> input){
		return !isEmpty(input);
	}
	
}
