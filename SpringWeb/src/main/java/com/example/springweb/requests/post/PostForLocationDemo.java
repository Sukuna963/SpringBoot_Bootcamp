package com.example.springweb.requests.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@SpringBootApplication
public class PostForLocationDemo {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForLocationDemo.class);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Task newTask = Task.builder()
                    .name("learn how to use postForLocation()")
                    .description("get comfortable using the " +
                            "RestTemplate postForLocation() method")
                    .userId(5)
                    .completed(false)
                    .build();

            // use postForLocation() to get the URL for the new resource
            URI returnedLocation =
                    restTemplate.postForLocation(
                            "http://demo.codingnomads.co:8080/tasks_api/tasks",
                            newTask,
                            ResponseObject.class
                    );

            System.out.println(Objects.requireNonNull(returnedLocation));

            ResponseEntity<?> responseEntity =
                    restTemplate.postForEntity(
                            "http://demo.codingnomads.co:8080/tasks_api/tasks",
                            newTask,
                            ResponseObject.class
                    );

            System.out.println(
                    responseEntity.getHeaders().get("Location"));
        };
    }

}
