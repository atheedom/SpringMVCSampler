package com.springmvcsampler.model;

import com.springmvcsampler.web.form.AccountCreateForm;

import javax.persistence.*;

@Entity
@Table(name = "Accounts",
        indexes = {
                @Index(name = "email_index", columnList = "email"),
                @Index(name = "username_index", columnList = "username"),
                @Index(name = "role_index", columnList = "role")
        }
)
public class Account extends BaseEntity<Account> {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountCreateForm.Role role = AccountCreateForm.Role.ROLE_USER;

    public Account() {

    }

    public Account(String email, String password, AccountCreateForm.Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public Account(String email, String username, String password, AccountCreateForm.Role role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountCreateForm.Role getRole() {
        return role;
    }

    public void setRole(AccountCreateForm.Role role) {
        this.role = role;
    }
}
