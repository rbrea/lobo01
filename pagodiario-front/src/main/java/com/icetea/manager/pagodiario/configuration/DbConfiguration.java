package com.icetea.manager.pagodiario.configuration;

import static org.slf4j.LoggerFactory.getLogger;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

//@Configuration
public class DbConfiguration {
	
	private static final Logger LOGGER = getLogger(DbConfiguration.class);

	@Inject @Value("${my.placeholder:conf/env}")
    private String myPlaceholder;
	
	@Bean
	public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer(){
		
		LOGGER.debug("Empezando a configurar propertyPlaceholder ...");
		
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		
		Resource location = new FileSystemResource(this.myPlaceholder + "/environment.properties");
		
		if(location == null || StringUtils.equals(this.myPlaceholder, "conf/env")){
			LOGGER.debug("propertyPlaceholder tomado del classpath ...");
			location = new ClassPathResource(this.myPlaceholder + "/environment.properties");
		} else {
			LOGGER.debug("propertyPlaceholder tomado del fileSystem ...");
		}
		
		propertyPlaceholderConfigurer.setLocation(location);
		
		return propertyPlaceholderConfigurer;
	}
}
