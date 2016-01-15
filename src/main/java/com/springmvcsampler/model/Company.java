package com.springmvcsampler.model;


import javax.persistence.*;
import java.util.List;

/**
 * Created by developer on 20/07/2015.
 */
@Entity
@Table(name = "Companies")
public class Company extends BaseEntity<Company> {

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Employee> employees;


    public Company() {
    }

    public Company(String name, List<Employee> employees) {
        this.setName(name);
        this.setEmployees(employees);
    }

//    public void addSelfToEmployees() {
//        employees.stream().forEach(e -> e.setCompany(this));
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
