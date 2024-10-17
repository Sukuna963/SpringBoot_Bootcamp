package com.example.spring.core.annotations.lazy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LazyAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(LazyAnnotationDemoConfiguration.class);
        ctx.refresh();
        ctx.start();
        ctx.getBean(LazyBean.class);
        ctx.close();
    }
}
