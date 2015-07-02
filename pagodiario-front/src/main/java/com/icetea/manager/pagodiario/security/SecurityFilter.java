package com.icetea.manager.pagodiario.security;

import static com.icetea.manager.pagodiario.security.AuthenticationConstants.COOKIE_TOKEN;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;

import com.icetea.manager.pagodiario.api.dto.UserDto;
import com.icetea.manager.pagodiario.cache.Cache;
import com.icetea.manager.pagodiario.service.AuthenticationService;

@Named
public class SecurityFilter
    extends GenericFilterBean {

	private static final Logger LOGGER = getLogger(SecurityFilter.class);

	private static final String XCLIENT = "X-CLIENT";
	
    private static final String _filterPatterns = "/html/(?!source/upload/add).*,/public/(?!.*(css/|js/|images/)).*$";
    private final AuthenticationService authenticationService;
    private final Cache cache;
	private final Boolean disableSecurity;

    @Inject
	public SecurityFilter(AuthenticationService authenticationService,
			Cache cache,
			@Value("${atp.service.disableSecurity:false}") Boolean disableSecurity) {
		super();
		this.authenticationService = authenticationService;
		this.cache = cache;
		this.disableSecurity = disableSecurity;
	}

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (javax.servlet.http.HttpServletResponse) response;
			String endUrl = SecurityHelper.endUrl(httpServletRequest.getRequestURL().toString(), httpServletRequest.getServletPath());
			
			if (this.disableSecurity) {
				chain.doFilter(request, response);
				return;
			}
			
			if(StringUtils.isEmpty(endUrl)){
				chain.doFilter(request, response);
				return;
			}

			String xclient = httpServletRequest.getHeader(XCLIENT);
			if (!StringUtils.isEmpty(xclient)) {
				SecurityContext.setUsername(xclient);
			}

			String username = null;
			
			if (!StringUtils.endsWith(endUrl, "/html/login") 
					&& !StringUtils.endsWith(endUrl, "/html/logout")
					&& !StringUtils.endsWith(endUrl, "/service/registration")
					&& !StringUtils.endsWith(endUrl, "/service/login")
					&& !StringUtils.endsWith(endUrl, "/html/user/registration")) {
				LOGGER.trace("Filtering URL: " + httpServletRequest.getRequestURL());
				LoggedDto loggedDto = this.getLoggedInUsername(httpServletRequest, httpServletResponse, chain);
	            if(loggedDto != null){
	                username = loggedDto.getUsername();
	                SecurityContext.setUsername(username);
	                SecurityContext.setTimezone(loggedDto.getTimezone());
	            }
				if (username != null) {
					// Continue to application
					request.setAttribute("urlRequest", httpServletRequest.getRequestURL());
					chain.doFilter(request, response);
				} else {
					this.redirectToLogin(httpServletRequest, httpServletResponse);
					return;
				}
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	private void redirectToLogin(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
		LOGGER.trace("Access Tokens Not detected. Redirecting to login");
		response.sendRedirect(httpServletRequest.getContextPath() + httpServletRequest.getServletPath() + "/html/login");
	}

	private LoggedDto getLoggedInUsername(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		UserDto userDto = SecurityHelper.findCookieUserDto(COOKIE_TOKEN, request.getCookies(), this.cache);
		
		if (userDto != null) {
        	// ACCESSTOKEN VALID
            LOGGER.trace("Access Token is valid: " + userDto.getToken());
            this.cache.put(AuthenticationConstants.AUTHENTICATION_EXCHANGE_KEY + userDto.getToken(), userDto);
            
            LoggedDto loggerDto = new LoggedDto();
            loggerDto.setUsername(userDto.getUsername());
            
            UserDto validated = this.authenticationService.search(userDto.getUsername());/*validateAccessToken(userDto.getToken());*/
            if(validated == null){
            	// si no encontre el token en la bd ,, entonces el usuario ya no existe o no es valido su token ...
            	return null;
            }
            
            return loggerDto;
        }

        return null;
	}

	private LoggedDto validateToken(String accessToken, HttpServletRequest request) throws IOException {
    	UserDto userDto = (UserDto) this.cache.get(
				AuthenticationConstants.AUTHENTICATION_EXCHANGE_KEY + accessToken);
    	if(userDto == null){
    		// si no lo encontre en el cache, invoco al servicio de atp ...
    		try {
    			if(StringUtils.isNotBlank(accessToken)){
    				userDto = this.authenticationService.validateAccessToken(accessToken);
    			} 
			} catch (Exception e) {
				// TODO: [roher] ver q hacer con esta exception ...
				throw new RuntimeException(e);
			}
    	}
        if (userDto != null) {
        	// ACCESSTOKEN VALID
            LOGGER.trace("Access Token is valid: " + userDto.getToken());
            request.setAttribute("username", userDto.getUsername());
            request.getSession().setAttribute(AuthenticationConstants.SESSION_USERNAME_KEY, 
            		userDto.getUsername());
            this.cache.put(AuthenticationConstants.AUTHENTICATION_EXCHANGE_KEY + userDto.getToken(), userDto);
            
            LoggedDto loggerDto = new LoggedDto();
            loggerDto.setUsername(userDto.getUsername());
            
            return loggerDto;
        }
        LOGGER.trace("Access Tokens invalid: " + accessToken);

        return null;
    }

}