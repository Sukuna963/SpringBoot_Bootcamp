package com.codingnomads.springdata.jdbc;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String name;
    private String lastname;

    public Employee(Long id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }
}
