package com.springmvcsampler.web.controller;

import com.springmvcsampler.service.AccountService;
import com.springmvcsampler.web.form.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

/**
 * Created by atheedom on 09/04/15.
 */
@Component
public class AccountCreateFormValidator implements Validator {

    private final AccountService accountService;

    @Autowired
    public AccountCreateFormValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SignupForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupForm form = (SignupForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
        validateUsername(errors, form);
        validateRole(errors, form);
    }

    private void validatePasswords(Errors errors, SignupForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateEmail(Errors errors, SignupForm form) {
        if (accountService.getAccountByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "User with this email already exists");
        }
    }

    private void validateUsername(Errors errors, SignupForm form) {
        if (accountService.getAccountByUsername(form.getUsername()).isPresent()) {
            errors.reject("username.exists", "User with this username already exists");
        }
    }

    private void validateRole(Errors errors, SignupForm form) {
        if(Arrays.asList(SignupForm.Role.values()).contains(form.getRole())){
            errors.reject("role.exists", "Role does not exist");
        }
    }
}
