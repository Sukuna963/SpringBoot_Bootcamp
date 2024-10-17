package com.example.ioc.dp;

public interface GreetingRenderer {
    void render();
    void setGreetingProvider(GreetingProvider greetingProvider);
    GreetingProvider getGreetingProvider();
}