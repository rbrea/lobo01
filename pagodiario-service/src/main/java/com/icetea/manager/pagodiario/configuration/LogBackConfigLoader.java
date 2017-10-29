package com.icetea.manager.pagodiario.configuration;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * Simple Utility class for loading an external config file for logback
 */
public class LogBackConfigLoader {

	private static final Logger LOGGER = getLogger(LogBackConfigLoader.class);
	
	private Resource location;

    public void setLocation(Resource location) {
        this.location = location;
    }
	
	public LogBackConfigLoader(String externalConfigFileLocation) throws IOException, JoranException{
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		
		File externalConfigFile = new File(externalConfigFileLocation);
		if(!externalConfigFile.exists()){
			throw new IOException("Logback External Config File Parameter does not reference a file that exists");
		}else{
			if(!externalConfigFile.isFile()){
				throw new IOException("Logback External Config File Parameter exists, but does not reference a file");
			}else{
				if(!externalConfigFile.canRead()){
					throw new IOException("Logback External Config File exists and is a file, but cannot be read.");
				}else{
					JoranConfigurator configurator = new JoranConfigurator();
					configurator.setContext(lc);
					lc.reset();
					configurator.doConfigure(externalConfigFileLocation);
					
					LOGGER.info("Configured Logback with config file from: " + externalConfigFileLocation);
				}
			}	
		}
		
		LOGGER.info("Configured Logback with config file from: " + this.location.getFilename());
	}
	
}
