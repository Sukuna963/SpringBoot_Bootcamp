package com.example.ioc.dp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoCDemoConfiguration {
    @Bean
    public GreetingProvider provider() {
        return new CodingNomadsGreetingProvider();
    }

    @Bean
    public GreetingRenderer renderer() {
        GreetingRenderer renderer = new StandardOutGreetingRenderer();

        renderer.setGreetingProvider(provider());

        return renderer;
    }
}
