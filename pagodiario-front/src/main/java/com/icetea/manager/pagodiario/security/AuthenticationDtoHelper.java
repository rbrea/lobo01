package com.icetea.manager.pagodiario.security;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.springframework.beans.factory.annotation.Value;

import com.icetea.manager.pagodiario.api.dto.UserDto;

@Named
public class AuthenticationDtoHelper {

    private final boolean userAllowedTestEnabled;
    
    @Inject
	public AuthenticationDtoHelper(@Value("${atp.user.allowed.test.enabled:false}") boolean userAllowedTestEnabled) {
        super();
        this.userAllowedTestEnabled = userAllowedTestEnabled;
    }

	public boolean isAccessAllowed(final UserDto UserDto){
		if(!this.userAllowedTestEnabled){
			return true;
		}
		
		if(!UserDto.getRoles().isEmpty()){
			return CollectionUtils.exists(UserDto.getRoles(), new Predicate<String>() {
				@Override
				public boolean evaluate(String r) {
					
					return true;
				}
			});
		}
		
		return false;
	}
	
}
