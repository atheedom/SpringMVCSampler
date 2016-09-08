package com.springmvcsampler.service;

import com.springmvcsampler.model.Employee;
import com.springmvcsampler.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("Service that manages employees")
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

}
