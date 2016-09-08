package com.springmvcsampler.config;

import com.springmvcsampler.model.Account;
import com.springmvcsampler.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Created by atheedom on 07/04/15.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<Account> {

    @Autowired
    private AccountService accountService;

    public Account getCurrentAuditor() {
        return accountService.getCurrentAccount();
    }
}