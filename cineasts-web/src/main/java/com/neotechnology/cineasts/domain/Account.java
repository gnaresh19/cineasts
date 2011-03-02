package com.neotechnology.cineasts.domain;

import org.springframework.util.StringUtils;

public class Account {

	private String username;
	private String password;
	private String email;
	
	public Account(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public Account() {}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
	
	/**
	 * Simple validation
	 * @return
	 */
	public boolean isValid() {
		if(StringUtils.hasLength(email) &&
				StringUtils.hasLength(password) &&
				StringUtils.hasLength(username)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password
				+ ", email=" + email + "]";
	}
	
}
