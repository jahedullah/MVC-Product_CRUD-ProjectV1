package com.Jahedullah.ProjectV1.configuration.projectConfiguration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ProjectConfigInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class arr[] = {ProjectConfig.class};

        return arr;
    }

    @Override
    protected String[] getServletMappings() {
        String arr[] = {"/"};

        return arr;
    }
}
