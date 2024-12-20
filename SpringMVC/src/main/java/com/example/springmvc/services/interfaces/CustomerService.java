package com.example.springmvc.services.interfaces;

import com.example.springmvc.entities.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    Customer getCustomer(Long id);
    void deleteCustomer(Long id);
    List<Customer> saveAllCustomer(List<Customer> customerList);
}
