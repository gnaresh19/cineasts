package com.neotechnology.cineasts.domain;

import org.springframework.data.graph.annotation.EndNode;
import org.springframework.data.graph.annotation.RelationshipEntity;
import org.springframework.data.graph.annotation.StartNode;


@RelationshipEntity
public class Participation {
    public static final String RELATIONSHIP_TYPE = "PARTICIPATION";
    
    @StartNode
    private Actor actor;
    @EndNode
    private Movie movie;
    
    private Role role;
    
    public Participation() {        
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Actor getActor() {
        return actor;
    }

    public Movie getMovie() {
        return movie;
    }
}
