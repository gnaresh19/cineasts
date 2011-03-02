package com.neotechnology.cineasts.domain;

import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.annotation.NodeEntity;

@NodeEntity
public class Movie {

    @Indexed
    private String id;
    
    private String title;

    public Movie() {        
    }
    
	public Movie(String id, String title) {
	    this.id = id;
	    this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    @Override
	public String toString() {
		return "I am the Movie object "+title;
	}
}
