package com.icetea.manager.pagodiario.jetty;

import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyThreadPoolExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(JettyThreadPoolExecutor.class);

    private static int corePoolSize = 256;
    private static int maximumPoolSize = 256;
    private static long keepAliveTime = 60000;

    static {
        try {
            Properties properties = new Properties();
            properties.load(JettyThreadPoolExecutor.class.getResourceAsStream("/jetty-threadpoolexecutor.properties"));
            corePoolSize = Integer.parseInt((String) properties.get("jetty.corePoolSize"));
            maximumPoolSize = Integer.parseInt((String) properties.get("jetty.maximumPoolSize"));
            keepAliveTime = Long.parseLong((String) properties.get("jetty.keepAliveTime"));
        } catch (Exception e) {
            LOGGER.error("Cannot read or parse jetty-threadpoolexecutor.properties file. Using default values", e);
        }
    }


    private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    private JettyThreadPoolExecutor() {

    }

    public static ThreadPoolExecutor getThreadPoolExecutor() {
        return executor;
    }

}
