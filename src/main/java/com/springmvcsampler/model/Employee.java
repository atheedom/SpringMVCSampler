package com.springmvcsampler.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by developer on 20/07/2015.
 */
@Entity
@Table(name = "Employees")
public class Employee extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String address;

    @ManyToOne
    private Company company;

    @OneToOne
    private PayGrade payGrade;

    public Employee() {

    }

    public Employee(String name, PayGrade payGrade) {
        this.name = name;
        this.payGrade = payGrade;
    }

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public PayGrade getPayGrade() {
        return payGrade;
    }

    public void setPayGrade(PayGrade payGrade) {
        this.payGrade = payGrade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(company, employee.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, company);
    }
}
