package com.icetea.manager.pagodiario.listener;

import static org.slf4j.LoggerFactory.getLogger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.icetea.manager.pagodiario.configuration.LogBackConfigLoader;

public class ExternalConfigLoaderContextListener implements ServletContextListener {
	
	private static final Logger LOGGER = getLogger(ExternalConfigLoaderContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String configLocation = sce.getServletContext().getInitParameter("CONFIGDIR");
		if(configLocation == null){
			configLocation = System.getenv("CONFIGDIR");
			if(StringUtils.isBlank("")){
				configLocation = "";
			}
		}
		try{
			new LogBackConfigLoader(configLocation + "logback.xml");
		}catch(Exception e){
			LOGGER.error("Unable to read config file", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {		
	}
	
}
