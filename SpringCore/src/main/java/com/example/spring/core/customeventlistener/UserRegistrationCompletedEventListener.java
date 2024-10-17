package com.example.spring.core.customeventlistener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationCompletedEventListener  implements ApplicationListener<UserRegistrationCompletedEvent> {

    @Override
    public void onApplicationEvent(UserRegistrationCompletedEvent event) {
        System.out.println("----- Received UserRegistrationCompletedEvent -----");
        System.out.println("----- Sending Email To: " + event.getMessage() + " -----");
    }
}
