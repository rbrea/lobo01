package com.icetea.manager.pagodiario.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

	public static final int BIG_DECIMAL_PRECISION = 34;
	public static final int BIG_DECIMAL_SCALE = 18;
	public static final String DEFAULT_PATTERN = "%.2f";
	private static final Locale locale = Locale.ENGLISH;
	
	public static final BigDecimal MINUS_1 = new BigDecimal(-1);
	public static final BigDecimal _100 = new BigDecimal(100);

	public static BigDecimal toBigDecimal(String value) {
		if (StringUtils.isBlank(value)) {
			return BigDecimal.ZERO;
		}
		value = StringUtils.trim(value);
		value = StringUtils.convertIfNegative(value);
		int dotPos = -1;
		int commaPos = -1;
		int qDot = 0;
		int qComma = 0;
		for(int i=0;i<value.length();i++){
			char c = value.charAt(i);
			if(c == '.'){
				qDot++;
				dotPos = i;
			}
			if(c == ','){
				qComma++;
				commaPos = i;
			}
		}
		if ((dotPos > commaPos && qDot==1) || (qComma>1)){
			if(qComma==0 && value.split("\\.")[1].length()%3==0){
				return new BigDecimal(StringUtils.replace(value, StringUtils.DOT, StringUtils.EMPTY));
			}
			return new BigDecimal(StringUtils.replace(value, StringUtils.COMMA, StringUtils.EMPTY));
		} else if(dotPos < commaPos){
			if((dotPos != -1)){
				value = StringUtils.replace(value, StringUtils.DOT, StringUtils.EMPTY);
			}
			if(value.split(StringUtils.COMMA)[1].length()>2){
				return new BigDecimal(StringUtils.replace(value, StringUtils.COMMA, StringUtils.EMPTY));
			}
			return new BigDecimal(StringUtils.replace(value, StringUtils.COMMA, StringUtils.DOT));
		}
		else if (qDot>1){
			return new BigDecimal(StringUtils.replace(value, StringUtils.DOT, StringUtils.EMPTY));
		}
		
		return new BigDecimal(value);
	}
	
	public static String toString(BigDecimal value, String pattern) {
		if (value == null) {
			return StringUtils.EMPTY;
		}
		return String.format(locale, pattern, value);
	}
	
	public static String toStringCustomDecimalFormat(BigDecimal value, String pattern) {
		if (value == null) {
			return StringUtils.EMPTY;
		}
		DecimalFormatSymbols custom=new DecimalFormatSymbols();
		custom.setDecimalSeparator('.');
		DecimalFormat format =  new DecimalFormat(pattern);
		format.setDecimalFormatSymbols(custom);
		return format.format(value);
	}

	public static String toString(BigDecimal value) {
		return toString(value, DEFAULT_PATTERN);
	}
	
	public static String toEmptyZero(BigDecimal value) {
		String result =  toString(value, DEFAULT_PATTERN);
		if (result.equals(toString(BigDecimal.ZERO, DEFAULT_PATTERN)))
		{
			result = StringUtils.EMPTY;
		}
		return result;
	}

	public static boolean isNullOrZero(BigDecimal input){
		return input == null || input.compareTo(BigDecimal.ZERO) == 0;
	}
	
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	public static BigDecimal nullToZero(BigDecimal input){
		if(input == null){
			return BigDecimal.ZERO;
		}
		
		return input;
	}
	
	public static BigDecimal add(BigDecimal v1, BigDecimal v2){
		return nullToZero(v1).add(nullToZero(v2), MathContext.DECIMAL128);
	}
	
	public static BigDecimal add(BigDecimal v1, String v2){
		return add(v1, toBigDecimal(v2));
	}
	
	public static BigDecimal add(String v1, String v2){
		return add(toBigDecimal(v1), toBigDecimal(v2));
	}
	
	public static BigDecimal add(String v1, BigDecimal v2){
		return add(toBigDecimal(v1), v2);
	}
	
	public static BigDecimal subtract(BigDecimal v1, BigDecimal v2){
		return nullToZero(v1).subtract(nullToZero(v2), MathContext.DECIMAL32);
	}
	
	public static BigDecimal subtract(BigDecimal v1, String v2){
		return subtract(v1, toBigDecimal(v2));
	}
	
	public static BigDecimal subtract(String v1, String v2){
		return subtract(toBigDecimal(v1), toBigDecimal(v2));
	}
	
	public static BigDecimal subtract(String v1, BigDecimal v2){
		return subtract(toBigDecimal(v1), v2);
	}
	
    public static String extractNumbers(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                s.append(str.charAt(i));
            }
        }
        return s.toString();
    }
    
    public static BigDecimal calculatePercentage(BigDecimal amount, BigDecimal percentage){
    	if(amount == null || percentage == null){
    		return BigDecimal.ZERO;
    	}
    	
    	return amount.multiply(percentage).divide(_100);
    }
    
}
