package com.springmvcsampler.service;

import com.springmvcsampler.model.Account;
import com.springmvcsampler.model.CustomUserDetails;
import com.springmvcsampler.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional(readOnly = true)
@Service("Service that manages accounts and security")
public class AccountService  {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void initialize() {
            this.save(new Account("user@example.com", "user", "demo", "ROLE_USER"));
            this.save(new Account("admin@example.com", "admin", "admin", "ROLE_ADMIN"));
	}

    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		return userService.loadUserByUsername(username);
	}

	public Account getCurrentAccount() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		CustomUserDetails springSecurityUser = (CustomUserDetails) securityContext.getAuthentication().getPrincipal();
		return springSecurityUser.getAccount();
	}

}
