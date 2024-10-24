package com.example.springbatch.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Employee {

    @Id
    private Integer id;
    private String name;
    private String designation;
    private Date nameUpdateAt;
    private Date designationUpdateAt;

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getNameUpdateAt() {
        return nameUpdateAt;
    }

    public void setNameUpdateAt(Date nameUpdateAt) {
        this.nameUpdateAt = nameUpdateAt;
    }

    public Date getDesignationUpdateAt() {
        return designationUpdateAt;
    }

    public void setDesignationUpdateAt(Date designationUpdateAt) {
        this.designationUpdateAt = designationUpdateAt;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", nameUpdateAt=" + nameUpdateAt +
                ", designationUpdateAt=" + designationUpdateAt +
                '}';
    }
}
