package com.kushmyruk.web.infrastructure;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String contextConfig = servletContextEvent.getServletContext().getInitParameter("contextConfig");
        String [] contextConfigs = contextConfig.split(" ");
        AnnotationConfigApplicationContext rootContext = new AnnotationConfigApplicationContext(contextConfigs);
        servletContextEvent.getServletContext().setAttribute("rootContext", rootContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
