package com.springmvcsampler.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by atheedom on 07/04/15.
 */
public class CustomUserDetails extends User {

    private Account account;

    public CustomUserDetails(Account account) {
        this(account, new ArrayList<GrantedAuthority>());
    }

    public CustomUserDetails(Account account, Collection<? extends GrantedAuthority> grantedAuthorities) {
        super(account.getUsername(), account.getPassword(), grantedAuthorities);
        this.account = account;
    }


    public Account getAccount() {
        return account;
    }
}