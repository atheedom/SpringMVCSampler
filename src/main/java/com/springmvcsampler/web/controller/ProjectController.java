package com.springmvcsampler.web.controller;

import com.springmvcsampler.service.ProjectService;
import com.springmvcsampler.web.form.ProjectCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Created by atheedom on 10/04/15.
 */
@Controller("Controller for the Project Service")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectCreateFormValidator projectCreateFormValidator;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectCreateFormValidator projectCreateFormValidation){
        this.projectCreateFormValidator = projectCreateFormValidator;
        this.projectService = projectService;
    }

    @RequestMapping("/projects")
    public ModelAndView getAllProjects(Pageable pageable) {
        return new ModelAndView("projects", "projects", projectService.getAllProjects(pageable));
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(projectCreateFormValidator);
    }

    @RequestMapping("/projects/{id}")
    public ModelAndView getProjectPage(@PathVariable UUID id) {
        return new ModelAndView("project", "project", projectService.getProjectById(id).orElseThrow(() -> new NoSuchElementException(String.format("Project=%s not found", id))));
    }

    @RequestMapping("/projects/{clientName}")
    public ModelAndView getProjectPage(@PathVariable String clientName, Pageable pageable) {
        return new ModelAndView("project", "project", projectService.getProjectByClientName(clientName, pageable));
    }

    @RequestMapping(value = "/projects/create", method = RequestMethod.GET)
    public ModelAndView getProjectCreatePage() {
//        (String viewName, String modelName, Object modelObject)
        return new ModelAndView("project_create", "project", new ProjectCreateForm());
    }

    @RequestMapping(value = "/projects/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("project") ProjectCreateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "project_create";
        }
        try {
            projectService.create(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("projectName.exists", "Project name already exists");
            return "project_create";
        }
        return "redirect:/projects";
    }


}
