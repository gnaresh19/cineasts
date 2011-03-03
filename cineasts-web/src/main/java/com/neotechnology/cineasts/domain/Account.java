package com.neotechnology.cineasts.domain;

import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.util.StringUtils;

@NodeEntity(autoAttach = false)
public class Account {

	@Indexed(indexName = "username")
	private String username;

	private String password;
	
	private String email;
	
	public Account(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	/**
	 * Used to copy an account, useful when you want to create an Account inside
	 * @Transactional from an command object account
	 */
	public static Account copy(Account account) {
		return new Account(account.getUsername(), account.getPassword(), account.getEmail());
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
