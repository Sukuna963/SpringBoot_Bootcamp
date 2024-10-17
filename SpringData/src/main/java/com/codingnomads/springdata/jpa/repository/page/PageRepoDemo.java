package com.codingnomads.springdata.jpa.repository.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class PageRepoDemo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PageRepoDemo.class);
    }

    @Autowired
    UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {

        User user = User.builder()
                .firstName("Bobson")
                .lastName("Silva")
                .age(45)
                .build();

        User user1 = User.builder()
                .firstName("Jose")
                .lastName("Rocha")
                .age(15)
                .build();

        User user2 = User.builder()
                .firstName("Maria")
                .lastName("Lima")
                .age(25)
                .build();

        userRepo.save(user);
        userRepo.save(user1);
        userRepo.save(user2);

        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("firstName"));
        Page<User> page = userRepo.findAll(pageRequest);

        System.out.println(page.stream().toList());
    }
}
