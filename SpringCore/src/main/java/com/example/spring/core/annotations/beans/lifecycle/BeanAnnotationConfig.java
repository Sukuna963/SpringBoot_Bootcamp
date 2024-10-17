package com.example.spring.core.annotations.beans.lifecycle;

import com.example.spring.core.annotations.configuration.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class BeanAnnotationConfig {

    @Bean(
            initMethod = "init",
            destroyMethod = "cleanup",
            name = "friendly_bean_name"
    )
    public SampleBean sampleBean() {
        return new SampleBean();
    }
}
