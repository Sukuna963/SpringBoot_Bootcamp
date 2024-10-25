package com.example.springcache.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    public Employee(String name) {
        this.name = name;
    }
}
