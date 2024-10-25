package com.example.springcache;

import com.example.springcache.models.Employee;
import com.example.springcache.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class SpringCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCacheApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(EmployeeRepository employeeRepository) {
		return args -> {
			if (employeeRepository.findAll().isEmpty()) {
				employeeRepository.saveAllAndFlush(List.of(
						new Employee("Cassandry Crumpton"),
						new Employee("Orella Hedge"),
						new Employee("Colby Ambroz"),
						new Employee("Doug Loveguard"),
						new Employee("Iosep O'Clery")));
			}
		};
	}

}
