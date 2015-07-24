package com.springmvcsampler.service;

import com.springmvcsampler.model.Company;
import com.springmvcsampler.repository.CompanyRepository;
import com.springmvcsampler.repository.PayGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service("Service that manages companies")
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PayGradeRepository payGradeRepository;

    @PostConstruct
    public void initialize() {

//        payGradeRepository.saveAndFlush(new PayGrade("A", new BigDecimal(10000)));
//        payGradeRepository.saveAndFlush(new PayGrade("B", new BigDecimal(8000)));
//        payGradeRepository.saveAndFlush(new PayGrade("C", new BigDecimal(5000)));
//        payGradeRepository.saveAndFlush(new PayGrade("D", new BigDecimal(1000)));

//        Employee e1 = new Employee("John", payGradeRepository.findByGrade("A").get());
//        Employee e2 = new Employee("David", payGradeRepository.findByGrade("A").get());
//        Employee e3 = new Employee("Sarah", payGradeRepository.findByGrade("B").get());
//        Employee e4 = new Employee("Lora", payGradeRepository.findByGrade("C").get());

//
//        Employee e1 = new Employee("John");
//        Employee e2 = new Employee("David");
//        Employee e3 = new Employee("Sarah");
//        Employee e4 = new Employee("Lora");
//
//        List<Employee> employees = new ArrayList<>();
//        employees.add(e1);
//        employees.add(e2);
//        employees.add(e3);
//        employees.add(e4);
//
//        Company company = new Company("ABC Ltd", employees);
//
//        company.addSelfToEmployees();
//
//        this.save(company);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Company save(Company company) {
        companyRepository.save(company);
        return company;
    }


    /*
    Scenario

    1. Delete company and all employees are deleted but NOT the pay grades
    2. Change address detail in an employee and persist the company - should persist the change in the employee





     */





}
