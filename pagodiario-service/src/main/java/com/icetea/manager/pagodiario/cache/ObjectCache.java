package com.icetea.manager.pagodiario.cache;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public final class ObjectCache {

	private final Date created;
	private final int timeToLive;
	private final Object object;

	/**
	 * @param created
	 * @param timeToLive in minutes
	 * @param object
	 */
	public ObjectCache(int timeToLive, Object object) {
		super();
		this.created = new Date();
		this.timeToLive = timeToLive;
		this.object = object;
	}

	public int getTimeToLive() {
		return timeToLive;
	}

	public Object getObject() {
		return object;
	}

	public Date getCreated() {
		return created;
	}
	
	public boolean isExpired(){
		Date now = new Date();
		Date expirationDate = DateUtils.addMinutes(this.created, this.timeToLive);
		
		return now.after(expirationDate);
	}
	
}
