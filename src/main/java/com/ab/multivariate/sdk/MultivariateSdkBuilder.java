package com.ab.multivariate.sdk;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


public class MultivariateSdkBuilder {

    private static final String ENV_PARAM_KEY = "multivariate.sdk.env";

    private static AnnotationConfigApplicationContext context = null;

    public static AnnotationConfigApplicationContext loadApplicationContext() {
        if (context == null) {
            context = refreshApplicationContext();
        }
        return context;
    }

    public static AnnotationConfigApplicationContext refreshApplicationContext() {

        //Read the environment classifier.
        String environment = System.getProperty(ENV_PARAM_KEY);

        context = new AnnotationConfigApplicationContext(MultivariateSdkBuilder.class);
        context.getEnvironment().setActiveProfiles(environment);

        return context;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



}
