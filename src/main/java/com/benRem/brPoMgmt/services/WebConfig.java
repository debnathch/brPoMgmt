package com.benRem.brPoMgmt.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by debnathchatterjee on 28/04/18.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/","classpath:/static/*", "classpath:/public/" };

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                if (!registry.hasMappingForPattern("/webjars/**")) {
                    registry.addResourceHandler("/webjars/**").addResourceLocations(
                            "classpath:/META-INF/resources/webjars/");
                }
                if (!registry.hasMappingForPattern("/**")) {
                    registry.addResourceHandler("/**").addResourceLocations(
                            CLASSPATH_RESOURCE_LOCATIONS);
                }
            }


           /* @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward requests to /admin and /user to their index.html
                registry.addViewController("/portal").setViewName(
                        "forward:/index.html");
            }*/
        };
    }
}
