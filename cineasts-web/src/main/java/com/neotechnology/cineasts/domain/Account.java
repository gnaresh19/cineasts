package com.neotechnology.cineasts.domain;

import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedToVia;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@NodeEntity(autoAttach = false)
public class Account {

	@Indexed(indexName = "username")
	private String username;

	private String password;
	
	private String email;
	
	@RelatedToVia(type = "RATING", elementClass = Rating.class)
    private Iterable<Rating> ratings;// = new TreeSet<Rating>();
	
	public Account(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	public Account() {}
	
	public Iterable<Rating> getRatings() {
		return ratings;
	}
	
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
	 * Rates the specified movie by this user.
	 * TODO - A user should only be able to rate the same movie once?
	 */
	@Transactional
    public void rate(Movie movie, String comment, int rating) {	
		Rating newRating = this.relateTo(movie, Rating.class,"RATING");
		newRating.setComment(comment);
		newRating.setRating(rating);
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
