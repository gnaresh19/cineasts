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

    @RelatedToVia(type=Participation.RELATIONSHIP_TYPE, direction=Direction.INCOMING , elementClass=Participation.class)
    Iterable<Participation> participations;    

    public Iterable<Rating> getRatings() {
		return ratings;
	}
    
    public Iterable<Participation> getParticipations() {
        return participations;
    }

    public Movie() {        
    }
    
    public Movie(String id) {
        this.id = id;
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
    
    public float averageRating() {
    	float total = 0;
    	float nrOfRatings = 0;
    	for(Rating rating: ratings) {
    		total += rating.getRating();
    		nrOfRatings++;
    	}
    	
    	return total / nrOfRatings;
    }
    
    public int ratingCount() {
    	int nrOfRatings = 0;
    	for(Rating rating: ratings) {
    		nrOfRatings++;
    	}
    	return nrOfRatings;
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
