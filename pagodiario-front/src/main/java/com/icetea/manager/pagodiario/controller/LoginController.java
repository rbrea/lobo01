package com.icetea.manager.pagodiario.controller;

import static com.icetea.manager.pagodiario.security.AuthenticationConstants.COOKIE_TOKEN;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.LoginDto;
import com.icetea.manager.pagodiario.api.dto.UserDto;
import com.icetea.manager.pagodiario.api.dto.UserRegistrationDto;
import com.icetea.manager.pagodiario.cache.Cache;
import com.icetea.manager.pagodiario.exception.IncorrectUserLoginException;
import com.icetea.manager.pagodiario.exception.PasswordIncorrectException;
import com.icetea.manager.pagodiario.model.Role;
import com.icetea.manager.pagodiario.security.AuthenticationConstants;
import com.icetea.manager.pagodiario.security.SecurityHelper;
import com.icetea.manager.pagodiario.service.AuthenticationService;

@Controller
public class LoginController extends ExceptionHandlingController {

	private static final Logger LOGGER = getLogger(LoginController.class);
	@Inject
	private AuthenticationService authenticationService;
	@Inject
	private Cache cache;

	@Override
	protected Logger getOwnLogger() {
		return LOGGER;
	}

	@RequestMapping(value = "/html/login", method = RequestMethod.GET)
	public String init(HttpServletResponse response) {
		response.addCookie(new Cookie(COOKIE_TOKEN, null));

		return "login";
	}

	@RequestMapping(value = "/html/login", method = RequestMethod.POST)
	public String doLogin(
			@ModelAttribute LoginDto loginDto,
			ModelMap model,
			HttpServletRequest request,
			HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
	
		UserDto userDto = null;
		String errorMsg = StringUtils.EMPTY;
		try {
			userDto = this.authenticationService.login(loginDto.getUsername(), loginDto.getPassword());

			ObjectMapper mapper = new ObjectMapper();
			Cookie cookie = new Cookie(COOKIE_TOKEN, mapper.writeValueAsString(userDto));
			response.addCookie(cookie);
			
			this.cache.put(
					AuthenticationConstants.AUTHENTICATION_ACCESS_KEY + userDto.getToken(), 
					userDto);
			
			return "redirect:index";
		} catch (IncorrectUserLoginException e) {
			errorMsg = e.getMessage();
		} catch (PasswordIncorrectException e) {
			errorMsg = e.getMessage();
		}
		response.addCookie(new Cookie(COOKIE_TOKEN, null));
		model.addAttribute("errorMsg", errorMsg);

		return "login";
		
	}
	
	@RequestMapping(value = "/html/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		UserDto userDto = SecurityHelper.findCookieUserDto(COOKIE_TOKEN, request.getCookies(), cache);
		
		if(userDto != null){
			this.cache.remove(AuthenticationConstants.AUTHENTICATION_ACCESS_KEY + userDto.getToken());
		}
		
		Cookie cookie = new Cookie(COOKIE_TOKEN, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		response.addCookie(new Cookie(AuthenticationConstants.COOKIE_MAX_AGE_MINUTES, null));
		
		return "redirect:login";
	}

	@RequestMapping(value = "/service/userData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UserDto getUserData(HttpServletRequest request,
			HttpServletResponse response) {

		UserDto userDto = SecurityHelper.findCookieUserDto(COOKIE_TOKEN, request.getCookies(), this.cache);

		if(userDto == null){
			throw new IncorrectUserLoginException("Usuario no logueado");
		}

		return userDto;
	}

	@RequestMapping(value = "/service/registration", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UserDto doRegistration(HttpServletRequest request,
		HttpServletResponse response, RedirectAttributes redirectAttributes, 
		HttpSession session)
				throws JsonParseException, JsonMappingException, IOException {
	
		UserRegistrationDto userRegistration = new UserRegistrationDto();
		userRegistration.setAdmin(true);
		userRegistration.setDocumentNumber("29575093");
		userRegistration.setDocumentType("dni");
		userRegistration.setName("Rodrigo hernandez");
		userRegistration.setPassword("123456");
		userRegistration.setToken(UUID.randomUUID().toString());
		userRegistration.setUsername("roher");
		userRegistration.setRoles(Lists.newArrayList(Role.ROLE_NAME_ADMIN));
		
		return this.authenticationService.register(userRegistration);
	}
	
	@RequestMapping(value = "/html/user", method = RequestMethod.GET)
	public String showUserPage(HttpServletRequest request,
		HttpServletResponse response,
		ModelMap model,		
		@ModelAttribute UserRegistrationDto userRegistration,
		HttpSession session)
				throws JsonParseException, JsonMappingException, IOException {
	
		return "user";
	}
	
	@RequestMapping(value = "/html/user/registration", method = RequestMethod.POST)
	public String saveUser(HttpServletRequest request,
		HttpServletResponse response,
		ModelMap model,		
		@ModelAttribute UserRegistrationDto userRegistration,
		HttpSession session)
				throws JsonParseException, JsonMappingException, IOException {
		String errorMsg = "";
		try {
			// si tiene errores tiene q agregar los mjes al modelmap
			this.authenticationService.register(userRegistration);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			errorMsg = e.getMessage();
		}
		model.addAttribute("errorMsg", errorMsg);
		
		return "login";
	}
	
}
