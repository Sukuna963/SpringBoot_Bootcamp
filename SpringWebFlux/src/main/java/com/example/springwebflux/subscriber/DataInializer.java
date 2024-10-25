package com.example.springwebflux.subscriber;

import com.example.springwebflux.repositories.StudentRepository;
import com.example.springwebflux.services.StudentService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataInializer {

    private StudentRepository studentRepository;
    private StudentService studentService;

    public DataInializer(StudentRepository studentRepository,
                         StudentService studentService) {

        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        this.studentRepository
                .deleteAll()
                .thenMany(studentService.saveAll("Evan", "Ji", "Harry", "Ryan", "Rick", "Chuck"))
                .thenMany(studentService.getAllStudent())
                .subscribe(s -> System.out.println(s.getName()));
    }
}
