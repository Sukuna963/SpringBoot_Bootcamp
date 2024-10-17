package com.example.spring.core.annotations.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan
public class ConfigurationDemoConfig {

    @Bean
    public SampleClass sampleClass() {
        return new SampleClass();
    }
}
