package com.example.springwebflux.controllers;

import com.example.springwebflux.model.Student;
import com.example.springwebflux.services.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Bean
    public RouterFunction<ServerResponse> getAllStudentsRoute() {
        return route().GET("/students-route", req -> ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(studentService.getAllStudent(), Student.class)
        ).build();
    }

    @Bean
    RouterFunction <ServerResponse> updateStudentRoute() {
        return route().PUT("/update-route/{id}", req -> {
            Integer studentId = Integer.parseInt(req.pathVariable("id"));
            Mono<Student> studentMono = req.bodyToMono(Student.class);
            return studentService
                    .updateStudent(studentId, studentMono)
                    .flatMap(student -> ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(Mono.just(student), Student.class))
                    .switchIfEmpty(ServerResponse
                            .notFound()
                            .build());
        }).build();
    }

    public Mono<ServerResponse> getAllStudents(ServerRequest request) {
        Flux<Student> students = studentService.getAllStudent();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(students, Student.class);
    }


    public Mono<ServerResponse> getStudent(ServerRequest request) {
        Integer studentId = Integer.parseInt(request.pathVariable("id"));
        return studentService
                .getStudent(studentId)
                .flatMap(student -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(student), Student.class))
                .switchIfEmpty(ServerResponse
                        .notFound()
                        .build());
    }

    public Mono<ServerResponse> addNewStudent(ServerRequest request) {
        Mono<Student> studentMono = request.bodyToMono(Student.class);
        Mono<Student> newStudent = studentMono.flatMap(studentService::saveStudent);
        return ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newStudent, Student.class);
    }

    public Mono<ServerResponse> updateStudent(ServerRequest request) {
        String studentId = request.pathVariable("id");
        Mono<Student> studentMono = request.bodyToMono(Student.class);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService
                        .updateStudent(Integer.valueOf(studentId), studentMono), Student.class);
    }

    public Mono<ServerResponse> deleteStudent(ServerRequest request) {
        String studentId = request.pathVariable("id");
        return studentService
                .getStudent(Integer.valueOf(studentId))
                .flatMap(student -> studentService
                        .deleteStudent(student)
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse
                        .notFound()
                        .build());
    }
}
