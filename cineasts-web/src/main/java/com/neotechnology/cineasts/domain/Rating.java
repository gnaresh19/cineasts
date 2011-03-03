package com.neotechnology.cineasts.domain;

import org.springframework.data.graph.annotation.NodeEntity;

@NodeEntity(autoAttach = false)
public class Rating {
	
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
	
	
}
