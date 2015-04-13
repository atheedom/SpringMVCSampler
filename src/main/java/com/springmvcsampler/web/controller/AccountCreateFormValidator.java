package com.springmvcsampler.web.controller;

import com.springmvcsampler.service.AccountService;
import com.springmvcsampler.web.form.AccountCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;

/**
 * Created by atheedom on 09/04/15.
 */
@Component("Validates Account creation form")
public class AccountCreateFormValidator implements Validator {

    private final AccountService accountService;

    @Autowired
    public AccountCreateFormValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AccountCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountCreateForm form = (AccountCreateForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
        validateUsername(errors, form);
        validateRole(errors, form);
    }

    private void validatePasswords(Errors errors, AccountCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateEmail(Errors errors, AccountCreateForm form) {
        if (accountService.getAccountByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "Account with this email already exists");
        }
    }

    private void validateUsername(Errors errors, AccountCreateForm form) {
        if (accountService.getAccountByUsername(form.getUsername()).isPresent()) {
            errors.reject("username.exists", "Account with this username already exists");
        }
    }

    private void validateRole(Errors errors, AccountCreateForm form) {
        if(!Arrays.asList(AccountCreateForm.Role.values()).contains(form.getRole())){
            errors.reject("role.exists", "Role does not exist");
        }
    }
}
