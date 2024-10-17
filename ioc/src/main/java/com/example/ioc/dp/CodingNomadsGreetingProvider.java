package com.example.ioc.dp;

public class CodingNomadsGreetingProvider  implements  GreetingProvider{
    @Override
    public String getGreeting() {
        return "Hello CodingNomads!";
    }
}
