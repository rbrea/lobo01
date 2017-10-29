package com.icetea.manager.pagodiario.security;

import java.util.TimeZone;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class LoggedDto
    extends BasicDto {

    private static final long serialVersionUID = 1L;

    private String username;
    private String timezone;

    public LoggedDto() {
        super();
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getTimezone() {
        return timezone;
    }
    
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer offset(){
        TimeZone timeZone = TimeZone.getTimeZone(this.timezone);
        
        return timeZone.getRawOffset();
    }
    
}
