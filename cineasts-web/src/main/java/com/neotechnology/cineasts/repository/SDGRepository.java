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
        new Actor();
        new Actor();
        new Actor();
    }
    
    @Override
    public Actor findActorById(long id) {
        return finderFactory.createNodeEntityFinder( Actor.class ).findById(id);
    }

    @Override
    public Movie findMovieById(long id) {
        return finderFactory.createNodeEntityFinder( Movie.class ).findById(id);
    }
    
    @Override
    public Iterable<Actor> findAllActors() {
        return finderFactory.createNodeEntityFinder( Actor.class ).findAll();        
    }
}
