package com.icetea.manager.pagodiario.configuration;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.icetea.manager.pagodiario.jetty.JettyThreadPoolExecutor;

@Configuration
public class AppContextConfiguration {

	@Bean(name = "jettyThreadPoolExecutor")
	public ThreadPoolExecutor getJettyThreadPoolExecutor(){
		return JettyThreadPoolExecutor.getThreadPoolExecutor();
	}
	
}
