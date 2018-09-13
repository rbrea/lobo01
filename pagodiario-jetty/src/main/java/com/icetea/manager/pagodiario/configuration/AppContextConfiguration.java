package com.icetea.manager.pagodiario.configuration;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.icetea.manager.pagodiario.jetty.JettyThreadPoolExecutor;

@Configuration
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan(basePackages = "com.icetea.manager.pagodiario")
@PropertySource({"classpath:conf/env/environment.properties"})
public class AppContextConfiguration {

	@Bean(name = "jettyThreadPoolExecutor")
	public ThreadPoolExecutor getJettyThreadPoolExecutor(){
		return JettyThreadPoolExecutor.getThreadPoolExecutor();
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource(){
		ReloadableResourceBundleMessageSource instance = new ReloadableResourceBundleMessageSource();
		instance.setBasename("/WEB-INF/i18n/messages");
		instance.setCacheSeconds(60);
		instance.setDefaultEncoding("UTF-8");
		
		return instance;
	}

	/**
	 * @PropertySource annotation does not automatically register a PropertySourcesPlaceholderConfigurer with Spring. 
	 * 		Hence we need to explicitly configure PropertySourcesPlaceholderConfigurer
	 *
	 *	Below JIRA ticket has more information on the rationale behind this design:
	 *           https://jira.spring.io/browse/SPR-8539
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
}
