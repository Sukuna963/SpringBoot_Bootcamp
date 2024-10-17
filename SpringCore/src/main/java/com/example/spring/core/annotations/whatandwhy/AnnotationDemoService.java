package com.example.spring.core.annotations.whatandwhy;

public class AnnotationDemoService implements LegacyInfoProvider{

    @ModernInfo
    @Override
    public String info() {
        return "legacy api fetching information";
    }
}