package com.icetea.manager.pagodiario.security;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;

import com.icetea.manager.pagodiario.api.dto.UserDto;
import com.icetea.manager.pagodiario.cache.Cache;
import com.icetea.manager.pagodiario.utils.DateUtils;

public class SecurityHelper {
    
    private static final Logger LOGGER = getLogger(SecurityHelper.class);
    
    public static UserDto findCookieUserDto(final String key, final Cookie[] cookies, final Cache cache) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && cookie.getName().equals(key) && cookie.getValue() != null) {
                	String cookieValue = cookie.getValue();
                	ObjectMapper mapper = new ObjectMapper();
                	if(StringUtils.isNotBlank(cookieValue) && !StringUtils.equalsIgnoreCase(cookieValue, "false")){
                		UserDto dto = null;
						try {
							dto = mapper.readValue(cookieValue, UserDto.class);
							if(cache != null){
								// actualizo el cache
								cache.put(
										AuthenticationConstants.AUTHENTICATION_ACCESS_KEY + dto.getToken(), dto);
							}
							
							return dto;
						} catch (IOException e) {
							LOGGER.error(
									String.format("Could not deserialize cookie value: %s for key: %s", 
											cookieValue, key), e);
						}
                	}
                }
            }
        }
        
        return null;
    }

	public static String findCookieValue(final String key, final Cookie[] cookies, final Cache cache) {
		UserDto userDto = findCookieUserDto(key, cookies, cache);
		if(userDto != null){
			return userDto.getToken();
		}
		
        return StringUtils.EMPTY;
    }

	public static String endUrl(final String fullUrl, final String base) {
        int idx = StringUtils.indexOf(fullUrl, base);

        return fullUrl.substring(idx + base.length());
    }

    @SuppressWarnings("unused")
	public static boolean matchFilterPatterns(final String url, final String filterPatterns) {
        boolean match = false;
        String[] patterns = filterPatterns.split(",");
        int i = 0;
        while (match == false && i < patterns.length) {
            match = url.matches(patterns[i]);
            String matchMessage = (match) ? "DOES MATCH" : "DOES NOT MATCH";
            i++;
        }
        
        return match;
    }
	
	public static Boolean isCookieExpired(Cookie[] cookies){
    	Boolean result = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && cookie.getName().equals(AuthenticationConstants.COOKIE_MAX_AGE_MINUTES) && cookie.getValue() != null) {
                    try {
                        String expirationDateString = cookie.getValue();
                        if(StringUtils.isNotBlank(expirationDateString)){
                        	Date expirationDate = DateUtils.parseDate(expirationDateString);
                        	Date now = new Date();
                        	result = now.after(expirationDate);
                        	
                        	break;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(String.format("Error when its tried to validate expirationDate for key: %s", 
                        		AuthenticationConstants.COOKIE_MAX_AGE_MINUTES), e);
                    }
                }
            }
        }
        
    	return result;
    }
	
}
