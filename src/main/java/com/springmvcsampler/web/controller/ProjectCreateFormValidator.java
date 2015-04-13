package com.springmvcsampler.web.controller;

import com.springmvcsampler.service.ProjectService;
import com.springmvcsampler.web.form.ProjectCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by atheedom on 10/04/15.
 */
@Component("Validates a Project creation form")
public class ProjectCreateFormValidator implements Validator {

    private final ProjectService projectService;

    @Autowired
    public ProjectCreateFormValidator(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ProjectCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProjectCreateForm form = (ProjectCreateForm) target;
        validateName(errors, form);
    }

    private void validateName(Errors errors, ProjectCreateForm form) {
        if (!projectService.getProjectByName(form.getName()).isPresent()) {
            errors.reject("name.exists", "The project name already exists");
        }
    }

}
