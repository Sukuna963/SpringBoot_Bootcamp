package com.example.springaop;

import com.example.springaop.models.Student;
import com.example.springaop.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringAopApplication implements CommandLineRunner {

	private final StudentService studentService;

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		studentService.saveAllStudents(Arrays.asList(
				Student.builder().email("student1@example.com").name("student1").build(),
				Student.builder().email("student2@example.com").name("student2").build(),
				Student.builder().email("student3@example.com").name("student3").build()
		));

		studentService.fetchAllStudents();
	}

}
