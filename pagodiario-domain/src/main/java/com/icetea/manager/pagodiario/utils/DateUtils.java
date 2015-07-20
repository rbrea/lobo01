package com.icetea.manager.pagodiario.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public final class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static final String DEFAULT_PATTERN = "dd/MM/yyyy HH:mm:ss";
	
	public static String toDate(Date date, String pattern, String timezone){
		if(date == null){
			return null;
		}
		Preconditions.checkArgument(StringUtils.isNotBlank(pattern), "pattern is required");
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		if(StringUtils.isNotBlank(timezone)){
			TimeZone tz = TimeZone.getTimeZone(timezone);
			if(tz != null){
				sdf.setTimeZone(tz);
			}
		}
		
		return sdf.format(date);
	}
	
	public static String toDate(Date date, String pattern){
		return toDate(date, pattern, null);
	}
	
	public static String toDate(Date date){
		return toDate(date, DEFAULT_PATTERN);
	}

	public static Date parseDate(String value, String pattern){
		Preconditions.checkArgument(StringUtils.isNotBlank(value), "value is required");
		
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(value, pattern);
		} catch (ParseException e) {
			throw new RuntimeException("cannot parse to Date value: " + value, e);
		}
	}
	
	public static Date parseDate(String value){
		return parseDate(value, DEFAULT_PATTERN);
	}
	
	public static String convertDateToTimeZone(String value, String timezone){
	    Date d = parseDate(value);
	    
        return toDate(d, DateUtils.DEFAULT_PATTERN, timezone);
	}
}
