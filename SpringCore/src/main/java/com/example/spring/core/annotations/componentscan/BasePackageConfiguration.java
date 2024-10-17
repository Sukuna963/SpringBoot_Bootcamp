package com.example.spring.core.annotations.componentscan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.spring.core.annotations.componentscan.pc")
public class BasePackageConfiguration {

    @Bean
    public SampleBean sampleBean() {
        return new SampleBean();
    }
}
