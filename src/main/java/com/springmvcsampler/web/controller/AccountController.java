package com.springmvcsampler.web.controller;

import com.springmvcsampler.model.Account;
import com.springmvcsampler.service.AccountService;
import com.springmvcsampler.web.form.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@Secured("ROLE_USER")
class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountCreateFormValidator accountCreateFormValidator;

//    public AccountController(AccountService accountService) {
//        this.accountService = accountService;
//    }

    @Autowired
    public AccountController(AccountService accountService, AccountCreateFormValidator accountCreateFormValidator) {
        this.accountService = accountService;
        this.accountCreateFormValidator = accountCreateFormValidator;
    }

    @RequestMapping(value = "account/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Account accounts(Principal principal) {
        Assert.notNull(principal);
        return accountService.findByUsername(principal.getName());
    }


    @RequestMapping("/accounts")
    public ModelAndView getUsersPage() {
        return new ModelAndView("accounts", "accounts", accountService.getAllAccounts());
    }




    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(accountCreateFormValidator);
    }

    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable UUID id) {
        return new ModelAndView("user", "user", accountService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
//        (String viewName, String modelName, Object modelObject)
        return new ModelAndView("user_create", "form", new SignupForm());
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") SignupForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_create";
        }
        try {
            accountService.create(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        return "redirect:/users";
    }

}



//    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
//    public ModelAndView showUsers(Model model, Pageable pageable) {
//        model.addAttribute("users", accountRepository.findAll(pageable));
//        return "users";
//    }
