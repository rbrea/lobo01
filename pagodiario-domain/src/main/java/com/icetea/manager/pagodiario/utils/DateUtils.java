package com.icetea.manager.pagodiario.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public final class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static final String DEFAULT_PATTERN = "dd/MM/yyyy";
	
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
	
	public static String currentDate(){
		return toDate(new Date());
	}
	
	public static int daysBetween(Date startDate, Date endDate){
		if(startDate == null || endDate == null){
			return -1;
		}
		if(DateUtils.truncate(startDate, Calendar.MONTH).after(
				DateUtils.truncate(endDate, Calendar.MONTH))){
			return -1;
		}
		
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		
		start.add(Calendar.DAY_OF_MONTH, (int)diffDays);
		while (start.before(end)) {
		    start.add(Calendar.DAY_OF_MONTH, 1);
		    diffDays++;
		}
		while (start.after(end)) {
		    start.add(Calendar.DAY_OF_MONTH, -1);
		    diffDays--;
		}
		
		return Integer.valueOf(String.valueOf(diffDays));
	}
	
	public static Date lastSecondOfDay(Date date){
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		return calendar.getTime();
	}

	public static Date now(){
		return new Date();
	}
	
}
