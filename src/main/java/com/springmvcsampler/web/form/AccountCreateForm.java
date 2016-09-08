package com.springmvcsampler.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class AccountCreateForm extends AbstractForm {

	public enum Role { ROLE_USER, ROLE_ADMIN }

	@NotBlank(message = AccountCreateForm.NOT_BLANK_MESSAGE)
	private String username;

    @NotBlank(message = AccountCreateForm.NOT_BLANK_MESSAGE)
	@Email(message = AccountCreateForm.EMAIL_MESSAGE)
	private String email;

    @NotBlank(message = AccountCreateForm.NOT_BLANK_MESSAGE)
	private String password;

	@NotBlank(message = AccountCreateForm.NOT_BLANK_MESSAGE)
	private String passwordRepeated = "";

	@NotNull
	private Role role = Role.ROLE_USER;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPasswordRepeated() {
		return passwordRepeated;
	}

	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserCreateForm{" +
				"email='" + email.replaceFirst("@.+", "@***") + '\'' +
				", password=***" + '\'' +
				", passwordRepeated=***" + '\'' +
				", role=" + role +
				'}';
	}

}
