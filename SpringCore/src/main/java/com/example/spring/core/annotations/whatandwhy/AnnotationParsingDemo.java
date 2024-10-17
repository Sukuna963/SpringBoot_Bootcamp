package com.example.spring.core.annotations.whatandwhy;

import java.lang.reflect.Method;

public class AnnotationParsingDemo {
    public static void main(String[] args) {
        try {
            Class<AnnotationDemoService> annotationDemoServiceClass = AnnotationDemoService.class;

            for (Method method : annotationDemoServiceClass.getMethods()) {
                if (method.isAnnotationPresent(ModernInfo.class)) {
                    ModernInfo modernInfo = method.getAnnotation(ModernInfo.class);
                    System.out.println("Info received: " + modernInfo.info());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
