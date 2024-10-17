package com.codingnomads.springdata.jpa.repository.sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class SortRepoDemo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SortRepoDemo.class);
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

        Iterable<User> sortedUsersAsc = userRepo.findAll(Sort.by("firstName"));
        Iterable<User> sortedUsersDesc = userRepo.findAll(Sort.by(Sort.Order.desc("firstName")));
        Iterable<User> sortedUsersBothDesc =
                userRepo.findAll(Sort.by(Sort.Direction.DESC, "firstName", "lastName"));
        Iterable<User> sortedUsersListDec = userRepo.findAll(Sort.by(
                List.of(
                        Sort.Order.desc("firstName"),
                        Sort.Order.desc("lastName")
                )
        ));

        sortedUsersAsc.forEach((data) -> {
            System.out.println(data.toString());
        });
        System.out.println("---");
        sortedUsersDesc.forEach((data) -> {
            System.out.println(data.toString());
        });
        System.out.println("---");
        sortedUsersBothDesc.forEach((data) -> {
            System.out.println(data.toString());
        });
        System.out.println("---");
        sortedUsersListDec.forEach((data) -> {
            System.out.println(data.toString());
        });
    }
}
