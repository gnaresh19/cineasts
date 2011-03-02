package com.neotechnology.cineasts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.graph.neo4j.finder.FinderFactory;
import org.springframework.data.graph.neo4j.support.GraphDatabaseContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.neotechnology.cineasts.domain.Actor;
import com.neotechnology.cineasts.domain.Movie;

@Component
public class SpringDataGraphCineastsService implements com.neotechnology.cineasts.service.CineastsService {

    @Autowired
    private FinderFactory finderFactory;
    
    @Autowired
    private GraphDatabaseContext graphDatabaseContext;

    @Transactional
    public void populateWithSomeTestData() {
        System.out.println("==== Populating ====");
        if (!graphDatabaseContext.transactionIsRunning()) {
            throw new RuntimeException("No transaction running");
        }
        new Actor("1", "Peter Weller");
        new Actor("2", "Ronny Cox");
        new Actor("3", "Kurtwood Smith");
        
        new Movie("1", "Robocop");
        new Movie("2", "Planet Terror");
    }
    
    @Override
    public Actor findActorById(String id) {
        return finderFactory.createNodeEntityFinder( Actor.class ).findByPropertyValue("actor_id", "id", id);
    }

    @Override
    public Movie findMovieById(String id) {
        return finderFactory.createNodeEntityFinder( Movie.class ).findByPropertyValue("movie_id", "id", id);
    }
    
    @Override
    public Iterable<Actor> findAllActors() {
        return finderFactory.createNodeEntityFinder( Actor.class ).findAll();        
    }
}
