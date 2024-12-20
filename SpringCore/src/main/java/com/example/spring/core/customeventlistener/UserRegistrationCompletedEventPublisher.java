package com.example.spring.core.customeventlistener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationCompletedEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishEventAndSendEmail(final String message) {
        System.out.println("----- Publishing UserRegistrationCompletedEvent -----");

        UserRegistrationCompletedEvent customSpringEvent =
                new UserRegistrationCompletedEvent(this, message);

        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
