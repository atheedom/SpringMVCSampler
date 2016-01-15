package com.springmvcsampler.web.controller;

import com.springmvcsampler.model.Company;
import com.springmvcsampler.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompanyController {


    @Autowired
    private CompanyService companyService;


    @RequestMapping(value = "/company/create", method = RequestMethod.GET)
    public ModelAndView companyView() {
        return new ModelAndView("company/create", "company", new Company());
    }


    @RequestMapping(value = "/company/create", method = RequestMethod.POST)
    public ModelAndView companyCreate(@Validated @ModelAttribute Company company) {

        companyService.save(company);

        return new ModelAndView();
    }


}
