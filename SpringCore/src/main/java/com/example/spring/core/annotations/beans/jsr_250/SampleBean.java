package com.example.spring.core.annotations.beans.jsr_250;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SampleBean {

    public SampleBean() {
        System.out.println("bean is getting ready!");
    }

    @PostConstruct
    public void init() {
        System.out.println("bean @PostConstruct is gathering resource..");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("bean to @PreDestroy and head home..");
    }
}