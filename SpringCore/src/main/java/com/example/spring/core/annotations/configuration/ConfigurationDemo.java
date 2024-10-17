package com.example.spring.core.annotations.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationDemo {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new AnnotationConfigApplicationContext(ConfigurationDemoConfig.class);
        isBeanPresent("sampleClass", "configurationDemoConfig");
    }

    public static void isBeanPresent(String... beans) {
        for (String beanName : beans) {
            System.out.println("Is " + beanName +
                    "in ApplicationContext: " +
                    applicationContext.containsBean(beanName));
        }
    }
}
