package com.icetea.manager.pagodiario.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

public class LogbackConfiguration implements InitializingBean {

	private Resource location;

    public void setLocation(Resource location) {
        this.location = location;
    }

    public void afterPropertiesSet() throws Exception {
        JoranConfigurator configurator = new JoranConfigurator();
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        configurator.setContext(loggerContext);
        configurator.doConfigure(this.location.getInputStream());
    }

}
