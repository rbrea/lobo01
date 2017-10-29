package com.icetea.manager.pagodiario.manager;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillTicketPojo;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;

@Named
public class FilePrintManager {

	private final Map<String, SoftReference<List<BillTicketPojo>>> CACHE = Maps.newConcurrentMap();
	
	public final List<BillTicketPojo> put(String key, List<BillTicketPojo> value){
		if(StringUtils.isEmpty(key) || value == null){
			return null;
		}
		
		SoftReference<List<BillTicketPojo>> v = CACHE.put(key, new SoftReference<List<BillTicketPojo>>(value));
		
		return v.get();
	}
	
	public final String putValue(List<BillTicketPojo> value){

		ErrorTypedConditions.checkArgument(value != null);
		
		String key = UUID.randomUUID().toString();
		
		CACHE.put(key, new SoftReference<List<BillTicketPojo>>(value));
		
		return key;
	}
	
	public final List<BillTicketPojo> get(String key){
		SoftReference<List<BillTicketPojo>> v = CACHE.get(key);
		
		return v.get();
	}
	
	public final boolean containsKey(String key){
		return CACHE.containsKey(key);
	}
	
	public final List<BillTicketPojo> remove(String key){
		SoftReference<List<BillTicketPojo>> v = CACHE.remove(key);
		
		return v.get();
	}
	
}
