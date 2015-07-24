package com.springmvcsampler.service;

import com.springmvcsampler.model.Account;
import com.springmvcsampler.model.CustomUserDetails;
import com.springmvcsampler.repository.AccountRepository;
import com.springmvcsampler.web.form.AccountCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;

@Service("Service that manages accounts and security")
public class AccountService  {

	@Autowired
	private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void initialize() {
//            this.save(new Account("user@example.com", "user", "demo", AccountCreateForm.Role.ROLE_USER));
//            this.save(new Account("admin@example.com", "admin", "admin", AccountCreateForm.Role.ROLE_ADMIN));
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }

	public Account create(AccountCreateForm form) {
		Account account = new Account();
		account.setUsername(form.getUsername());
		account.setEmail(form.getEmail());
		account.setPassword(passwordEncoder.encode(form.getPassword()));
		account.setRole(form.getRole());
		return accountRepository.save(account);
	}

	public Account getCurrentAccount() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		CustomUserDetails springSecurityUser = (CustomUserDetails) securityContext.getAuthentication().getPrincipal();
		return springSecurityUser.getAccount();
	}

	public Account findByEmail(String email){
		return accountRepository.findByEmail(email);
	}

	public Optional<Account> getAccountByEmail(String email) {
		return accountRepository.findOneByEmail(email);
	}

	public Account findByUsername(String username){
		return accountRepository.findByUsername(username);
	}

	public Collection<Account> getAllAccounts() {
		return accountRepository.findAll(new Sort("username"));
	}

	public Optional<Account> getUserById(Long id) {
		return Optional.ofNullable(accountRepository.findOne(id));
	}

	public Optional<Account> getAccountByUsername(String username) {
		return Optional.ofNullable(accountRepository.findByUsername(username));
	}

}
