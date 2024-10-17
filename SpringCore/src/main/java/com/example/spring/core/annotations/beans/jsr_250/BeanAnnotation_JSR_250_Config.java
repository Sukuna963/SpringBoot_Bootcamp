package com.example.spring.core.annotations.beans.jsr_250;

import com.example.spring.core.annotations.configuration.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class BeanAnnotation_JSR_250_Config {

    @Bean
    public SampleBean sampleBean() {
        return new SampleBean();
    }
}
