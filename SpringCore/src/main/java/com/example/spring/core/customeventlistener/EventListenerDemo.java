package com.example.spring.core.customeventlistener;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RequiredArgsConstructor
public class EventListenerDemo implements CommandLineRunner {

    private final UserRegistrationCompletedEventPublisher publisher;

    public static void main(String[] args) {
        SpringApplication.run(EventListenerDemo.class, args);
    }

    @Override
    public void run(String... args) {
        publisher.publishEventAndSendEmail("noms@codingnomads.co");
    }
}
