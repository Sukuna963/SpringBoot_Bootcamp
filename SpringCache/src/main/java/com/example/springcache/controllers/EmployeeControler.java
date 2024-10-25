package com.example.springcache.controllers;

import com.example.springcache.models.Employee;
import com.example.springcache.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeControler {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {

        employee = employeeService.saveEmployee(employee);

        return ResponseEntity
                .created(URI.create("/employees" + employee.getId()))
                .body(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> findEmployeeId(
            @PathVariable(value = "employeeId") Integer employeeId) {

        return ResponseEntity.ok(employeeService.getEmployee(employeeId));
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable(value = "employeeId") Integer employeeId,
            @RequestBody Employee employeeUpdate) {

        Employee employee = employeeService.getEmployee(employeeId);
        employee.setName(employeeUpdate.getName());

        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Integer employeeId) {
        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok().build();
    }
}
