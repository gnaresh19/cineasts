package com.neotechnology.cineasts.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.graph.neo4j.finder.FinderFactory;
import org.springframework.data.graph.neo4j.support.GraphDatabaseContext;
import org.springframework.stereotype.Repository;

import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;

@Repository
public class SDGRepository implements com.neotechnology.cineasts.repository.Repository {

    @Autowired
    private FinderFactory finderFactory;
    
    @Autowired
    private GraphDatabaseContext graphDatabaseContext;

    public void populateWithSomeTestData() {
        System.out.println("==== Populating ====");
        if (!graphDatabaseContext.transactionIsRunning()) {
            throw new RuntimeException("No transaction running");
        }
        new Actor("1", "Peter Weller");
        new Actor("2", "Ronny Cox");
        new Actor("3", "Kurtwood Smith");
        
        new Movie("10", "Robocop");
        new Movie("11", "Planet Terror");
    }
    
    @Override
    public Actor findActorById(String id) {
        return finderFactory.createNodeEntityFinder( Actor.class ).findByPropertyValue(null, "id", id);
    }

    @Override
    public Movie findMovieById(String id) {
        return finderFactory.createNodeEntityFinder( Movie.class ).findByPropertyValue(null, "id", id);
    }
    
    @Override
    public Iterable<Actor> findAllActors() {
        return finderFactory.createNodeEntityFinder( Actor.class ).findAll();        
    }
}
