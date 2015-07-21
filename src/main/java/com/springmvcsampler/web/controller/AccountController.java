package com.springmvcsampler.web.controller;

import com.springmvcsampler.model.Account;
import com.springmvcsampler.service.AccountService;
import com.springmvcsampler.web.form.AccountCreateForm;
import com.springmvcsampler.web.support.web.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller("Controller for the Account Service")
@Secured({"ROLE_USER", "ROLE_ADMIN"})
class AccountController implements AbstractPersonController {

    private static final String ACCOUNT_CREATE = "account/create";
    private static final String ACCOUNT_LIST = "account/accounts";
    private static final String ACCOUNT_VIEW = "account/account";

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountCreateFormValidator accountCreateFormValidator;

    @Autowired
    public AccountController(AccountService accountService, AccountCreateFormValidator accountCreateFormValidator) {
        this.accountService = accountService;
        this.accountCreateFormValidator = accountCreateFormValidator;
    }

    @RequestMapping(value = "accounts/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Account accounts(Principal principal) {
        Assert.notNull(principal);
        return accountService.findByUsername(principal.getName());
    }

    @RequestMapping("/accounts")
    public ModelAndView listAccounts() {
        return new ModelAndView(ACCOUNT_LIST, "accounts", accountService.getAllAccounts());
    }

    @InitBinder("accountCreateForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(accountCreateFormValidator);
    }

    @RequestMapping("/accounts/{id}")
    public ModelAndView viewAccount(@PathVariable Long id) {
        return new ModelAndView(ACCOUNT_VIEW, "account", accountService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }


    @RequestMapping(value = "/accounts/create", method = RequestMethod.GET)
    public String createAccountForm(Model model) {
        model.addAttribute(new AccountCreateForm());
        return ACCOUNT_CREATE;
    }

    @RequestMapping(value = "/accounts/create", method = RequestMethod.POST)
    public String createAccount(@Valid @ModelAttribute AccountCreateForm accountCreateForm, BindingResult errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return ACCOUNT_CREATE;
        }
        accountService.create(accountCreateForm);

        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "signup.success");
        return "redirect:/accounts";
    }

}


//    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
//    public ModelAndView showUsers(Model model, Pageable pageable) {
//        model.addAttribute("users", accountRepository.findAll(pageable));
//        return "users";
//    }
