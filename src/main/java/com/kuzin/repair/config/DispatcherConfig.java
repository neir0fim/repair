package com.kuzin.repair.config;


import com.kuzin.repair.RepairApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class DispatcherConfig
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {RepairApplication.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

}
