package com.example.springweb.requests.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@SpringBootApplication
public class PostForEntityDemo {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForEntityDemo.class);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Task newTask = Task.builder()
                    .name("learn how to use postForEntity()")
                    .description("get comfortable using the " +
                            "RestTemplate postForEntity() method")
                    .userId(5)
                    .completed(false)
                    .build();

            ResponseEntity<?> responseEntity =
                    restTemplate.postForEntity(
                            "http://demo.codingnomads.co:8080/tasks_api/tasks",
                            newTask,
                            ResponseObject.class
                    );

            if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
                // print body
                System.out.println(
                        Objects.requireNonNull(responseEntity.getBody()));
            } else {
                // print error if status was not 201 CREATED
                System.out.println(
                        Objects.requireNonNull(
                                responseEntity.getBody()));
            }
        };
    }

}
