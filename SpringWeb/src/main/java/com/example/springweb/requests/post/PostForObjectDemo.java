package com.example.springweb.requests.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PostForObjectDemo {

    @Autowired
    RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PostForObjectDemo.class);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            Task newTask = Task.builder()
                    .name("learn how to use postForObject()")
                    .description("get comfortable using the " +
                            "RestTemplate postForObject() method")
                    .userId(2)
                    .completed(false)
                    .build();

            ResponseObject taskReturnByServerAfterPost =
                    restTemplate.postForObject("http://demo.codingnomads.co:8080/tasks_api/tasks",
                            newTask, ResponseObject.class);

            if (taskReturnByServerAfterPost != null){
                System.out.println(taskReturnByServerAfterPost.toString());
            }
        };
    }
}
