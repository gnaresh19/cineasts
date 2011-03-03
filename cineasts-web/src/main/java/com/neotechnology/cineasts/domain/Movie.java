package com.neotechnology.cineasts.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedTo;

@NodeEntity
//@Indexed(indexName="movie")
public class Movie{

    @Indexed(indexName="movie_id")
    private String id;
    
    private String title;

    @RelatedTo(type = "HAS_RATING", elementClass = Rating.class)
    private Set<Rating> ratings;// = new TreeSet<Rating>();
    
    public Movie() {        
    }
    
	public Movie(String id, String title) {
	    this.id = id;
	    this.title = title;
    }
	
	public Movie(String id, String title,Rating rating) {
		this(id,title);
		System.out.println(ratings.getClass());
		ratings.add(rating);
	    
    }

	public void addRating(Rating rating) {
		ratings.add(rating);
	}
	
	public Iterable<Rating> getRatings() {
		return ratings;
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
		return "I am the Movie object "+title + ": Ratings   = " ;
	}
}
