package com.neotechnology.cineasts.domain;

import org.springframework.data.graph.annotation.EndNode;
import org.springframework.data.graph.annotation.RelationshipEntity;
import org.springframework.data.graph.annotation.StartNode;

@RelationshipEntity
public class Rating {
	
	@StartNode
	Account account;
	
	@EndNode
	Movie movie;
	
	private String comment;
	private int rating;
	
	public Rating() {
	}
	
	public Rating(String comment, int rating) {
		super();
		this.comment = comment;
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
}
