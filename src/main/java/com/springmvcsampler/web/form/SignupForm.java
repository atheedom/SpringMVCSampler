package com.springmvcsampler.web.form;

import com.springmvcsampler.model.Account;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class SignupForm {

	public enum Role { ROLE_USER, ROLE_ADMIN }

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";

	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String username;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	@Email(message = SignupForm.EMAIL_MESSAGE)
	private String email;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;

	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String passwordRepeated = "";

//	@NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
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

	public Account createAccount() {
        return new Account(getUsername(), getEmail(), getPassword(), getRole());
	}
}
