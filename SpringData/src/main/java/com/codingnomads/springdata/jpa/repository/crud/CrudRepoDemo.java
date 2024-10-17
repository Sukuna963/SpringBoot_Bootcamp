package com.codingnomads.springdata.jpa.repository.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudRepoDemo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrudRepoDemo.class);
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

        user = userRepo.save(user);

        userRepo.deleteById(user.getId());
    }
}
