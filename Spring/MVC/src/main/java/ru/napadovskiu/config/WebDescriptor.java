package ru.napadovskiu.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebDescriptor extends AbstractAnnotationConfigDispatcherServletInitializer {


    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringRootConfig.class};
    }


    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }


    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
