package com.neotechnology.cineasts.domain;

import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedToVia;
import org.springframework.data.graph.core.Direction;
import org.springframework.transaction.annotation.Transactional;

@NodeEntity
public class Movie{

    @Indexed(indexName="movie_id")
    private String id;
    
    private String title;

    @RelatedToVia(type = "RATING", elementClass = Rating.class,direction= Direction.INCOMING)
    private Iterable<Rating> ratings;// = new TreeSet<Rating>();

    public Iterable<Rating> getRatings() {
		return ratings;
	}
    
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
		StringBuilder sb = new StringBuilder();
		sb.append("I am the Movie object "+title + "<br>");
		for (Rating rating : ratings) {
			sb.append(rating.getComment() + " " + rating.getRating() + "<br>");
		}
		return sb.toString();
	}
}
