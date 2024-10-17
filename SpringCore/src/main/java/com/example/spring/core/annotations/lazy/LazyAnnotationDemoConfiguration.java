package com.example.spring.core.annotations.lazy;

import com.example.spring.core.annotations.configuration.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
@ComponentScan(basePackages = "com.example.spring.core.annotations.lazy")
public class LazyAnnotationDemoConfiguration {

    @Bean
    public LazyBean lazyBean() {
        return new LazyBean();
    }
}
