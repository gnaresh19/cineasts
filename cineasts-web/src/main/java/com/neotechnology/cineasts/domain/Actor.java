package com.neotechnology.cineasts.domain;

import org.springframework.data.annotation.Indexed;
import org.springframework.data.graph.annotation.NodeEntity;

@NodeEntity
public class Actor {
    
    @Indexed(indexName="actor_id")
    private String id;
    
    private String name;
    
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

    @Override
	public String toString() {
		return "I am the actor object "+name;
	}
}
