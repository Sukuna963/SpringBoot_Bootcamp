package com.example.spring.core.bean;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBeansDemo {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeansDemo.class);
        SpringDeveloper springDeveloper = ctx.getBean(SpringDeveloper.class);

        System.out.println("Spring Developer Address: " +
                springDeveloper.getAddress().getStreetNumber() + " " +
                springDeveloper.getAddress().getStreet());
    }
}
