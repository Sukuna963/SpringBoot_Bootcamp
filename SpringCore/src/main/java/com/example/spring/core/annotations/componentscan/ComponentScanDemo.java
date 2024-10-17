package com.example.spring.core.annotations.componentscan;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentScanDemo {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = new AnnotationConfigApplicationContext(ComponentScanConfiguration.class);

        isBeanPresent("framework", "JDK", "motherboard", "OS",
                "sampleBean","componentScanConfiguration");

        System.out.println("----");

        applicationContext = new AnnotationConfigApplicationContext(BasePackageConfiguration.class);

        isBeanPresent("framework", "JDK", "motherboard", "OS",
                "sampleBean","basePackageConfiguration");
    }

    public static void isBeanPresent(String... beans) {
        for (String beanName : beans) {
            System.out.println("Is " + beanName +
                    " in ApplicationContext " +
                    applicationContext.containsBean(beanName));
        }
    }
}
