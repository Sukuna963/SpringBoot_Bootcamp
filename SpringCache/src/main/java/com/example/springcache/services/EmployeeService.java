package com.example.springcache.services;

import com.example.springcache.exceptions.ResourceNotFoundException;
import com.example.springcache.models.Employee;
import com.example.springcache.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CacheManager cacheManager;

    @Cacheable(value = "employees", key = "#employeeId", sync = true)
    public Employee getEmployee(Integer employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + employeeId));
    }

    @Cacheable(value = "employees")
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @CachePut(value = "employees", key = "#employee.id")
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = "employee", allEntries = true)
    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
