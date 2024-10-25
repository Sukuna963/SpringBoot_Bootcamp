package com.example.springwebflux.repositories;

import com.example.springwebflux.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StudentRepository extends ReactiveCrudRepository<Student, Integer> {
}
