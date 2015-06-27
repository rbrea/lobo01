package com.icetea.manager.pagodiario.utils;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collection;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public static final String MINUS = "−";
	public static final String SPACE = " ";
	public static final String UNDERSCORE = "_";
	public static final String COMMA = ",";
	
	public static final String DOT = ".";
	public static final String NULL = "null";
	public static final String AT = "@";
	public static final String PLUS = "+";
	public static final String DASH = "-";
	public static final String DASH_SPACED = SPACE + DASH + SPACE;
	public static final String ASTERISK = "*";
	public static final String PERCENTAGE = "%";
	public static final String PIPE = "|";
	public static final String SLASH = "/";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String SEMICOLON = ";";
	public static final String SHARP = "#";
	public static final String BRACKET_LEFT = "(";
	public static final String BRACKET_RIGHT = ")";
	
	public static final String INITIAL_BALANCING_ID = "SALDO       INICIAL";
	public static final String FINAL_BALANCING_ID = "SALDO       FINAL";
	
	public static final String FRENCH_DATE_PATTERN = "dd/MM/yyyy";
	
	public static final String ZERO = "0";
	public static final String ONE = "1";
	
	public static String convertIfNegative(final String value) {
		//StringUtils.defaultString(str, defaultStr)
		String target = value;
		if (StringUtils.endsWith(target, MINUS)) {
			target = DASH + StringUtils.remove(target, MINUS);
		} else if(StringUtils.endsWith(target, DASH)){
			target = DASH + StringUtils.remove(target, DASH);
		} else if(StringUtils.contains(value, BRACKET_LEFT) && StringUtils.contains(value, BRACKET_RIGHT)){
			target = StringUtils.remove(value, BRACKET_LEFT);
			target = StringUtils.remove(target, BRACKET_RIGHT);
			target = DASH + target;
		}
		
		return target;
	}
	
	public static String normalize(String txt) {
		 return Normalizer.normalize(txt, Normalizer.Form.NFD)
				 .replaceAll("\\p{InCombiningDiacriticalMarks}+", EMPTY)
				 .replaceAll(SPACE, PLUS);
	}
	
	public static String emptyWhenNull(String value){
		return value != null ? value : EMPTY;
	}
	
	public static String[] getNames(Class<? extends Enum<?>> e) {
	    return Arrays.toString(e.getEnumConstants()).replaceAll("\\[|]", EMPTY).split(", ");
	}

	public static String valueOf(Integer obj) {
        return (obj == null) ? EMPTY : obj.toString();
	}

	public static String toString(Collection<String> collection){
		if(collection == null){
			return EMPTY;
		}
		StringBuffer buff = new StringBuffer();
		boolean isFirst = true;
		for(String s : collection){
			if(isFirst){
				buff.append(s);
				isFirst = false;
			} else {
				buff.append(COMMA).append(s);
			}
		}
		
		return buff.toString();
	}

	public static String nullIfNullString(String input){
        if (input == null || StringUtils.equalsIgnoreCase(input, StringUtils.NULL)) {
			return null;
		}
		
		return input;
    }

    public static String nullWhenEmpty(String value) {
        return value != null && value.length() > 0 ? value : null;
    }
    
    public static boolean in(String valueToMatch, String... values){
    	if(values == null){
    		return false;
    	}
    	for(String value : values){
    		if(StringUtils.equals(value, valueToMatch)){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public static boolean notIn(String valueToMatch, String... values){
    	if(values == null){
    		return false;
    	}
    	
    	return !in(valueToMatch, values);
    }
    
}
