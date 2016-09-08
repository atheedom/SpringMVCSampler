package com.springmvcsampler.web.controller;

import com.springmvcsampler.model.Employee;
import com.springmvcsampler.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;


    @RequestMapping(value = "/employee/create", method = RequestMethod.GET)
    public ModelAndView employeeView() {
        return new ModelAndView("employee/create", "employee", new Employee());
    }


    @RequestMapping(value = "/employee/create", method = RequestMethod.POST)
    public ModelAndView employeeCreate(@Validated @ModelAttribute Employee employee) {

        employeeService.save(employee);


        return new ModelAndView();
    }


}
