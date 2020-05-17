package com.anouar.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Web implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext sc) throws ServletException {

        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();

        context.register(Config.class);
        context.setServletContext(sc);

        ServletRegistration.Dynamic appServlet =
                sc.addServlet("mvc", new DispatcherServlet(context));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }
}
