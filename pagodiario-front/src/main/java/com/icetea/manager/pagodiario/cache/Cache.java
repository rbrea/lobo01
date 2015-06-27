package com.icetea.manager.pagodiario.cache;

import java.lang.ref.SoftReference;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

@Named
public final class Cache {

	private static final Map<String, SoftReference<ObjectCache>> CACHE = Maps.newHashMap();
	
	private static final int DEFAULT_TTL = 60;
	
	public synchronized Object get(String key){
		SoftReference<ObjectCache> ref = CACHE.get(key);
		if(ref != null){
			ObjectCache objectCache = ref.get();
			if(objectCache != null){
				if(!objectCache.isExpired()){
					return objectCache.getObject();
				}
			}
		}
		
		return null;
	}

	public Object put(String key, Object object){
		
		return this.put(key, object, DEFAULT_TTL);
	}
	
	public synchronized boolean put(String key, Object object, int ttl){
		Preconditions.checkArgument(StringUtils.isNotBlank(key), "key is required");
		Preconditions.checkArgument(object != null, "object is required");
		
		ObjectCache cacheObject = new ObjectCache(ttl, object);
		SoftReference<ObjectCache> ref = new SoftReference<ObjectCache>(cacheObject);
		CACHE.put(key, ref);
		
		return false;
	}

	public synchronized Object remove(String key){
		if(CACHE.containsKey(key)){
			SoftReference<ObjectCache> ref = CACHE.remove(key);
			if(ref != null && ref.get() != null){
				return ref.get().getObject();
			}
		}
		
		return null;
	}
	
}
