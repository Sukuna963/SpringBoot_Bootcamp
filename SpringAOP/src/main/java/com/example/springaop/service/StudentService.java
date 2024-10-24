package com.example.springaop.service;

import com.example.springaop.aop.Loggable;
import com.example.springaop.aop.TrackMethodExecutionTime;
import com.example.springaop.models.Student;
import com.example.springaop.repositories.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    @TrackMethodExecutionTime
    @Loggable
    public List<Student> fetchAllStudents() {
        return studentRepo.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    @TrackMethodExecutionTime
    @Loggable
    public List<Student> saveAllStudents(List<Student> students) {
        return studentRepo.saveAll(students);
    }
}
