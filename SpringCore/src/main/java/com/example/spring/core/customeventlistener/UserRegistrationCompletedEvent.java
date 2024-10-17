package com.example.spring.core.customeventlistener;

import org.springframework.context.ApplicationEvent;

public class UserRegistrationCompletedEvent extends ApplicationEvent {

    private final String message;

    public UserRegistrationCompletedEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
