package com.example.spring.core.annotations.beans.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(BeanAnnotationConfig.class);
        ctx.refresh();

        SampleBean sampleBean = ctx.getBean("friendly_bean_name", SampleBean.class);

        ctx.close();
    }
}
