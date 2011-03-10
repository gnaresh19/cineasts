package com.neotechnology.cineasts.domain;

import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.annotation.NodeEntity;
import org.springframework.data.graph.annotation.RelatedToVia;
import org.springframework.data.graph.core.Direction;

@NodeEntity
public class Actor {
    
    @Indexed(indexName="actor_id")
    private String id;
    
    private String name;
    
    @RelatedToVia(type=Participation.RELATIONSHIP_TYPE, direction=Direction.OUTGOING , elementClass=Participation.class)
    Iterable<Participation> participations;
    
    public Actor() {        
    }
    
    public Actor(String id, String name) {
        this.id = id;
        this.name = name;
    }

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
    
    public Iterable<Participation> getParticipations() {
        return participations;
    }

    public void participateIn(Movie movie, Role role) {
        // TODO: Only add if not already related in same role.
        Participation participation = relateTo(movie, Participation.class, Participation.RELATIONSHIP_TYPE);
        participation.setRole(role);
    }

    @Override
	public String toString() {
		return "I am the actor object "+name;
	}
}
